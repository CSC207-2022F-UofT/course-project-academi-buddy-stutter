package UseCases;

import Entities.InterestTag;
import Entities.Student;
import Entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TagMatchManager extends UseCase{

    private InterestTag selectedTag;
    private TagDataManager tagManager;


    public TagMatchManager(CourseDataManager courseDatabase, UserDataManager userDatabase, TagDataManager tagManager) {
        super(courseDatabase, userDatabase);
        this.tagManager = tagManager;
    }

    public void setSelectedTag(String selected){
        InterestTag selectedTag = new InterestTag(selected);
        this.selectedTag = selectedTag;
    }

    public ArrayList<Student> match(){
        List<String> idList = tagManager.getStudentList(selectedTag);
        ArrayList<Student> matchedStudents = new ArrayList<>();
        for(String id: idList){
            System.out.println(id);
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
        if(students.contains(null)){
            return nameList;
        }
        for(Student s: students){
            if(s.getUserID() != null && !s.getUserID().equals(self.getUserID())) {
                nameList.add(s.getFullName());
            }
        }
        return nameList;
    }

    public Student getStudentByIndex(int index){
        return match().get(index);
    }



}
