package UIController;

import Entities.User;
import Entities.Student;
import UseCases.CourseDataManager;
import UseCases.CourseMatchManager;
import UseCases.UserDataManager;

import java.util.ArrayList;

public class MatchUIControl {
    private CourseMatchManager courseMatchManager;
    public Student self;
    private ArrayList<Student> matches = new ArrayList();
    public MatchUIControl(User self, CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.self = (Student)self;
        this.courseMatchManager = new CourseMatchManager(courseDatabase, userDatabase);
    }


    public ArrayList<Student> getMatches(int numCommon, int topNum){
        this.matches = this.courseMatchManager.getTopSameSessionStudents(this.self, numCommon, topNum);
        return this.matches;
    }

    public String getSelectedUserID(int index){
        return matches.get(index).getUserID();
    }

}
