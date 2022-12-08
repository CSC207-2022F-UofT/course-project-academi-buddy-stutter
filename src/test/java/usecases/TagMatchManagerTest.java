package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.entities.InterestTag;
import model.entities.Student;
import model.usecases.TagMatchManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class TagMatchManagerTest extends LocalTempDataFactory {

    final ArrayList<?> managers;

    {
        try {
            managers = super.initializeStaticDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);

    final TagMatchManager tagmatchManager = new TagMatchManager(cb, ub, tb);

    @Test
    //it tests setSelectTag() as well.
    void match(){
        for(String tagName: tb.getTagNameList()){
            tagmatchManager.setSelectedTag(tagName);
            ArrayList<String> selectedStudents = tb.getStudentList(new InterestTag(tagName));
            ArrayList<Student> matchedStudents = tagmatchManager.match();
            ArrayList<String> matchedStudentIDs = new ArrayList<>();
            for (Student student: matchedStudents){
                matchedStudentIDs.add(student.getUserID());
            }
            Assertions.assertIterableEquals(selectedStudents, matchedStudentIDs);
        }
    }

    @Test
    void getNameList() throws IOException {
        for(String tagName: tb.getTagNameList()){
            tagmatchManager.setSelectedTag(tagName);
            ArrayList<String> selectedStudents = tb.getStudentList(new InterestTag(tagName));
            ArrayList<String> selectedStudentNames = new ArrayList<>();
            for (String studentID: selectedStudents){
                selectedStudentNames.add(ub.getUserByID(studentID).getFullName());
            }
            selectedStudentNames.remove(STUDENT_A.getFullName());
            List<String> matchedNameList = tagmatchManager.getStudentName(STUDENT_A.getUserID());
            Assertions.assertIterableEquals(selectedStudentNames, matchedNameList);
        }
    }
}

