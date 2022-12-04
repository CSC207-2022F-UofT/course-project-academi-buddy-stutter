package UseCases;

import Gateways.DatabaseInterface;
import Entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Use case operator for user data.
 */
public class UserDataManager {

    private DatabaseInterface fi;

    /**
     * Initializer.
     * @param ub The database interface.
     */
    public UserDataManager(DatabaseInterface ub){
        this.fi = ub;
        fi.initialize("users");
    }

    /**
     * Getting the user id list
     * @return An ArrayList of Strings of user ids
     */
    public ArrayList<String> getUserIDList(){
        fi.initialize("users");
        return fi.getDocumentStringList();
    }

    /**
     * add a user to the database.
     * @param user the user that is being added to database.
     * @return whether a user is added. If returned false, then the user already exists.
     */
    public boolean addUser(User user){
        fi.initialize("users");
        String userID = user.getUserID();
        fi.addEntry(userID, "account type", "user");
        fi.addEntry(userID, "account password", user.getUserPassword());
        fi.addEntry(userID, "full name", user.getFullName());
        fi.addEntry(userID, "student info", user.getUserInfo());
        return true;
    }

    /**
     * add a student user to the database.
     * @param student the student that is being added to database.
     * @return whether a student user is added. If returned false, then the student user already exists.
     */
    public boolean addStudentUser(Student student) throws IOException{
        fi.initialize("users");
        String studentID = student.getUserID();
        addUser(student);
        fi.addEntry(studentID, "account type", "student");
        fi.addEntry(studentID, "email", student.getEmail());
        //following data are stored as arraylists. Use toString().
        ArrayList<String> labelList = new ArrayList<>();
        for(Label i: student.getLabels()){
            labelList.add(i.getName());
        }
        labelList.remove("");
        fi.addEntry(studentID, "labels", labelList.toString());
        fi.addEntry(studentID, "enrolled courses", student.getEnrolledCourseCodes().toString());
        ArrayList<String> tagList = new ArrayList<>();
        for(InterestTag i: student.getTags()){
            tagList.add(i.getName());
        }
        tagList.remove("");
        fi.addEntry(studentID, "tags of interests", tagList.toString());

        ArrayList<String> friendList = student.getFriendList();
        ArrayList<String> friendRequests = student.getFriendListRequest();
        ArrayList<String> friendRequestsSent = student.getFriendRequestSentList();

        fi.addEntry(studentID, "friend list", friendList.toString());
        fi.addEntry(studentID, "friend request list", friendRequests.toString());
        fi.addEntry(studentID, "friend request sent list", friendRequestsSent.toString());
        return true;
    }

    /**
     * Update the courses for student.
     * @param student The student to update the courses for.
     */
    public void updateStudentCourses(Student student){
        String studentID = student.getUserID();
        fi.addEntry(studentID, "enrolled courses", student.getEnrolledCourseCodes().toString());
    }

    public boolean addAdminUser(Admin admin) throws IOException {
        /**
         * add an admin user to the database.
         * @return whether an admin user is added. If returned false, then the admin user already exists.
         */
        fi.initialize("users");
        String adminID = admin.getUserID();
        addUser(admin);
        fi.addEntry(adminID, "account type", "admin");
        return true;
    }

    /**
     * Removes a user from database.
     * @param userID the id of the user to be removed.
     * @return True if user was successfully removed, false otherwise.
     */
    public boolean removeUser(String userID){
        fi.initialize("users");
        return fi.removeEntry(userID);
    }

    /**
     * get a user by their user id
     * @param userID the id of the user to get
     * @return the user of the matching id
     */
    public User getUserByID(String userID) throws IOException{
        fi.initialize("users");
        Map<String, Object> userData = fi.getEntry(userID);
        String type = (String) userData.get("account type");
        String uPass = (String) userData.get("account password");
        String fullName = (String) userData.get("full name");
        String info = (String) userData.get("student info");

        try{
            if(type.equals("student")){

                Student retrievedUser = new Student(userID, uPass, fullName, info);
                String email = (String) userData.get("email");
                retrievedUser.setEmail(email);
                //parsing ArrayList from String.
                String courseCodesString = (String) userData.get("enrolled courses");
                List<String> courseCodes =
                        Arrays.asList(courseCodesString.substring(1, courseCodesString.length() - 1).split(", "));
                ArrayList<String> courseList = new ArrayList<>();
                courseList.addAll(courseCodes);
                if(courseList.contains("")){
                    courseList.remove("");
                }
                retrievedUser.setEnrolledCourses(courseList);

                //
                String labelsString = (String) userData.get("labels");
                List<String> labels =
                        Arrays.asList(labelsString.substring(1, labelsString.length() - 1).split(", "));

                for(String l: labels) {
                    if(!labels.get(0).equals("")){
                        Label label = new Label(l);
                        retrievedUser.updateLabel(label, true);
                    }
                }

                //
                String tagString = (String) userData.get("tags of interests");
                List<String> tags = Arrays.asList(tagString.substring(1, tagString.length() - 1).split(", "));
                for(String t: tags) {
                    if(!tags.equals("")){
                        InterestTag tag = new InterestTag(t);
                        retrievedUser.updateStudentTOI(tag, true);
                    }
                }

                // update get friend list
                String friendsString = (String) userData.get("friend list");
                if (!friendsString.equals("[]")) {
                    String[] friends = friendsString.substring(1, friendsString.length()-1).split(", ");
                    for(String f: friends) {
                        f = f.trim().strip();
                        retrievedUser.updateFriendList(f);
                    }
                }

                // update get friend request list
                String friendRequestString = (String) userData.get("friend request list");
                String[] friendRequests = friendRequestString.substring(1, friendRequestString.length()-1).split(", ");
                if (!friendRequestString.equals("[]")) {
                    for(String fr: friendRequests) {
                        fr = fr.trim().strip();
                        retrievedUser.updateFriendRequestList(fr);
                    }
                }

                // update get friend request sent list
                String requestSentString = (String) userData.get("friend request sent list");
                if(!requestSentString.equals("[]")){
                    String[] requests = requestSentString.substring(1, requestSentString.length()-1).split(", ");
                    for(String r: requests) {
                        r = r.trim().strip();
                        retrievedUser.updateFriendRequestSentList(r);
                    }
                }

                return retrievedUser;
            }else if (type.equals("admin")){
                return new Admin(userID, uPass, fullName, info);
            }
            return new User(userID, uPass, fullName, info);
        }catch (NullPointerException e){
            System.out.println(userID + "userManager: null type");
        }
        return null;
    }

    //    this method should be in matcher? not database! for clean architecture?

    /**
     * get a list of common session between two users.
     * @param selfUserID the current user id
     * @param targetUserID the target user id
     * @return A ArrayList of Strings of common sessions
     */
    public ArrayList<String> getCommonSessionCode(String selfUserID, String targetUserID) throws IOException {
        fi.initialize("users");
        ArrayList<String> commonSessions = new ArrayList<>();
        //accessing from database instead of directly from student class.
        Student s = (Student) getUserByID(selfUserID);
        Student t = (Student) getUserByID(targetUserID);
        ArrayList<String> selfEnrolledCourses = s.getEnrolledCourseCodes();
        ArrayList<String> targetEnrolledCourses = t.getEnrolledCourseCodes();
        for(String c: selfEnrolledCourses){
            if(targetEnrolledCourses.contains(c)){
                commonSessions.add(c);
            }
        }
        return commonSessions;
    }

    /**
     * whether a user exists in the database.
     * @return whether a user exists in the database.
     */
    public boolean exist(User user){
        fi.initialize("users");
        return fi.getDocumentList().contains(user.getUserID());
    }

    /**
     * whether a user exists in the database.
     * @return whether a user exists in the database.
     */
    public boolean existByID(String ID){
        fi.initialize("users");
        return fi.getDocumentStringList().contains(ID);
    }

    // FriendList Methods

    /**
     * Updating the friend list
     * @param student the student to update for
     */
    public void updateFriendList(Student student) {
        fi.initialize("users");
        String studentID = student.getUserID();
        fi.addEntry(studentID, "friend list", student.getFriendList().toString());
//        System.out.println(student.getFullName() + "friend list has been updated on Firebase");
    }

    /**
     * Updating the friend request list
     * @param student the student to update for
     */
    public void updateFriendRequestList(Student student) {
        fi.initialize("users");
        String studentID = student.getUserID();
        fi.addEntry(studentID, "friend request list", student.getFriendListRequest().toString());
//        System.out.println(student.getFullName() + "friend request list has been updated on Firebase");
    }

    /**
     * Updating the friend request sent list.
     * @param student the student to update for.
     */
    public void updateFriendRequestsSentList(Student student) {
        fi.initialize("users");
        String studentID = student.getUserID();
        fi.addEntry(studentID, "friend request sent list", student.getFriendRequestSentList().toString());
//        System.out.println(student.getFullName() + "friend request sent list has been updated on Firebase");
    }

    //Helper methods
    /**
     * Helper method
     */
    private ArrayList<String> toUserIdStrings(ArrayList<Student> students) {
        ArrayList<String> userIDs = new ArrayList<>();
        for(Student s: students){
            userIDs.add(s.getUserID());
        }
        return userIDs;
    }
}
