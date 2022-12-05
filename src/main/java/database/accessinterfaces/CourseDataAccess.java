package database.accessinterfaces;

import entities.Course;
import entities.Student;

import java.io.IOException;
import java.util.ArrayList;

public interface CourseDataAccess {
    ArrayList<String> getCourseCodeList();
    void addCourse(Course course);

    void updateCourse(Course course);

    boolean addStudent(String courseCode, String courseType, Student student) throws IOException;

    boolean removeStudent(String courseCode, String courseType, Student student) throws IOException;

    Course getCourse(String courseCode, String courseType) throws IOException;

}
