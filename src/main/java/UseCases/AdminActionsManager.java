package UseCases;

import Entities.Course;
import Entities.InterestTag;
import Entities.Student;
import Entities.User;

import java.io.IOException;

public class AdminActionsManager extends UseCase{

    private UserDataManager userDataManager;
    private CourseDataManager courseDataManager;

    private TagDataManager tagDataManager;

    public AdminActionsManager(CourseDataManager courseDatabase, UserDataManager userDatabase, TagDataManager tagDataManager) {
        super(courseDatabase, userDatabase);
        this.userDataManager = userDatabase;
        this.courseDataManager = courseDatabase;
        this.tagDataManager = tagDataManager;
    }

    public boolean removeUser(String userID){
        if(!userExist(userID)){
            return false;
        }
        try {
            Student student = (Student) userDataManager.getUserByID(userID);
            removeStudentFromCourse(student, courseDataManager);
            if(!student.getTags().get(0).getName().equals("")){
                removeStudentFromTag(student, tagDataManager);
            }
            userDataManager.removeUser(userID);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void removeStudentFromCourse(Student student, CourseDataManager courseDataManager) throws IOException {
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
    private void removeStudentFromTag(Student student, TagDataManager tagDataManager) throws IOException{
        for(InterestTag t: student.getTags()){
            tagDataManager.removeStudent(t, student);
        }
    }

    public boolean userExist(String userID){
        return userDataManager.existByID(userID);
    }
}