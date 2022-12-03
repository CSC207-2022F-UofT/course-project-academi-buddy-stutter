package UseCases;

import Gateways.DatabaseInterface;
import Entities.InterestTag;
import Entities.Student;

import java.io.IOException;
import java.util.*;

public class TagDataManager {
    private DatabaseInterface fi;
    private UserDataManager ud;

    public TagDataManager(DatabaseInterface tb, UserDataManager ud){
        this.fi = tb;
        this.fi.initialize("tags");
        this.ud = ud;
    }

    public ArrayList<String> getTagNameList(){
        fi.initialize("tags");
        return fi.getDocumentStringList();
    }


    public ArrayList<String> getStudentList(InterestTag tag){
        fi.initialize("tags");
        String tagName = tag.getName();
        Map<String, Object> tagEntry = fi.getEntry(tagName);
        Set<String> studentsSet = tagEntry.keySet();
        ArrayList<String> students = new ArrayList<>();
        students.addAll(studentsSet);
        return students;
    }

    public void addStudent(InterestTag tag, Student student) throws IOException {
        fi.initialize("tags");
        ArrayList<String> students = getStudentList(tag);
        if(!students.contains(student.getUserID())){
            fi.initialize("tags");
            fi.addEntry(tag.getName(), student.getUserID(), 1);
        }
    }

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
