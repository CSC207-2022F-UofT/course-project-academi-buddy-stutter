package UseCases;
import Entities.Course;
import Entities.Student;
import External.BiweeklyAPI;
import Gateways.CalendarInterface;
import Gateways.DatabaseInterface;
import Gateways.UploaderInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


public class UploadManager extends UseCase {
    UploaderInterface uploaderInterface;
    CalendarInterface calendarInterface;
    CourseDataManager courseDataManager;
    public UploadManager(CourseDataManager courseDatabase, UserDataManager userDatabase,
                         UploaderInterface uploaderInterface,
                         CalendarInterface calendarInterface){
        super(courseDatabase, userDatabase);
        this.uploaderInterface = uploaderInterface;
        this.calendarInterface = calendarInterface;
        this.courseDataManager = courseDatabase;
    }

    public boolean upload(){
        return uploaderInterface.uploadFile();
    }

    public void copyFileToPath() throws IOException {
        this.uploaderInterface.copyFileToPath();
    }

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

    public void reuploadUpdateDatabase(Student student) throws IOException {
        AdminActionsManager.removeStudentFromCourse(student, courseDataManager);
        updateDatabase(student);
    }

}