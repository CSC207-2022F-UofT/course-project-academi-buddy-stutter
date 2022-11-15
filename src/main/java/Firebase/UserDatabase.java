package Firebase;

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

    public boolean addStudentUser(Student student) throws IOException{
        String studentID = student.GetUserID();
        db.addEntry(studentID, "account type", "student");
        db.addEntry(studentID, "account password", student.GetUserPassword());
        db.addEntry(studentID, "full name", student.getFull_name());
        db.addEntry(studentID, "student info", student.GetUserInfo());
//        db.addEntry(studentID, "tabs", student.getTabs_of_interests());
        db.addEntry(studentID, "enrolled courses", student.getEnrolled_courseCodes().toString());
        return true;
    }

    public boolean addAdminUser(Admin admin) throws IOException {
        String adminID = admin.GetUserID();
        db.addEntry(adminID, "account type", "admin");
        db.addEntry(adminID, "account password", admin.GetUserPassword());
        db.addEntry(adminID, "full name", admin.getFull_name());
        db.addEntry(adminID, "student info", admin.GetUserInfo());
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
        }else{
            Admin retrievedUser = new Admin(userID, uPass, fullName, info);
            return retrievedUser;
        }
    }

    public ArrayList<Course> getCommonSession(Student self, Student target){
        ArrayList<Course> commonSessions = new ArrayList<>();
        ArrayList<Course> selfEnrolledCourses = self.getEnrolledCourses();
        ArrayList<Course> targetEnrolledCourses = target.getEnrolledCourses();
        for(Course c: selfEnrolledCourses){
            if(targetEnrolledCourses.contains(c)){
                commonSessions.add(c);
            }
        }
        return commonSessions;
    }





}
