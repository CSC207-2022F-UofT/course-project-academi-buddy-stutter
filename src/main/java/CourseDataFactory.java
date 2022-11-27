import Entities.Course;
import Entities.Student;
import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;

import java.io.IOException;
import java.util.ArrayList;

public class CourseDataFactory extends DataFactory{
    public CourseDataFactory(CourseDataManager courseDataManager, UserDataManager userDataManager, TagDataManager tagDataManager){
        super(courseDataManager, userDataManager, tagDataManager);
    }

    public Course createCourse(String courseCode, String courseType, String sessionNumber, String courseName, String day, String startTime, String year){
        Course course = new Course(courseCode, courseType, sessionNumber, courseName, day, startTime, year);
        try {
            courseDataManager.addCourse(course);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    public void addStudent(Course course, Student student){
        try {
            courseDataManager.addStudent(course, student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
