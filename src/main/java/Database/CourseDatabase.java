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
    private UserDatabase ud;
    private List<QueryDocumentSnapshot> currentDocuments;
    public CourseDatabase(DatabaseInterface cb, UserDatabase ud){
        this.fi = cb;
        this.fi.initialize("courses");
        this.ud = ud;
    }
    public void updateDocuments(){
        /**
         * update document list.
         */
        currentDocuments = fi.getDocumentList();
    }
    public ArrayList<String> getCourseCodeList(){
        /**
         * get a list of course code that is currently documented in the database.
         * @return an Arraylist of course code.
         */
        ArrayList<String> courseCodeList = new ArrayList<>();
        updateDocuments();
        for(QueryDocumentSnapshot course: currentDocuments){
            courseCodeList.add(course.getId());
        }
        return courseCodeList;
    }
    public void addCourse(Course course) throws IOException {
        /**
         * add a course to the database. Note that this will overwrite any course of the same course code in database.
         */
        String courseCode = course.getCourseCode();
        fi.addEntry(courseCode, "session type", course.getCourseType());
        fi.addEntry(courseCode, "session number", course.getSessionNumber());
        fi.addEntry(courseCode, "session name", course.getCourseName());
        fi.addEntry(courseCode, "day of week", course.getDayOfWeek());
        fi.addEntry(courseCode, "start time", course.getStartTime());
        fi.addEntry(courseCode, "year", course.getYear());
    }

    public boolean addStudent(Course course, Student student) throws IOException {
        /**
         * add a student to a course. It also updates in user database as well.
         */
        boolean added = course.addStudent(student);
        student.addCourse(course);
        if(added){
            fi.addEntry(course.getCourseCode(), "enrolled students id", course.getEnrolledID());
            ud.addStudentUser(student);//update student's enrolled course in userdatabase.
            return true;
        }
        return false;
    }

    public boolean removeStudent(Course course, Student student) throws IOException {
        /**
         * remove a student to a course. It also updates in user database as well.
         */
        boolean removed = course.removeStudent(student);
        student.removeCourse(course);
        if(removed){
            fi.addEntry(course.getCourseCode(), "enrolled students id", course.getEnrolledID());
            ud.addStudentUser(student);//update student's enrolled course in userdatabase.
            return true;
        }
        return false;
    }

    public Course getCourse(String courseCode) throws IOException {
        /**
         * get a course by course code.
         */
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
