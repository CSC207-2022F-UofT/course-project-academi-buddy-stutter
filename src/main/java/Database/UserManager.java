package Database;

import Users.Student;
import Users.Admin;
import Users.User;
import Sessions.Course;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserManager {

    private DatabaseInterface fi;
    private List<QueryDocumentSnapshot> currentDocuments;

    public UserManager(DatabaseInterface ub){
        this.fi = ub;
        fi.initialize("users");
    }

    public void updateDocuments(){
        currentDocuments = fi.getDocumentList();
    }

    public ArrayList<String> getUserIDList(){
        ArrayList<String> userIDList = new ArrayList<>();
        updateDocuments();
        for(QueryDocumentSnapshot user:currentDocuments){
            userIDList.add(user.getId());
        }
        return userIDList;
    }
    public boolean addUser(User user){
        /**
         * add a user to the database.
         * @return whether a user is added. If returned false, then the user already exists.
         */
        if(exist(user)){
            return false;
        }
        String userID = user.GetUserID();
        fi.addEntry(userID, "account type", "user");
        fi.addEntry(userID, "account password", user.GetUserPassword());
        fi.addEntry(userID, "full name", user.getFull_name());
        fi.addEntry(userID, "student info", user.GetUserInfo());
        return true;
    }
    public boolean addStudentUser(Student student) throws IOException{
        /**
         * add a student user to the database.
         * @return whether a student user is added. If returned false, then the student user already exists.
         */
        if(exist(student)){
            return false;
        }
        String studentID = student.GetUserID();
        addUser(student);
        fi.addEntry(studentID, "account type", "student");
        //following data are stored as arraylists. Use toString().
        fi.addEntry(studentID, "labels", student.getLabels().toString());
        fi.addEntry(studentID, "enrolled courses", student.getEnrolled_courseCodes().toString());
        fi.addEntry(studentID, "tags of interests", student.getTabs_of_interests().toString());
        return true;
    }

    public boolean addAdminUser(Admin admin) throws IOException {
        /**
         * add an admin user to the database.
         * @return whether an admin user is added. If returned false, then the admin user already exists.
         */
        if(exist(admin)){
            return false;
        }
        String adminID = admin.GetUserID();
        addUser(admin);
        fi.addEntry(adminID, "account type", "admin");
        return true;
    }

    public boolean removeUser(User user){
        /**
         * remove a user from database.
         */
        return fi.removeEntry(user.getUser_id());
    }

    public User getUserByID(String userID) throws IOException{
        /**
         * get a user by userid.
         */
        Map<String, Object> userData = fi.getEntry(userID);
        String type = (String) userData.get("account type");
        String uPass = (String) userData.get("account password");
        String fullName = (String) userData.get("full name");
        String info = (String) userData.get("student info");
        try{
            if(type.equals("student")){
                //parsing ArrayList from String.
                String courseCodesString = (String) userData.get("enrolled courses");
                List<String> courseCodes = Arrays.asList(courseCodesString.substring(1, courseCodesString.length() - 1).split(", "));
                ArrayList<Course> courseList = new ArrayList<>();
                for(String courseCode: courseCodes){
                    CourseManager courseDB = new CourseManager(fi, this);
                    courseList.add(courseDB.getCourse(courseCode));
                }
                Student retrievedUser = new Student(userID, uPass, fullName, info);
                retrievedUser.setEnrolled_courses(courseList);
                return retrievedUser;
            }else if (type.equals("admin")){
                return new Admin(userID, uPass, fullName, info);
            }
            return new User(userID, uPass, fullName, info);
        }catch (NullPointerException e){
            System.out.println("null type");
        }
        return null;
    }

//    this method should be in matcher? not database! for clean architecture?
    public ArrayList<Course> getCommonSession(Student self, Student target) throws IOException {
        /**
         * get a list of common session between two users.
         */
        ArrayList<Course> commonSessions = new ArrayList<>();
        //accessing from database instead of directly from student class.
        Student s = (Student) getUserByID(self.getUser_id());
        Student t = (Student) getUserByID(target.getUser_id());
        ArrayList<Course> selfEnrolledCourses = s.getEnrolledCourses();
        ArrayList<Course> targetEnrolledCourses = t.getEnrolledCourses();
        for(Course c: selfEnrolledCourses){
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
        return fi.getDocumentList().contains(user.getUser_id());
    }





}
