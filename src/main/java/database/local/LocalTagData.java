package database.local;

import database.accessinterfaces.TagDataAccess;
import entities.InterestTag;
import entities.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LocalTagData implements TagDataAccess {

    private LocalUserData userDataLocal;

    public HashMap<String, HashMap> allTags;

    public LocalTagData(LocalUserData userDataLocal){
        this.userDataLocal = userDataLocal;
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
        ArrayList<String> tagNameList = new ArrayList<>();
        for (String tagName: allTags.keySet()){
            tagNameList.add(tagName);
        }
        return tagNameList;
    }

    @Override
    public ArrayList<String> getStudentList(InterestTag tag) {
        ArrayList<String> studentIDArrayList = new ArrayList<>();
        Set<String> studentIDList = allTags.get(tag.getName()).keySet();
        for (String s: studentIDList){
            studentIDArrayList.add(s);
        }
        return studentIDArrayList;
    }

    @Override
    public void addStudent(InterestTag tag, Student student) throws IOException {
        allTags.get(tag.getName()).put(student.getUserID(), 1);

    }

    @Override
    public boolean removeStudent(InterestTag tag, Student student) throws IOException {
        System.out.println(getStudentList(tag));
        System.out.println(tag.getName() + student.getUserID());
        if(!allTags.get(tag.getName()).containsKey(student.getUserID())){
            return false;
        }
        allTags.get(tag.getName()).remove(student.getUserID(), 1);
        return true;
    }
}