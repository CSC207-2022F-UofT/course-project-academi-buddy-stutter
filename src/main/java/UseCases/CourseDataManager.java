package UseCases;
import Gateways.DatabaseInterface;
import Entities.Course;
import Entities.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Use case of operations on the Course Database.
 */
public class CourseDataManager {
    private DatabaseInterface fi;
    private UserDataManager ud;
    private final String COURSES = "courses";

    /**
     * Initializer
     * @param cb The Database Interface.
     * @param ud The User Data Manager.
     */
    public CourseDataManager(DatabaseInterface cb, UserDataManager ud){
        this.fi = cb;
        this.fi.initialize(COURSES);
        this.ud = ud;
    }

    /**
     * Get a list of course code that is currently documented in the database.
     * @return an Arraylist of course code.
     */
    public ArrayList<String> getCourseCodeList(){
        fi.initialize(COURSES);
        return fi.getDocumentStringList();
    }

    /**
     * Add a course to the database. Note that this will overwrite any course of the same course code in database.
     * @param course the course that is going to be added.
     */
    private void addCourse(Course course){
        String courseCode = course.getCourseCode() + course.getCourseType();
        fi.addEntry(courseCode, "session type", course.getCourseType());
        fi.addEntry(courseCode, "session number", course.getSessionNumber());
        fi.addEntry(courseCode, "session name", course.getCourseName());
        fi.addEntry(courseCode, "day of week", course.getDayOfWeek());
        fi.addEntry(courseCode, "start time", course.getStartTime());
        fi.addEntry(courseCode, "year", course.getYear());
        fi.addEntry(courseCode, "enrolled students id", course.getEnrolledIDList());
    }

    /**
     * Updates the course
     * @param course the course to update
     */
    public void updateCourse(Course course){
        fi.initialize(COURSES);
        String courseIdentifier = course.getCourseCode() + course.getCourseType();

        if(!courseExists(courseIdentifier)){
            addCourse(course);
        }
    }

    /**
     * Add a student to a course. Also update user database as well.
     * @param courseCode The String of the Course Code that the student will be added in.
     * @param courseType The String of the Course Type that the student will be added in.
     * @param student The student that will be added to the course.
     * @return True if the student is added, false otherwise.
     */
    public boolean addStudent(String courseCode, String courseType, Student student) throws IOException {
        fi.initialize(COURSES);
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
     * Remove a student from a course. Updates it user database.
     * @param courseCode The String of course code that the student will be removed from.
     * @param courseType The String of course type that the student will be removed from.
     * @param student The student that will be removed from the courses.
     * @return True if student is removed from courses, false otherwise.
     */
    public boolean removeStudent(String courseCode, String courseType, Student student) throws IOException {
        fi.initialize(COURSES);
        Course course = this.getCourse(courseCode, courseType);
        boolean removed = course.removeStudent(student);
        if(removed){
            student.removeCourse(course);
            fi.addEntry(course.getCourseCode() + course.getCourseType(), "enrolled students id", course.getEnrolledIDList());
            ud.updateStudentCourses(student);//update student's enrolled course in userDatabase.
            return true;
        }
        return false;
    }

    /**
     * Get a lecture course by course code.
     * @param courseCode A course code String.
     * @param courseType A course type String.
     * @return null if course does not exist, course with its students otherwise.
     */
    public Course getCourse(String courseCode, String courseType) throws IOException {
        fi.initialize(COURSES);
        System.out.println(courseCode + courseType);
        if(!courseExists(courseCode + courseType)){

            return null;
        }

        Map<String, Object> courseDetail = fi.getEntry(courseCode + courseType);
        Course course = new Course(courseCode, (String) courseDetail.get("session type"),
                (String) courseDetail.get("session number"), (String) courseDetail.get("session name"),
                (String) courseDetail.get("day of week"), (String) courseDetail.get("start time"),
                (String) courseDetail.get("year"));
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

    /**
     * Check if course exists in course code list.
     * @param courseIdentifier The String to identify the course
     * @return True if the course exists in course code list, false otherwise.
     */
    private boolean courseExists(String courseIdentifier){
        fi.initialize(COURSES);
        return this.getCourseCodeList().contains(courseIdentifier);
    }

}
