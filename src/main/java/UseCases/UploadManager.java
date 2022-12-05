package UseCases;
import Entities.Course;
import Entities.Student;
import Gateways.CalendarInterface;
import Gateways.UploaderInterface;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Use case operations for uploading a calendar.
 */
public class UploadManager extends UseCase {
    UploaderInterface uploaderInterface;
    CalendarInterface calendarInterface;

    /**
     * Initializer.
     * @param courseDatabase The course database.
     * @param userDatabase The user database.
     * @param uploaderInterface The upload interface.
     * @param calendarInterface The calendar interface.
     */
    public UploadManager(CourseDataManager courseDatabase, UserDataManager userDatabase,
                         TagDataManager tagDatabase,
                         UploaderInterface uploaderInterface,
                         CalendarInterface calendarInterface){
        super(courseDatabase, userDatabase, tagDatabase);
        this.uploaderInterface = uploaderInterface;
        this.calendarInterface = calendarInterface;
    }

    /**
     * Uploading a file.
     * @return true if uploaded.
     */
    public boolean upload(){
        return uploaderInterface.uploadFile();
    }

    /**
     * Copy the file path.
     */
    public void copyFileToPath() throws IOException {
        this.uploaderInterface.copyFileToPath();
    }

    /**
     * Updating the database by adding corresponding courses and students.
     * @param student The student that is being updated.
     */
    public void updateDatabase(Student student) throws IOException {
        CalendarInterpreter ci = new CalendarInterpreter(this.calendarInterface);
        ArrayList<Course> courses = ci.getCourses("coursesCalendar.ics");
        for(Course c: courses){
            this.cb.updateCourse(c);
            String courseCode = c.getCourseCode();
            String courseType = c.getCourseType();
            this.cb.addStudent(courseCode, courseType, student);
        }
    }

    /**
     * Takes out the original database info and replaces it with new ones.
     * @param student The student to operate on.
     */
    public void reuploadUpdateDatabase(Student student) throws IOException {

        for(String c: student.getEnrolledCourseCodes()){
            Course course = this.cb.getCourse(c, "LEC");
            this.cb.removeStudent(course.getCourseCode(), course.getCourseType(), student);
            student.removeCourse(course);
        }
        if(!student.getEnrolledCourseCodes().isEmpty()){
            for(String c: student.getEnrolledCourseCodes()){
                Course course = this.cb.getCourse(c, "TUT");
                this.cb.removeStudent(course.getCourseCode(), course.getCourseType(), student);
                student.removeCourse(course);
            }
        }

        updateDatabase(student);
    }

}