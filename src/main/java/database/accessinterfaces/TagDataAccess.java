package database.accessinterfaces;

import model.entities.InterestTag;
import model.entities.Student;

import java.io.IOException;
import java.util.*;

public interface TagDataAccess {
    ArrayList<String> getTagNameList();


    ArrayList<String> getStudentList(InterestTag tag);

    void addStudent(InterestTag tag, Student student) throws IOException;

    boolean removeStudent(InterestTag tag, Student student) throws IOException;


}
