package UseCases;

import Gateways.DatabaseInterface;
import Entities.InterestTag;
import Entities.Student;

import java.io.IOException;
import java.util.*;

/**
 * User case operations for tag data.
 */
public class TagDataManager {
    private DatabaseInterface fi;
    private UserDataManager ud;

    /**
     * initializer.
     * @param tb database interface.
     * @param ud user database.
     */
    public TagDataManager(DatabaseInterface tb, UserDataManager ud){
        this.fi = tb;
        this.fi.initialize("tags");
        this.ud = ud;
    }

    public ArrayList<String> getTagNameList(){
        fi.initialize("tags");
        return fi.getDocumentStringList();
    }

    /**
     * Get the students for selected tags.
     * @param tag The tag to filter by.
     * @return An ArrayList of Strings of students based on the tags.
     */
    public ArrayList<String> getStudentList(InterestTag tag){
        fi.initialize("tags");
        String tagName = tag.getName();
        Map<String, Object> tagEntry = fi.getEntry(tagName);
        Set<String> studentsSet = tagEntry.keySet();
        ArrayList<String> students = new ArrayList<>();
        students.addAll(studentsSet);
        return students;
    }

    /**
     * To add students in the tag group.
     * @param tag The selected tag group.
     * @param student The student to add.
     */
    public void addStudent(InterestTag tag, Student student) throws IOException {
        fi.initialize("tags");
        ArrayList<String> students = getStudentList(tag);
        if(!students.contains(student.getUserID())){
            fi.initialize("tags");
            fi.addEntry(tag.getName(), student.getUserID(), 1);
        }
    }

    /**
     * Remove selected student from tag group.
     * @param tag The selected tag group.
     * @param student The seleceted student to remove.
     * @return True if the student is removed from the tag group, false otherwise.
     * @throws IOException
     */
    public boolean removeStudent(InterestTag tag, Student student) throws IOException {
        fi.initialize("tags");
        ArrayList<String> students = getStudentList(tag);
        if(students.contains(student.getUserID())){
            fi.initialize("tags");
            fi.removeDocField(tag.getName(), student.getUserID());
            return true;
        }
        return false;
    }


}
