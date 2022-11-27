package UseCases;
import Gateways.DatabaseInterface;
import Entities.Course;
import Entities.Student;
import Entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CourseDataManager {
    private DatabaseInterface fi;
    private UserDataManager ud;
    public CourseDataManager(DatabaseInterface cb, UserDataManager ud){
        this.fi = cb;
        this.fi.initialize("courses");
        this.ud = ud;
    }
    public ArrayList<String> getCourseCodeList(){
        /**
         * get a list of course code that is currently documented in the database.
         * @return an Arraylist of course code.
         */
        fi.initialize("courses");
        return fi.getDocumentStringList();
    }
    public void addCourse(Course course) throws IOException {
        /**
         * add a course to the database. Note that this will overwrite any course of the same course code in database.
         */
        fi.initialize("courses");
        String courseCode = course.getCourseCode() + course.getCourseType();
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
        fi.initialize("courses");
        boolean added = course.addStudent(student);
        if(added){
            student.addCourse(course);
            fi.addEntry(course.getCourseCode() + course.getCourseType(), "enrolled students id", course.getEnrolledID());
            ud.addStudentUser(student);//update student's enrolled course in userdatabase.
            return true;
        }
        return false;
    }

    public boolean removeStudent(Course course, Student student) throws IOException {
        /**
         * remove a student to a course. It also updates in user database as well.
         */
        fi.initialize("courses");
        boolean removed = course.removeStudent(student);
        if(removed){
            student.removeCourse(course);
            fi.addEntry(course.getCourseCode() + course.getCourseType(), "enrolled students id", course.getEnrolledID());
            ud.addStudentUser(student);//update student's enrolled course in userdatabase.
            return true;
        }
        return false;
    }

    public Course getCourse(String courseCode) throws IOException {
        /**
         * get a course by course code.
         */
        fi.initialize("courses");
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
