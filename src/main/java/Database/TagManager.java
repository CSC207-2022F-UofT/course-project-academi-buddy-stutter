package Database;

import Sessions.Course;
import Users.InterestTag;
import Users.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TagManager {
    private DatabaseInterface fi;
    private UserManager ud;

    public TagManager(DatabaseInterface tb, UserManager ud){
        this.fi = tb;
        this.fi.initialize("tags");
        this.ud = ud;
    }

    public ArrayList<String> getTagNameList(){
        return fi.getDocumentStringList();
    }

    public void addTag(InterestTag tag) throws IOException {
        String tagName = tag.getName();
        fi.addEntry(tagName, "students", new ArrayList<>().toString());
    }

    public List<String> getStudentList(InterestTag tag){
        String tagName = tag.getName();
        Map<String, Object> tagEntry= fi.getEntry(tagName);
        String stuString = (String) tagEntry.get("students");
        List<String> students = Arrays.asList(stuString.substring(1, stuString.length() - 1).split(", "));
        return students;
    }

    public void addStudent(InterestTag tag, Student student) throws IOException {
        List<String> students = getStudentList(tag);
        if(!students.contains(student)){
            students.add(student.GetUserID());
            student.updateStudentTOI(tag, true);
            ud.addStudentUser(student);
            fi.addEntry(tag.getName(), "students", students.toString());
        }
    }

    public void removeStudent(InterestTag tag, Student student) throws IOException {
        List<String> students = getStudentList(tag);
        if(students.contains(student)){
            students.remove(student.GetUserID());
            student.updateStudentTOI(tag, false);
            ud.addStudentUser(student);
            fi.addEntry(tag.getName(), "students", students.toString());
        }
    }


}
