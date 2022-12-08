package database.cloud;

import database.accessinterfaces.TagDataAccess;
import adapters.gateways.DatabaseInterface;
import model.entities.InterestTag;
import model.entities.Student;

import java.util.*;

public class CloudTagData implements TagDataAccess {
    private final DatabaseInterface fi;

    public CloudTagData(DatabaseInterface tb){
        this.fi = tb;
        this.fi.openCollection("tags");
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
        return new ArrayList<>(studentsSet);
    }

    @Override
    public void addStudent(InterestTag tag, Student student) {
        fi.openCollection("tags");
        ArrayList<String> students = getStudentList(tag);
        if(!students.contains(student.getUserID())){
            fi.openCollection("tags");
            fi.addEntry(tag.getName(), student.getUserID(), 1);
        }
    }

    @Override
    public void removeStudent(InterestTag tag, Student student) {
        fi.openCollection("tags");
        ArrayList<String> students = getStudentList(tag);
        if(students.contains(student.getUserID())){
            fi.openCollection("tags");
            fi.removeDocField(tag.getName(), student.getUserID());
        }
    }


}
