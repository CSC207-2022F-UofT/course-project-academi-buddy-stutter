package UseCases;

import Entities.InterestTag;
import Entities.Student;
import Entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Matches students based on their selected tags.
 */
public class TagMatchManager extends UseCase{

    private InterestTag selectedTag;
    private TagDataManager tagManager;

    private ArrayList<String> idList = new ArrayList<>();


    /**
     * Initializer.
     * @param courseDatabase The course database.
     * @param userDatabase The user database.
     * @param tagManager The tag manager.
     */
    public TagMatchManager(CourseDataManager courseDatabase, UserDataManager userDatabase, TagDataManager tagManager) {
        super(courseDatabase, userDatabase);
        this.tagManager = tagManager;
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
        List<String> idList = tagManager.getStudentList(selectedTag);
        ArrayList<Student> matchedStudents = new ArrayList<>();
        for(String id: idList){
            Student student;
            try {
                student = (Student) ub.getUserByID(id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matchedStudents.add(student);
        }
        return matchedStudents;
    }

    /**
     * Get a List of Strings of student names that are matched.
     * @param self The current user.
     * @return A List of Strings of other usernames matched.
     */
    public List<String> getStudentName(User self){
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