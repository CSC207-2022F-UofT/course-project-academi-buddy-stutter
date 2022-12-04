package UseCases;

import Entities.InterestTag;
import Entities.Student;

import java.io.IOException;
import java.util.*;

public interface TagDataManager {
    ArrayList<String> getTagNameList();


    ArrayList<String> getStudentList(InterestTag tag);

    void addStudent(InterestTag tag, Student student) throws IOException;

    boolean removeStudent(InterestTag tag, Student student) throws IOException;


}
