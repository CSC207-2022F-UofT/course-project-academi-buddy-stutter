package UIController;

import Entities.User;
import Entities.Student;
import UseCases.CourseDataManager;
import UseCases.CourseMatchManager;
import UseCases.UserDataManager;

import java.io.IOException;
import java.util.ArrayList;

public class CourseMatchUIControl {
    private CourseMatchManager courseMatchManager;

    public Student self;
    private ArrayList<Student> matches = new ArrayList();
    public CourseMatchUIControl(User self, CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.self = (Student)self;
        this.courseMatchManager = new CourseMatchManager(courseDatabase, userDatabase);
    }


    public ArrayList<Student> getMatches(int min_numCommon) throws IOException {
        this.matches = new ArrayList();
        this.matches = this.courseMatchManager.getTopSameSessionStudents(this.self, min_numCommon);
        return this.matches;
    }

    public String getSelectedUserID(int index){
        return matches.get(index).getUserID();
    }

    private ArrayList<Student> filterByLabel(String label){
        return courseMatchManager.filterByLabel(this.matches, label);
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
