package Firebase;
import Sessions.Course;
import Users.Student;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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



}
