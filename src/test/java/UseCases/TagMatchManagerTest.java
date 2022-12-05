package UseCases;

import Entities.InterestTag;
import Entities.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class TagMatchManagerTest extends TestDataFactory {

    ArrayList<?> managers;

    {
        try {
            managers = super.initializeStaticDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    LocalUserData ub = (LocalUserData) managers.get(0);
    LocalCourseData cb = (LocalCourseData) managers.get(1);
    LocalTagData tb = (LocalTagData) managers.get(2);

    TagMatchManager tagmatchManager = new TagMatchManager(cb, ub, tb);

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
            selectedStudentNames.remove(STUDENTA.getFullName());
            List<String> matchedNameList = tagmatchManager.getStudentName(STUDENTA);
            Assertions.assertIterableEquals(selectedStudentNames, matchedNameList);
        }
    }
}

