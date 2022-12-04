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
    CourseDataManager courseDataManager;

    /**
     * Initializer.
     * @param courseDatabase The course database.
     * @param userDatabase The user database.
     * @param uploaderInterface The upload interface.
     * @param calendarInterface The calendar interface.
     */
    public UploadManager(CourseDataManager courseDatabase, UserDataManager userDatabase,
                         UploaderInterface uploaderInterface,
                         CalendarInterface calendarInterface){
        super(courseDatabase, userDatabase);
        this.uploaderInterface = uploaderInterface;
        this.calendarInterface = calendarInterface;
        this.courseDataManager = courseDatabase;
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
        ArrayList<Course> courses = ci.getCourses(ci.readCalendar("coursesCalendar.ics"));
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
        AdminActionsManager.removeStudentFromCourse(student, courseDataManager);
        updateDatabase(student);
    }

}