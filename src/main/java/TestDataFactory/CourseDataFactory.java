package TestDataFactory;

import Entities.Course;
import Entities.Student;
import UseCases.CloudCourseData;
import UseCases.CloudTagData;
import UseCases.CloudUserData;

import java.io.IOException;

public class CourseDataFactory extends DataFactory{
    public CourseDataFactory(CloudCourseData courseDataManager, CloudUserData userDataManager, CloudTagData tagDataManager){
        super(courseDataManager, userDataManager, tagDataManager);
    }

    public Course createCourse(String courseCode, String courseType, String sessionNumber, String courseName, String day, String startTime, String year){
        Course course = new Course(courseCode, courseType, sessionNumber, courseName, day, startTime, year);
        courseDataManager.updateCourse(course);
        return course;
    }

    public void addStudent(Course course, Student student){
        try {
            String courseCode = course.getCourseCode();
            String courseType = course.getCourseType();
            courseDataManager.addStudent(courseCode, courseType, student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
