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

public class UserDatabase {

    private FirebaseCollection db;
    private List<QueryDocumentSnapshot> currentDocuments;

    public UserDatabase() throws IOException {
         this.db = new FirebaseCollection("courses");
    }

    public void updateDocuments(){
        currentDocuments = db.getDocumentList();
    }

    public ArrayList<String> getUserIDList(){
        ArrayList<String> userIDList = new ArrayList<>();
        updateDocuments();
        for(QueryDocumentSnapshot user:currentDocuments){
            userIDList.add(user.getId());
        }
        return userIDList;
    }
    public boolean addUser(User user) throws IOException{
        if(ifUserExist(user)){
            return false;
        }
        String userID = user.GetUserID();
        db.addEntry(userID, "account type", "user");
        db.addEntry(userID, "account password", user.GetUserPassword());
        db.addEntry(userID, "full name", user.getFull_name());
        db.addEntry(userID, "student info", user.GetUserInfo());
        return true;
    }
    public boolean addStudentUser(Student student) throws IOException{
        if(ifUserExist(student)){
            return false;
        }
        String studentID = student.GetUserID();
        addUser(student);
        db.addEntry(studentID, "account type", "student");
        //following data are stored as arraylists. Use toString().
        db.addEntry(studentID, "labels", student.getLabels().toString());
        db.addEntry(studentID, "enrolled courses", student.getEnrolled_courseCodes().toString());
        db.addEntry(studentID, "tags of interests", student.getTabs_of_interests().toString());
        return true;
    }

    public boolean addAdminUser(Admin admin) throws IOException {
        if(ifUserExist(admin)){
            return false;
        }
        String adminID = admin.GetUserID();
        addUser(admin);
        db.addEntry(adminID, "account type", "admin");
        return true;
    }

    public boolean removeUser(User user) throws IOException{
        return db.removeEntry(user.getUser_id());
    }

    public User getUserByID(String userID) throws IOException{
        Map<String, Object> userData = db.getEntry(userID);
        String type = (String) userData.get("account type");
        String uPass = (String) userData.get("account password");
        String fullName = (String) userData.get("full name");
        String info = (String) userData.get("student info");
        if(type == "student"){
            //parsing ArrayList from String.
            String courseCodesString = (String) userData.get("enrolled courses");
            List<String> courseCodes = Arrays.asList(courseCodesString.substring(1, courseCodesString.length() - 1).split(", "));
            ArrayList<Course> courseList = new ArrayList<>();
            for(String courseCode: courseCodes){
                CourseDatabase courseDB = new CourseDatabase();
                courseList.add(courseDB.getCourse(this, courseCode));
            }
            Student retrievedUser = new Student(userID, uPass, fullName, info);
            retrievedUser.setEnrolled_courses(courseList);
            return retrievedUser;
        }else if (type == "admin"){
            Admin retrievedUser = new Admin(userID, uPass, fullName, info);
            return retrievedUser;
        }
        return new User(userID, uPass, fullName, info);
    }

    public ArrayList<Course> getCommonSession(Student self, Student target) throws IOException {
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

    public boolean ifUserExist(User user){
        return db.getDocumentList().contains(user.getUser_id());
    }





}
