package useCases;

import Database.CourseManager;
import Database.TagManager;
import Database.UserManager;
import Users.InterestTag;
import Users.Student;
import Users.User;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TagMatchManager extends UseCase{

    private InterestTag selectedTag;
    private TagManager tagManager;
    private ArrayList<Student> matchedStudents;


    public TagMatchManager(CourseManager courseDatabase, UserManager userDatabase, TagManager tagManager) {
        super(courseDatabase, userDatabase);
        this.tagManager = tagManager;
    }

    public void setSelectedTag(String selected){
        InterestTag selectedTag = new InterestTag(selected);
        this.selectedTag = selectedTag;
        match();
    }

    public void match(){
        List<String> idList = tagManager.getStudentList(selectedTag);
        ArrayList<Student> matchedStudents = new ArrayList<>();
        for(String id: idList){
            Student student = null;
            try {
                student = (Student) this.ub.getUserByID(id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            matchedStudents.add(student);
        }
        this.matchedStudents = matchedStudents;
    }

    public ArrayList<Student> getStudents(){
        return matchedStudents;
    }

    public List<String> getStudentName(User self){
        List<String> nameList = new ArrayList<>();
        for(Student s: matchedStudents){
            if(!s.getUser_id().equals(self.getUser_id())) {
                nameList.add(s.getFull_name());
            }
        }
        return nameList;
    }

    public Student getStudentByIndex(int index){
        return matchedStudents.get(index);
    }



}
