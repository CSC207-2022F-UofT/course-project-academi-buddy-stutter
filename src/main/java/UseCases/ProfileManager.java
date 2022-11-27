package UseCases;

import Entities.Course;
import Entities.Student;
import Entities.User;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileManager extends UseCase{

    public ProfileManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public String getName(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            return user.getFullName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserEmail(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            return ((Student) user).getEmail();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserInfo(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            return user.getUserInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCourseString(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            ArrayList<Course> coursesList = ((Student) user).getEnrolledCourses();
            StringBuilder courseString = new StringBuilder();
            if(coursesList.contains(null)){
                return courseString.toString();
            }
            for(Course course: coursesList){
                courseString.append(course.getCourseCode());
                courseString.append(": ");
                courseString.append(course.getCourseName());
                courseString.append("\n");
            }
            return courseString.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
