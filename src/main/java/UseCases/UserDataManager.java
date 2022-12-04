package UseCases;

import Gateways.DatabaseInterface;
import Entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Use case operations on a user's data.
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

    public ArrayList<String> getUserIDList(){
        fi.initialize("users");
        return fi.getDocumentStringList();
    }

    /**
     * add a user to the database.
     * @param user The user to add
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
     * @param student The student that is being added.
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
        //fi.addEntry(studentID, "friend list", student.getFriendList());
        //fi.addEntry(studentID, "friend list request", student.getFriendListRequest());
        ArrayList<String> tagList = new ArrayList<>();
        for(InterestTag i: student.getTags()){
            tagList.add(i.getName());
        }
        tagList.remove("");
        fi.addEntry(studentID, "tags of interests", tagList.toString());
        return true;
    }

    /**
     * Update the students in courses.
     * @param student The student to update.
     */
    public void updateStudentCourses(Student student){
        String studentID = student.getUserID();
        fi.addEntry(studentID, "enrolled courses", student.getEnrolledCourseCodes().toString());
    }

    /**
     * Add an admin user to the database.
     * @param admin The admin to add to the database.
     * @return whether an admin user is added. If returned false, then the admin user already exists.
     */
    public boolean addAdminUser(Admin admin) throws IOException {
        fi.initialize("users");
        String adminID = admin.getUserID();
        addUser(admin);
        fi.addEntry(adminID, "account type", "admin");
        return true;
    }

    /**
     * Remove a user from database.
     * @param userID The id of the user to remove.
     * @return True if the user is removed, false otherwise.
     */
    public boolean removeUser(String userID){
        fi.initialize("users");
        return fi.removeEntry(userID);
    }

    /**
     * Get a user by userid.
     * @param userID The user id to search for.
     * @return The user that is associated with the id.
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
                List<String> labels = Arrays.asList(labelsString.substring(1, labelsString.length() - 1).split(", "));

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
     * Get a list of common session between two users.
     * @param self The current user.
     * @param target The user to compare with.
     * @return An ArrayList of Strings of common sessions.
     */
    public ArrayList<String> getCommonSessionCode(Student self, Student target) throws IOException {
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

    /**
     * To check whether a user exists in the database.
     * @param user The User that is checked.
     * @return True if user is in database, false otherwise.
     */
    public boolean exist(User user){
        fi.initialize("users");
        return fi.getDocumentList().contains(user.getUserID());
    }

    /**
     * Check if user exists in the database.
     * @param ID The id of the user
     * @return True if user is in database, false otherwise.
     */
    public boolean existByID(String ID){
        fi.initialize("users");
        return fi.getDocumentStringList().contains(ID);
    }



}
