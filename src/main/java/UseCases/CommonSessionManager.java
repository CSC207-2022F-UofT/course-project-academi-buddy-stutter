package UseCases;

import java.io.IOException;
import java.util.ArrayList;

public class CommonSessionManager extends UseCase{

    private int numberOfCommonSessions;

    public CommonSessionManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public String getName(String targetUserID){
        try {
            return this.ub.getUserByID(targetUserID).getFullName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCommonSessions(String selfUserID, String targetUserID){
        ArrayList<String> commonCourseCode = new ArrayList<>();
        StringBuilder courseString = new StringBuilder();
        try {
            commonCourseCode = this.ub.getCommonSessionCode(selfUserID, targetUserID);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumberOfCommonSessions(){
        return numberOfCommonSessions;
    }
}