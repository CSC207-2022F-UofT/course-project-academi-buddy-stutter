package UseCases;

import Entities.Course;
import Entities.InterestTag;
import Entities.Student;

import java.io.IOException;

public class AdminActionsManager extends UseCase{

    private UserDataCloud userDataManager;
    private CourseDataCloud courseDataManager;
    private TagDataCloud tagDataManager;

    public AdminActionsManager(CourseDataCloud courseDatabase, UserDataCloud userDatabase, TagDataCloud tagDataManager) {
        super(courseDatabase, userDatabase);
        this.userDataManager = userDatabase;
        this.courseDataManager = courseDatabase;
        this.tagDataManager = tagDataManager;
    }

    public boolean removeUser(String userID){
        System.out.println(userID);
        if(!userExist(userID)){
            return false;
        }
        try {
            Student student = (Student) userDataManager.getUserByID(userID);
            removeStudentFromCourse(student, courseDataManager);
            removeStudentFromTag(student, tagDataManager);
            userDataManager.removeUser(userID);
            System.out.println("true");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void removeStudentFromCourse(Student student, CourseDataCloud courseDataManager) throws IOException {
        for(String c: student.getEnrolledCourseCodes()){
            Course course = courseDataManager.getCourse(c, "LEC");
            courseDataManager.removeStudent(course.getCourseCode(), course.getCourseType(), student);
            student.removeCourse(course);
        }
        if(!student.getEnrolledCourseCodes().isEmpty()){
            for(String c: student.getEnrolledCourseCodes()){
                Course course = courseDataManager.getCourse(c, "TUT");
                courseDataManager.removeStudent(course.getCourseCode(), course.getCourseType(), student);
                student.removeCourse(course);
            }
        }
    }
    private void removeStudentFromTag(Student student, TagDataCloud tagDataManager) throws IOException{
        for(InterestTag t: student.getTags()){
            if(!t.getName().equals("")){
                tagDataManager.removeStudent(t, student);
            }
        }
    }

    public boolean userExist(String userID){
        return userDataManager.existByID(userID);
    }
}