package database.local;

import database.accessinterfaces.CourseDataAccess;
import model.entities.Course;
import model.entities.Student;

import java.io.IOException;
import java.util.ArrayList;

public class LocalCourseData implements CourseDataAccess {

    private final LocalUserData userDataLocal;

    private ArrayList<Course> allCourses;

    public LocalCourseData(LocalUserData userDataLocal){
        this.userDataLocal = userDataLocal;
        this.allCourses = new ArrayList<>();
    }

    @Override
    public ArrayList<String> getCourseCodeList() {
        ArrayList<String> courseCodeList = new ArrayList<>();
        System.out.println(allCourses);
        for(Course c: allCourses){
            courseCodeList.add(c.getCourseCode() + c.getCourseType());
        }
        return courseCodeList;
    }

    @Override
    public void addCourse(Course course) {
        allCourses.add(course);
    }

    @Override
    public void updateCourse(Course course) {
        String courseIdentifier = course.getCourseCode() + course.getCourseType();
        if(!getCourseCodeList().contains(courseIdentifier)){
            addCourse(course);
        }
    }

    @Override
    public boolean addStudent(String courseCode, String courseType, Student student) throws IOException {
        Course course = getCourse(courseCode, courseType);
        boolean added = course.addStudent(student);
        if(added){
            student.addCourse(course);
            ArrayList<Course> newCourseList = new ArrayList<>();
            for(Course c: allCourses){
                if (!(c.getCourseCode().equals(courseCode) && c.getCourseType().equals(courseType))){
                    newCourseList.add(c);
                }
                else{
                    newCourseList.add(course);
                }
            }
            userDataLocal.updateStudentCourses(student);
            allCourses = newCourseList;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeStudent(String courseCode, String courseType, Student student) throws IOException {
        Course course = this.getCourse(courseCode, courseType);
        boolean removed = course.removeStudent(student);
        if(removed){
            student.removeCourse(course);
            ArrayList<Course> newCourseList = new ArrayList<>();
            for(Course c: allCourses){
                if (!(c.getCourseCode().equals(courseCode) && c.getCourseType().equals(courseType))){
                    newCourseList.add(c);
                }
                else{
                    newCourseList.add(course);
                }
            }
            userDataLocal.updateStudentCourses(student);
            allCourses = newCourseList;
            return true;
        }
        return false;
    }

    @Override
    public Course getCourse(String courseCode, String courseType) throws IOException {
        for(Course c: allCourses){
            if (c.getCourseCode().equals(courseCode) && c.getCourseType().equals(courseType)){
                return c;
            }
        }
        return null;
    }
}
