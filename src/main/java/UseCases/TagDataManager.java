package UseCases;

import Database.DatabaseInterface;
import Entities.InterestTag;
import Entities.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    public void addTag(InterestTag tag) throws IOException {
        fi.initialize("tags");
        String tagName = tag.getName();
        ArrayList<String> strList = new ArrayList<>();
        fi.addEntry(tagName, "students", strList.toString());
    }

    public ArrayList<String> getStudentList(InterestTag tag){
        fi.initialize("tags");
        String tagName = tag.getName();
        Map<String, Object> tagEntry= fi.getEntry(tagName);
        String stuString = (String) tagEntry.get("students");
        if(stuString == null){
            return new ArrayList<>();
        }
        List<String> studentList = Arrays.asList(stuString.substring(1, stuString.length() - 1).split(", "));
        ArrayList<String> students = new ArrayList<>();
        for(String s: studentList){
            students.add(s);
        }
        return students;
    }

    public void addStudent(InterestTag tag, Student student) throws IOException {
        fi.initialize("tags");
        ArrayList<String> students = new ArrayList<>();
        for(String s: getStudentList(tag)){
            students.add(s);
        }
        if(!students.contains(student.getUser_id())){
            students.add(student.GetUserID());
            student.updateStudentTOI(tag, true);
            ud.addStudentUser(student);
            fi.addEntry(tag.getName(), "students", students.toString());
        }
    }

    public void removeStudent(InterestTag tag, Student student) throws IOException {
        fi.initialize("tags");
        ArrayList<String> students = new ArrayList<>();
        for(String s: getStudentList(tag)){
            students.add(s);
        }
        if(students.contains(student.getUser_id())){
            students.remove(student.GetUserID());
            student.updateStudentTOI(tag, false);
            ud.addStudentUser(student);
            fi.initialize("tags");
            fi.addEntry(tag.getName(), "students", students.toString());
        }
    }


}
