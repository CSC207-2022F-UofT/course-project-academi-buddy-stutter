package UseCases;

import Entities.Course;
import Entities.Student;

import java.io.IOException;

public class AdminActionsManager extends UseCase{

    private UserDataManager userDataManager;
    private CourseDataManager courseDataManager;

    public AdminActionsManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
        this.userDataManager = userDatabase;
        this.courseDataManager = courseDatabase;
    }

    public boolean removeUser(String userID){
        try {
            Student student = (Student) userDataManager.getUserByID(userID);
            for(String c: student.getEnrolledCourseCodes()){
                Course course = courseDataManager.getCourse(c, "LEC");
                course.removeStudent(student);
                student.removeCourse(course);
            }
            if(!student.getEnrolledCourseCodes().isEmpty()){
                for(String c: student.getEnrolledCourseCodes()){
                    Course course = courseDataManager.getCourse(c, "TUT");
                    course.removeStudent(student);
                    student.removeCourse(course);
                }
            }
            userDataManager.removeUser(userID);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
