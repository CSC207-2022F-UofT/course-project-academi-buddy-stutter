package Firebase;
import Sessions.Course;
import Users.Student;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class courseDatabase {
    private FirebaseCollection db;
    private List<QueryDocumentSnapshot> currentDocuments;
    public courseDatabase() throws IOException {
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
        /*
        if(this.getCourseCodeList().contains(courseCode)){
            return false;
        }
        */
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
        if(added){
            db.addEntry(course.getCourseCode(), "enrolled students id", course.getEnrolledID());
            return true;
        }
        return false;
    }

    public boolean removeStudent(Course course, Student student) throws IOException {
        boolean removed = course.removeStudent(student);
        if(removed){
            db.addEntry(course.getCourseCode(), "enrolled students id", course.getEnrolledID());
            return true;
        }
        return false;
    }

    public Course getCourse(String courseCode){
        if(!this.getCourseCodeList().contains(courseCode)){
            return null;
        }
        Map<String, Object> courseDetail = db.getEntry(courseCode);
        Course course = new Course(courseCode, (String) courseDetail.get("session type"),
                (String) courseDetail.get("session number"), (String) courseDetail.get("session name"),
                (String) courseDetail.get("day of week"), (String) courseDetail.get("start time"),
                (String) courseDetail.get("year"));
        String sidString = (String) courseDetail.get("enrolled students id");
        List<String> sid = Arrays.asList(sidString.substring(1, sidString.length() - 1).split(", "));
        ArrayList<Student> enrolledStudents = new ArrayList<>();
        for(String userID: sid){
            //TODO enrolledStudents.add(userDatabase.getUser(userID));
        }
        course.setEnrolledStudents(enrolledStudents);
        return course;
    }
}
