package UseCases;

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
            ArrayList<String> coursesList = ((Student) user).getEnrolledCourseCodes();
            ArrayList<String> lectureList = new ArrayList<>();
            ArrayList<String> tutorialList = new ArrayList<>();
            for(String course: coursesList){
                if (lectureList.contains(course)){
                    tutorialList.add(course);
                }
                else{
                    lectureList.add(course);
                }
            }
            StringBuilder courseString = new StringBuilder();
            if(coursesList.contains("")){
                return courseString.toString();
            }
            courseString.append("Lectures:\n");
            for(String lecture: lectureList){
                courseString.append(lecture);
                courseString.append(": ");
                courseString.append(cb.getCourse(lecture, "LEC").getCourseName());
                courseString.append("\n");
            }
            if(tutorialList.isEmpty()){
                return courseString.toString();
            }
            courseString.append("Tutorials:\n");
            for(String tutorial: tutorialList){
                courseString.append(tutorial);
                courseString.append(": ");
                courseString.append(cb.getCourse(tutorial, "TUT").getCourseName());
                courseString.append("\n");
            }
            return courseString.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
