package database.cloud;

import database.accessinterfaces.UserDataAccess;
import adapters.gateways.DatabaseInterface;
import model.entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CloudUserData implements UserDataAccess {

    private final DatabaseInterface fi;

    public CloudUserData(DatabaseInterface ub){
        this.fi = ub;
        fi.openCollection("users");
    }

    @Override
    public ArrayList<String> getUserIDList(){
        fi.openCollection("users");
        return fi.getDocumentStringList();
    }

    /**
     * add a user to the database.
     * @return whether a user is added. If returned false, then the user already exists.
     */
    @Override
    public boolean addUser(User user){
        fi.openCollection("users");
        String userID = user.getUserID();
        fi.addEntry(userID, "account type", "user");
        fi.addEntry(userID, "account password", user.getUserPassword());
        fi.addEntry(userID, "full name", user.getFullName());
        fi.addEntry(userID, "student info", user.getUserInfo());
        return true;
    }

    /**
     * add a student user to the database.
     */
    @Override
    public void addStudentUser(Student student) {
        fi.openCollection("users");
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
    }

    @Override
    public void updateStudentCourses(Student student){
        fi.openCollection("users");
        String studentID = student.getUserID();
        fi.addEntry(studentID, "enrolled courses", student.getEnrolledCourseCodes().toString());
    }

    /**
     * remove a user from database.
     */
    @Override
    public void removeUser(String userID){
        fi.openCollection("users");
        fi.removeEntry(userID);
    }

    /**
     * get a user by userid.
     */
    @Override
    public User getUserByID(String userID) {
        fi.openCollection("users");
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
                List<String> courseCodes = Arrays.asList(courseCodesString.substring(1, courseCodesString.length() - 1).split(", "));
                ArrayList<String> courseList = new ArrayList<>(courseCodes);
                courseList.remove("");
                retrievedUser.setEnrolledCourses(courseList);

                //
                String labelsString = (String) userData.get("labels");
                List<String> labels = Arrays.asList(labelsString.substring(1, labelsString.length() - 1).split(", "));

                for(String l: labels) {
                    if(!labels.get(0).equals("")){
                        Label label = new Label(l);
                        retrievedUser.updateLabel(label, true);
                    }
                }

                //
                String tagString = (String) userData.get("tags of interests");
                String[] tags = tagString.substring(1, tagString.length() - 1).split(", ");
                for(String t: tags) {
                    if(!t.equals("")){
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

    /**
     * get a list of common session between two users.
     */
    @Override
    public ArrayList<String> getCommonSessionCode(String selfUserID, String targetUserID) {
        fi.openCollection("users");
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
     * @return whether a user exists in the database.
     */
    @Override
    public boolean existByID(String ID){
        fi.openCollection("users");
        return fi.getDocumentStringList().contains(ID);
    }

    // FriendList Methods
    @Override
    public void updateFriendList(Student student) {
        fi.openCollection("users");
        String studentID = student.getUserID();
        fi.addEntry(studentID, "friend list", student.getFriendList().toString());
//        System.out.println(student.getFullName() + "friend list has been updated on Firebase");
    }

    @Override
    public void updateFriendRequestList(Student student) {
        fi.openCollection("users");
        String studentID = student.getUserID();
        fi.addEntry(studentID, "friend request list", student.getFriendListRequest().toString());
//        System.out.println(student.getFullName() + "friend request list has been updated on Firebase");
    }

    @Override
    public void updateFriendRequestsSentList(Student student) {
        fi.openCollection("users");
        String studentID = student.getUserID();
        fi.addEntry(studentID, "friend request sent list", student.getFriendRequestSentList().toString());
//        System.out.println(student.getFullName() + "friend request sent list has been updated on Firebase");
    }

    @Override
    public ArrayList<String> getAdminIDs() {
        fi.openCollection("users");
        ArrayList<String> userIDs = this.getUserIDList();
        ArrayList<String> adminIDs = new ArrayList<>();
        for (String id: userIDs) {
            Map<String, Object> userData = fi.getEntry(id);
            String type = (String) userData.get("account type");
            if (type.equals("admin")) {
                adminIDs.add(id);
            }
        } return adminIDs;
    }

    @Override
    public void removeFromFriendList(Student student, Student friend) {
        fi.openCollection("users");
        fi.addEntry(friend.getUserID(), "friend list", "[]");
    }

    @Override
    public void removeFromFriendRequestSentList(Student student, Student friend) {
        fi.openCollection("users");
        fi.addEntry(friend.getUserID(), "friend request sent list", "[]");
    }

    @Override
    public void removeFromFriendRequestList(Student student, Student friend) {
        fi.openCollection("users");
        fi.addEntry(friend.getUserID(), "friend request list", "[]");
    }
}
