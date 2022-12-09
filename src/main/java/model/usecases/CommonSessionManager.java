package model.usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.util.ArrayList;

/**
 * Use case operations on common sessions.
 */
public class CommonSessionManager extends UseCase{

    private int numberOfCommonSessions;

    /**
     * Initiator
     * @param courseDatabase the course database
     * @param userDatabase the user database
     * @param tagDatabase the tag database
     */
    public CommonSessionManager(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess tagDatabase) {
        super(courseDatabase, userDatabase, tagDatabase);
    }

    /**
     * Getting the name of the user given id
     * @param targetUserID the id of the target
     * @return the name of the user with the id
     */
    public String getName(String targetUserID){
        return this.ub.getUserByID(targetUserID).getFullName();
    }


    /**
     * Getting common session between user and target.
     * @param selfUserID the user's id
     * @param targetUserID the target's id
     * @return The String of common sessions
     */

    public String getCommonSessions(String selfUserID, String targetUserID){
        ArrayList<String> commonCourseCode = this.ub.getCommonSessionCode(selfUserID, targetUserID);
        StringBuilder courseString = new StringBuilder();
        numberOfCommonSessions = commonCourseCode.size();
        ArrayList<String> lectureList = new ArrayList<>();
        ArrayList<String> tutorialList = new ArrayList<>();
        for(String course: commonCourseCode){
            if (lectureList.contains(course)){
                tutorialList.add(course);
            }
            else{
                lectureList.add(course);
            }
        }
        if(lectureList.isEmpty()){
            return courseString.toString();
        }
        courseString.append("Lectures:\n");
        for(String lecture: lectureList){
            System.out.println(lecture);
            if(this.cb.getCourse(lecture, "LEC") == null){
                tutorialList.add(lecture);
            }
            else{
                courseString.append(lecture);
                courseString.append(": ");
                courseString.append(this.cb.getCourse(lecture, "LEC").getCourseName());
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
            courseString.append(this.cb.getCourse(tutorial, "TUT").getCourseName());
            courseString.append("\n");
        }
        courseString.deleteCharAt(courseString.length() - 1);
        return courseString.toString();
    }

    /**
     * Getting the number of common sessions
     * @return the number of common sessions.
     */
    public int getNumberOfCommonSessions(){
        return numberOfCommonSessions;
    }
}