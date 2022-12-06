package usecaseio;

import entities.Student;
import database.accessinterfaces.CourseDataAccess;
import usecases.CourseMatchManager;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements CourseMatchIO for CourseMatchFrame
 */
public class CourseMatchIO {
    private CourseMatchManager courseMatchManager;

    public String self;
    private ArrayList<Student> matches = new ArrayList();
    public CourseMatchIO(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.self = userID;
        this.courseMatchManager = new CourseMatchManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * Finds student matches
     * @param min_numCommon minimum number of common sessions
     * @return a list of matched students
     * @throws IOException fails to find matching students
     */
    public ArrayList<Student> getMatches(int min_numCommon) throws IOException {
        this.matches = new ArrayList();
        Student stu = (Student) courseMatchManager.getUserByID(this.self);
        this.matches = this.courseMatchManager.getTopSameSessionStudents(stu, min_numCommon);
        return this.matches;
    }

    /**
     * Get select student ID by index
     * @param index which user id we want to get
     * @return user id of target index
     */
    public String getSelectedUserID(int index){
        return matches.get(index).getUserID();
    }

    /**
     * Find list of users by filtering labels
     * @param label labels we want to have commons with
     * @return a list of users that share common labels
     */
    private ArrayList<Student> filterByLabel(String label){
        return courseMatchManager.filterByLabel(this.matches, label);
    }

    /**
     * @return whether there is atleast a match or not
     */
    private boolean noMatches(){
        return matches.size() == 0;
    }

    /**
     * Match students by labels
     * @param label labels that users should have common with
     * @return a list of users that share the same labels
     */
    public ArrayList<Student> getLabeledMatches(String label){

        if(!noMatches()){
            return filterByLabel(label);
        }else{
            return new ArrayList<>();
        }
    }
}
