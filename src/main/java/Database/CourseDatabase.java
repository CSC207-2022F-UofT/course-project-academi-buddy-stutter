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

public class CourseDatabase{
    private DatabaseInterface fi;
    private List<QueryDocumentSnapshot> currentDocuments;
    public CourseDatabase(DatabaseInterface cb){
        this.fi = cb;
        this.fi.initialize("courses");
    }
    public void updateDocuments(){
        currentDocuments = fi.getDocumentList();
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
        fi.addEntry(courseCode, "session type", course.getCourseType());
        fi.addEntry(courseCode, "session number", course.getSessionNumber());
        fi.addEntry(courseCode, "session name", course.getCourseName());
        fi.addEntry(courseCode, "day of week", course.getDayOfWeek());
        fi.addEntry(courseCode, "start time", course.getStartTime());
        fi.addEntry(courseCode, "year", course.getYear());
        fi.addEntry(courseCode, "enrolled students id", course.getEnrolledID().toString());
        return true;
    }

    public boolean addStudent(Course course, Student student) throws IOException {
        boolean added = course.addStudent(student);
        student.addCourse(course);
        if(added){
            fi.addEntry(course.getCourseCode(), "enrolled students id", course.getEnrolledID());
            return true;
        }
        return false;
    }

    public boolean removeStudent(Course course, Student student) throws IOException {
        boolean removed = course.removeStudent(student);
        student.removeCourse(course);
        if(removed){
            fi.addEntry(course.getCourseCode(), "enrolled students id", course.getEnrolledID());
            return true;
        }
        return false;
    }

    public Course getCourse(UserDatabase ud, String courseCode) throws IOException {
        if(!this.getCourseCodeList().contains(courseCode)){
            return null;
        }
        Map<String, Object> courseDetail = fi.getEntry(courseCode);
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
