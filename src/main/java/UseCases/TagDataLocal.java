package UseCases;

import Entities.InterestTag;
import Entities.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class TagDataLocal implements TagDataManager{

    private UserDataLocal userDataLocal;

    private HashMap<String, HashMap> allTags;

    public TagDataLocal(UserDataLocal userDataLocal){
        this.userDataLocal = userDataLocal;
        allTags = new HashMap<>();
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
        if(!allTags.get(tag.getName()).keySet().contains(student.getUserID())){
            return false;
        }
        allTags.get(tag.getName()).remove(student.getUserID());
        return true;
    }
}
