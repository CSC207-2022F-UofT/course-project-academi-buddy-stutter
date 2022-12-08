package database.cloud;
import database.accessinterfaces.CourseDataAccess;
import adapters.gateways.DatabaseInterface;
import model.entities.Course;
import model.entities.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CloudCourseData implements CourseDataAccess {
    private final DatabaseInterface fi;
    private final CloudUserData ud;
    public CloudCourseData(DatabaseInterface cb, CloudUserData ud){
        this.fi = cb;
        this.fi.openCollection("courses");
        this.ud = ud;
    }

    /**
     * get a list of course code that is currently documented in the database.
     * @return an Arraylist of course code.
     */
    @Override
    public ArrayList<String> getCourseCodeList(){
        fi.openCollection("courses");
        return fi.getDocumentStringList();
    }

    /**
     * add a course to the database. Note that this will overwrite any course of the same course code in database.
     */
    @Override
    public void addCourse(Course course){
        fi.openCollection("courses");
        String courseCode = course.getCourseCode() + course.getCourseType();
        fi.addEntry(courseCode, "session type", course.getCourseType());
        fi.addEntry(courseCode, "session number", course.getSessionNumber());
        fi.addEntry(courseCode, "session name", course.getCourseName());
        fi.addEntry(courseCode, "day of week", course.getDayOfWeek());
        fi.addEntry(courseCode, "start time", course.getStartTime());
        fi.addEntry(courseCode, "year", course.getYear());
        fi.addEntry(courseCode, "enrolled students id", course.getEnrolledIDList());
    }

    @Override
    public void updateCourse(Course course){
        fi.openCollection("courses");
        String courseIdentifier = course.getCourseCode() + course.getCourseType();

        if(!courseExists(courseIdentifier)){
            addCourse(course);
        }
    }

    /**
     * add a student to a course. It also updates in user database as well.
     */
    @Override
    public boolean addStudent(String courseCode, String courseType, Student student) throws IOException {
        fi.openCollection("courses");
        System.out.println(courseCode + courseType);
        Course course = this.getCourse(courseCode, courseType);

        boolean added = course.addStudent(student);
        if(added){
            student.addCourse(course);
            fi.addEntry(course.getCourseCode() + course.getCourseType(), "enrolled students id", course.getEnrolledIDList());
            ud.updateStudentCourses(student);//update student's enrolled course in userdatabase.
            return true;
        }
        return false;
    }

    /**
     * remove a student to a course. It also updates in user database as well.
     */
    @Override
    public boolean removeStudent(String courseCode, String courseType, Student student) throws IOException {
        fi.openCollection("courses");
        Course course = this.getCourse(courseCode, courseType);
        boolean removed = course.removeStudent(student);
        if(removed){
            student.removeCourse(course);
            fi.addEntry(course.getCourseCode() + course.getCourseType(), "enrolled students id", course.getEnrolledIDList());
            ud.updateStudentCourses(student);//update student's enrolled course in userdatabase.
            return true;
        }
        return false;
    }

    /**
     * get a lecture course by course code.
     */
    @Override
    public Course getCourse(String courseCode, String courseType) throws IOException {
        fi.openCollection("courses");
        System.out.println(courseCode + courseType);
        if(!courseExists(courseCode + courseType)){

            return null;
        }

        Map<String, Object> courseDetail = fi.getEntry(courseCode + courseType);
        Course course = new Course(courseCode, (String) courseDetail.get("session type"),
                (String) courseDetail.get("session number"), (String) courseDetail.get("session name"),
                (String) courseDetail.get("day of week"), (String) courseDetail.get("start time"),
                (String) courseDetail.get("year"));
        System.out.println(courseDetail);
        String sidString = courseDetail.get("enrolled students id").toString();
        List<String> sid = Arrays.asList(sidString.substring(1, sidString.length() - 1).split(", "));
        ArrayList<String> studentIDList = new ArrayList<>(sid);
        studentIDList.remove("");

        course.setEnrolledStudents(studentIDList);
        return course;
    }

    private boolean courseExists(String courseIdentifier){
        fi.openCollection("courses");
        return this.getCourseCodeList().contains(courseIdentifier);
    }

}
