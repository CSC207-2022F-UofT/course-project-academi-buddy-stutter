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


    public TagMatchManager(CourseManager courseDatabase, UserManager userDatabase, TagManager tagManager) {
        super(courseDatabase, userDatabase);
        this.tagManager = tagManager;
    }

    public void setSelectedTag(String selected){
        InterestTag selectedTag = new InterestTag(selected);
        this.selectedTag = selectedTag;
    }

    public ArrayList<Student> match(){
        List<String> idList = tagManager.getStudentList(selectedTag);
        System.out.println(idList);
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

    public List<String> getStudentName(User self){
        List<String> nameList = new ArrayList<>();
        ArrayList<Student> students = match();
        for(Student s: students){
            if(s.getUser_id() != null && !s.getUser_id().equals(self.getUser_id())) {
                nameList.add(s.getFull_name());
            }
        }
        return nameList;
    }

    public Student getStudentByIndex(int index){
        return match().get(index);
    }



}
