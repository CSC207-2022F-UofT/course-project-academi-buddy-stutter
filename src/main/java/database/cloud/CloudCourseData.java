package database.cloud;
import database.accessinterfaces.CourseDataAccess;
import gateways.DatabaseInterface;
import entities.Course;
import entities.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CloudCourseData implements CourseDataAccess {
    private DatabaseInterface fi;
    private CloudUserData ud;
    public CloudCourseData(DatabaseInterface cb, CloudUserData ud){
        this.fi = cb;
        this.fi.initialize("courses");
        this.ud = ud;
    }

    @Override
    public ArrayList<String> getCourseCodeList(){
        /**
         * get a list of course code that is currently documented in the database.
         * @return an Arraylist of course code.
         */
        fi.initialize("courses");
        return fi.getDocumentStringList();
    }

    @Override
    public void addCourse(Course course){
        /**
         * add a course to the database. Note that this will overwrite any course of the same course code in database.
         */
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
        fi.initialize("courses");
        String courseIdentifier = course.getCourseCode() + course.getCourseType();

        if(!courseExists(courseIdentifier)){
            addCourse(course);
        }
    }

    @Override
    public boolean addStudent(String courseCode, String courseType, Student student) throws IOException {
        /**
         * add a student to a course. It also updates in user database as well.
         */
        fi.initialize("courses");
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

    @Override
    public boolean removeStudent(String courseCode, String courseType, Student student) throws IOException {
        /**
         * remove a student to a course. It also updates in user database as well.
         */
        fi.initialize("courses");
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

    @Override
    public Course getCourse(String courseCode, String courseType) throws IOException {
        /**
         * get a lecture course by course code.
         */
        fi.initialize("courses");
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
        ArrayList<String> studentIDList = new ArrayList<>();
        studentIDList.addAll(sid);
        if(studentIDList.contains("")){
            studentIDList.remove("");
        }

        course.setEnrolledStudents(studentIDList);
        return course;
    }

    private boolean courseExists(String courseIdentifier){
        fi.initialize("courses");
        return this.getCourseCodeList().contains(courseIdentifier);
    }

}
