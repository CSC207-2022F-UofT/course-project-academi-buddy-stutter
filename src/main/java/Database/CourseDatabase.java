package Database;
import Sessions.Course;
import Users.Student;
import Users.User;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CourseDatabase {
    private FirebaseCollection db;
    private List<QueryDocumentSnapshot> currentDocuments;
    public CourseDatabase(){
        db = new FirebaseCollection("courses");
    }
    public void updateDocuments(){
        currentDocuments = db.getDocumentList();
    }
    public ArrayList<String> getCourseCodeList(){
        ArrayList<String> courseCodeList = new ArrayList<>();
        updateDocuments();
        for(QueryDocumentSnapshot course: currentDocuments){
            courseCodeList.add(course.getId());
        }
        return courseCodeList;
    }
    public boolean addCourse(Course course) throws IOException {
        String courseCode = course.getCourseCode();
        db.addEntry(courseCode, "session type", course.getCourseType());
        db.addEntry(courseCode, "session number", course.getSessionNumber());
        db.addEntry(courseCode, "session name", course.getCourseName());
        db.addEntry(courseCode, "day of week", course.getDayOfWeek());
        db.addEntry(courseCode, "start time", course.getStartTime());
        db.addEntry(courseCode, "year", course.getYear());
        db.addEntry(courseCode, "enrolled students id", course.getEnrolledID().toString());
        return true;
    }

    public boolean addStudent(Course course, Student student) throws IOException {
        boolean added = course.addStudent(student);
        student.addCourse(course);
        if(added){
            db.addEntry(course.getCourseCode(), "enrolled students id", course.getEnrolledID());
            return true;
        }
        return false;
    }

    public boolean removeStudent(Course course, Student student) throws IOException {
        boolean removed = course.removeStudent(student);
        student.removeCourse(course);
        if(removed){
            db.addEntry(course.getCourseCode(), "enrolled students id", course.getEnrolledID());
            return true;
        }
        return false;
    }

    public Course getCourse(UserDatabase ud, String courseCode) throws IOException {
        if(!this.getCourseCodeList().contains(courseCode)){
            return null;
        }
        Map<String, Object> courseDetail = db.getEntry(courseCode);
        Course course = new Course(courseCode, (String) courseDetail.get("session type"),
                (String) courseDetail.get("session number"), (String) courseDetail.get("session name"),
                (String) courseDetail.get("day of week"), (String) courseDetail.get("start time"),
                (String) courseDetail.get("year"));
        String sidString = courseDetail.get("enrolled students id").toString();
        List<String> sid = Arrays.asList(sidString.substring(1, sidString.length() - 1).split(", "));
        ArrayList<Student> enrolledStudents = new ArrayList<>();
        for(String userID: sid){
            User u = ud.getUserByID(userID);
            if(u instanceof Student){
                enrolledStudents.add((Student) ud.getUserByID(userID));
            }
        }
        course.setEnrolledStudents(enrolledStudents);
        return course;
    }

}
