package UIController;

import Entities.User;
import Entities.Student;
import UseCases.CourseDataManager;
import UseCases.CourseMatchManager;
import UseCases.UserDataManager;

import java.io.IOException;
import java.util.ArrayList;

public class MatchUIControl {
    private CourseMatchManager courseMatchManager;
    private UserDataManager userDataManager;
    public Student self;
    private ArrayList<String> matches = new ArrayList();
    public MatchUIControl(User self, CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.self = (Student)self;
        this.courseMatchManager = new CourseMatchManager(courseDatabase, userDatabase);
        this.userDataManager = userDatabase;
    }


    public ArrayList<String> getMatches(int numCommon, int topNum){
        this.matches = this.courseMatchManager.getTopSameSessionStudents(this.self, numCommon, topNum);
        return this.matches;
    }

    public String getSelectedUserID(int index){
        return matches.get(index);
    }
    public String getFullName(String userID){
        try {
            return userDataManager.getUserByID(userID).getFullName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
