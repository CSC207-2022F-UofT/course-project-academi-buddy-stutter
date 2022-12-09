package database.accessinterfaces;

import model.entities.Course;
import model.entities.Student;

import java.util.ArrayList;

public interface CourseDataAccess {
    ArrayList<String> getCourseCodeList();
    void addCourse(Course course);

    void updateCourse(Course course);

    void addStudent(String courseCode, String courseType, Student student);

    void removeStudent(String courseCode, String courseType, Student student);

    Course getCourse(String courseCode, String courseType);

}
