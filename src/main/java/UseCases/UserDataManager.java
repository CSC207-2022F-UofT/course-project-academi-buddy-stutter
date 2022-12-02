package UseCases;

import Gateways.DatabaseInterface;
import Entities.*;
import Entities.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserDataManager {

    private DatabaseInterface fi;

    public UserDataManager(DatabaseInterface ub){
        this.fi = ub;
        fi.initialize("users");
    }


    public ArrayList<String> getUserIDList(){
        fi.initialize("users");
        return fi.getDocumentStringList();
    }
    public boolean addUser(User user){
        /**
         * add a user to the database.
         * @return whether a user is added. If returned false, then the user already exists.
         */
        fi.initialize("users");
        String userID = user.getUserID();
        fi.addEntry(userID, "account type", "user");
        fi.addEntry(userID, "account password", user.getUserPassword());
        fi.addEntry(userID, "full name", user.getFullName());
        fi.addEntry(userID, "student info", user.getUserInfo());
        return true;
    }
    public boolean addStudentUser(Student student) throws IOException{
        /**
         * add a student user to the database.
         * @return whether a student user is added. If returned false, then the student user already exists.
         */
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
        fi.addEntry(studentID, "labels", labelList.toString());
        fi.addEntry(studentID, "enrolled courses", student.getEnrolledCourseCodes().toString());
//        fi.addEntry(studentID, "friend list", student.getFriendList().toString());
//        fi.addEntry(studentID, "friend list request", student.getFriendListRequest().toString());
        ArrayList<String> tagList = new ArrayList<>();
        for(InterestTag i: student.getTags()){
            tagList.add(i.getName());
        }
        fi.addEntry(studentID, "tags of interests", tagList.toString());

        ArrayList<String> friendList = student.getFriendList();
        ArrayList<String> friendRequests = student.getFriendListRequest();
        ArrayList<String> friendRequestsSent = student.getFriendRequestSentList();

        fi.addEntry(studentID, "friend list", friendList);
        fi.addEntry(studentID, "friend request list", friendRequests);
        fi.addEntry(studentID, "friend request sent list", friendRequestsSent);
        return true;
    }


    public void updateStudentCourses(Student student){
        fi.initialize("users");
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

    public boolean removeUser(User user){
        /**
         * remove a user from database.
         */
        fi.initialize("users");
        return fi.removeEntry(user.getUserID());
    }

    public User getUserByID(String userID) throws IOException{
        /**
         * get a user by userid.
         */
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
                List<String> courseCodes = Arrays.asList(courseCodesString.substring(1, courseCodesString.length() - 1).split(", "));
                ArrayList<String> courseList = new ArrayList<>();
                courseList.addAll(courseCodes);
                retrievedUser.setEnrolledCourses(courseList);
                //
                String labelsString = (String) userData.get("labels");
                List<String> labels = Arrays.asList(labelsString.substring(1, labelsString.length() - 1).split(", "));
                for(String l: labels){
                    Label label = new Label(l);
                    retrievedUser.updateLabel(label, true);
                }
                //
                String tagString = (String) userData.get("tags of interests");
                List<String> tags = Arrays.asList(tagString.substring(1, tagString.length() - 1).split(", "));
                for(String t: tags) {
                    InterestTag tag = new InterestTag(t);
                    retrievedUser.updateStudentTOI(tag, true);
                }
                // update get friend list
                String friendsString = (String) userData.get("friend list");
                String[] friends = friendsString.substring(1, friendsString.length()-1).split(",");
                for(String f: friends) {
                    retrievedUser.updateFriendList(f);
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
    public ArrayList<String> getCommonSessionCode(Student self, Student target) throws IOException {
        /**
         * get a list of common session between two users.
         */
        fi.initialize("users");
        ArrayList<String> commonSessions = new ArrayList<>();
        //accessing from database instead of directly from student class.
        Student s = (Student) getUserByID(self.getUserID());
        Student t = (Student) getUserByID(target.getUserID());
        ArrayList<String> selfEnrolledCourses = s.getEnrolledCourseCodes();
        ArrayList<String> targetEnrolledCourses = t.getEnrolledCourseCodes();
        for(String c: selfEnrolledCourses){
            if(targetEnrolledCourses.contains(c)){
                commonSessions.add(c);
            }
        }
        return commonSessions;
    }

    public boolean exist(User user){
        /**
         * @return whether a user exists in the database.
         */
        fi.initialize("users");
        return fi.getDocumentList().contains(user.getUserID());
    }


    public boolean existByID(String ID){
        /**
         * @return whether a user exists in the database.
         */
        fi.initialize("users");
        return fi.getDocumentStringList().contains(ID);
    }

    // FriendList Methods
    public void updateFriendList(Student student) {
        fi.initialize("users");
        String studentID = student.getUserID();
        fi.addEntry(studentID, "friend list", student.getFriendList());
    }

    public void updateFriendRequestList(Student student) {
        fi.initialize("users");
        String studentID = student.getUserID();
        fi.addEntry(studentID, "friend request list", student.getFriendListRequest());
    }

    public void updateFriendRequestsSentList(Student student) {
        fi.initialize("users");
        String studentID = student.getUserID();
        fi.addEntry(studentID, "friend request sent list", student.getFriendRequestSentList());
    }


    //Helper methods
    private ArrayList<String> toUserIdStrings(ArrayList<Student> students) {
        ArrayList<String> userIDs = new ArrayList<>();
        for(Student s: students){
            userIDs.add(s.getUserID());
        }
        return userIDs;
    }

}


