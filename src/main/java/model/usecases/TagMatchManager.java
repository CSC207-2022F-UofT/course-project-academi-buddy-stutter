package model.usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.entities.InterestTag;
import model.entities.Student;
import model.entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Matches students based on their selected tags.
 */
public class TagMatchManager extends UseCase{

    private InterestTag selectedTag;

    private ArrayList<String> idList = new ArrayList<>();


    /**
     * Initializer.
     * @param courseDatabase The course database.
     * @param userDatabase The user database.
     * @param tagDatabase The tag manager.
     */
    public TagMatchManager(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess tagDatabase) {
        super(courseDatabase, userDatabase, tagDatabase);
    }

    /**
     * Set the tag as selected.
     * @param selected The interest tag to be selected.
     */
    public void setSelectedTag(String selected){
        InterestTag selectedTag = new InterestTag(selected);
        this.selectedTag = selectedTag;
        idList = new ArrayList<>();
    }

    /**
     * Match students by selected tags.
     * @return An ArrayList of students that are matched.
     */
    public ArrayList<Student> match(){
        List<String> idList = tb.getStudentList(selectedTag);
        ArrayList<Student> matchedStudents = new ArrayList<>();
        for(String id: idList){
            Student student;
            student = (Student) this.getUserByID(id);
            matchedStudents.add(student);
        }
        return matchedStudents;
    }

    /**
     * Get a List of Strings of student names that are matched.
     * @param self The current user.
     * @return A List of Strings of other usernames matched.
     */
    public List<String> getStudentName(String selfID) throws IOException {
        User self = this.getUserByID(selfID);
        List<String> nameList = new ArrayList<>();
        ArrayList<Student> students = match();
        if(students.contains(null)){
            return nameList;
        }
        for(Student s: students){
            if(s.getUserID() != null && !s.getUserID().equals(self.getUserID())) {
                idList.add(s.getUserID());
                nameList.add(s.getFullName());
            }
        }
        return nameList;
    }

    /**
     * Getting the student id in idList given a certain index.
     * @param index The index to search for.
     * @return A id String in the id list that matches the index.
     */
    public String getStudentIDByIndex(int index){
        return idList.get(index);
    }



}