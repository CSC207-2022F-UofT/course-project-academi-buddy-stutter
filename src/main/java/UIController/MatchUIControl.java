package UIController;

import Entities.User;
import Entities.Student;
import UseCases.CourseDataManager;
import UseCases.CourseMatchManager;
import UseCases.CourseMatchManager2;
import UseCases.UserDataManager;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.util.ArrayList;

public class MatchUIControl {
    private CourseMatchManager courseMatchManager;
    private CourseMatchManager2 courseMatchManager2;

    public Student self;
    private ArrayList<Student> matches = new ArrayList();
    public MatchUIControl(User self, CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.self = (Student)self;
        this.courseMatchManager2 = new CourseMatchManager2(courseDatabase, userDatabase);
    }


    public ArrayList<Student> getMatches(int min_numCommon) throws IOException {
        this.matches = this.courseMatchManager2.getTopSameSessionStudents(this.self, min_numCommon);
        return this.matches;
    }

    public String getSelectedUserID(int index){
        return matches.get(index).getUserID();
    }

    private ArrayList<Student> filterByLabel(String label){
        return courseMatchManager2.filterByLabel(this.matches, label);
    }

    private boolean noMatches(){
        return matches.size() == 0;
    }

    public ArrayList<Student> getLabeledMatches(String label){

        if(!noMatches()){
            return filterByLabel(label);
        }else{
            return new ArrayList<>();
        }
    }


}
