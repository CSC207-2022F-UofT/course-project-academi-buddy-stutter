package database.local;

import database.accessinterfaces.TagDataAccess;
import model.entities.InterestTag;
import model.entities.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LocalTagData implements TagDataAccess {

    public final HashMap<String, HashMap> allTags;

    public LocalTagData(){
        allTags = new HashMap<>();
        allTags.put("Adventure", new HashMap<>());
        allTags.put("Beer", new HashMap<>());
        allTags.put("Cat", new HashMap<>());
        allTags.put("Music", new HashMap<>());
        allTags.put("Outdoors", new HashMap<>());
        allTags.put("Books", new HashMap<>());
        allTags.put("Photography", new HashMap<>());
        allTags.put("Video Games", new HashMap<>());
        allTags.put("Movies", new HashMap<>());
    }

    @Override
    public ArrayList<String> getTagNameList() {
        return new ArrayList<>(allTags.keySet());
    }

    @Override
    public ArrayList<String> getStudentList(InterestTag tag) {
        Set<String> studentIDList = allTags.get(tag.getName()).keySet();
        return new ArrayList<>(studentIDList);
    }

    @Override
    public void addStudent(InterestTag tag, Student student) {
        allTags.get(tag.getName()).put(student.getUserID(), 1);

    }

    @Override
    public void removeStudent(InterestTag tag, Student student) {
        if(!allTags.get(tag.getName()).containsKey(student.getUserID())){
            return;
        }
        allTags.get(tag.getName()).remove(student.getUserID(), 1);
    }
}
