package database.cloud;

import database.accessinterfaces.TagDataAccess;
import adapters.gateways.DatabaseInterface;
import entities.InterestTag;
import entities.Student;

import java.io.IOException;
import java.util.*;

public class CloudTagData implements TagDataAccess {
    private DatabaseInterface fi;
    private CloudUserData ud;

    public CloudTagData(DatabaseInterface tb, CloudUserData ud){
        this.fi = tb;
        this.fi.openCollection("tags");
        this.ud = ud;
    }

    @Override
    public ArrayList<String> getTagNameList(){
        fi.openCollection("tags");
        return fi.getDocumentStringList();
    }

    @Override
    public ArrayList<String> getStudentList(InterestTag tag){
        fi.openCollection("tags");
        String tagName = tag.getName();
        Map<String, Object> tagEntry = fi.getEntry(tagName);
        Set<String> studentsSet = tagEntry.keySet();
        ArrayList<String> students = new ArrayList<>();
        students.addAll(studentsSet);
        return students;
    }

    @Override
    public void addStudent(InterestTag tag, Student student) throws IOException {
        fi.openCollection("tags");
        ArrayList<String> students = getStudentList(tag);
        if(!students.contains(student.getUserID())){
            fi.openCollection("tags");
            fi.addEntry(tag.getName(), student.getUserID(), 1);
        }
    }

    @Override
    public boolean removeStudent(InterestTag tag, Student student) throws IOException {
        fi.openCollection("tags");
        ArrayList<String> students = getStudentList(tag);
        if(students.contains(student.getUserID())){
            fi.openCollection("tags");
            fi.removeDocField(tag.getName(), student.getUserID());
            return true;
        }
        return false;
    }


}
