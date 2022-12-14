package model.usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.entities.Student;
import model.entities.User;

import java.util.ArrayList;

public class ProfileManager extends UseCase{

    /**
     * Use case for operations managing profiles in the databases.
     * @param courseDatabase The course database.
     * @param userDatabase The user database.
     */
    public ProfileManager(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess tagDatabase) {
        super(courseDatabase, userDatabase, tagDatabase);
    }

    /**
     * Get the name of user given userID.
     * @param userID The id of the user.
     * @return The String name of the user.
     */
    public String getName(String userID){
        User user = this.getUserByID(userID);
        return user.getFullName();
    }

    /**
     * Get the email of the user given userID.
     * @param userID The id of the user.
     * @return The String of the user's email.
     */
    public String getUserEmail(String userID){
        User user = this.getUserByID(userID);
        return ((Student) user).getEmail();
    }

    /**
     * Get the info of the user given userID.
     * @param userID The id of the user.
     * @return The String of the user's info.
     */
    public String getUserInfo(String userID){
        User user = this.getUserByID(userID);
        return user.getUserInfo();
    }

    /**
     * Get the Course of the user given userID.
     * @param userID The id of the user.
     * @return The String of courses of the student.
     */
    public String getCourseString(String userID){
        User user = this.getUserByID(userID);
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
        if(lectureList.isEmpty()){
            return courseString.toString();
        }
        courseString.append("Lectures:\n");
        for(String lecture: lectureList){
            System.out.println(lecture);
            if(cb.getCourse(lecture, "LEC") == null){
                tutorialList.add(lecture);
            }
            else{
                courseString.append(lecture);
                courseString.append(": ");
                courseString.append(cb.getCourse(lecture, "LEC").getCourseName());
                courseString.append("\n");
            }
        }
        if(tutorialList.isEmpty()){
            return courseString.toString();
        }
        courseString.append("\n");
        courseString.append("Tutorials:\n");
        for(String tutorial: tutorialList){
            courseString.append(tutorial);
            courseString.append(": ");
            courseString.append(cb.getCourse(tutorial, "TUT").getCourseName());
            courseString.append("\n");
        }
        courseString.deleteCharAt(courseString.length() - 1);
        return courseString.toString();
    }

    /**
     * Updating the email of the user in their profile.
     * @param userID The id of the user.
     * @param email the new email that is being updated.
     */
    public void updateEmail(String userID, String email){
        Student student = (Student) this.getUserByID(userID);
        student.setEmail(email);
        ub.addStudentUser(student);
    }

    /**
     * Updating the info of a user in their profile.
     * @param userID The id of the user.
     * @param info The new information that is being updated.
     */
    public void updateInfo(String userID, String info){
        Student student = (Student) this.getUserByID(userID);
        student.setUserInfo(info);
        ub.addStudentUser(student);
    }

}