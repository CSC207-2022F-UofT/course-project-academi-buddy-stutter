package usecases;

import database.local.LocalTempDataBuilder;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.entities.Student;
import model.usecases.CourseMatchManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;


class CourseMatchManagerTest extends LocalTempDataBuilder {

    final ArrayList<?> managers;

    {
        managers = super.initializeStaticDatabase();
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);

    CourseMatchManager courseMatchManager = new CourseMatchManager(cb, ub, tb);

    @Test
    void createLabelByModelTestA() {
        //COURSE_A: A, B, C
        //COURSE_B: A, B
        //COURSE_C:    B
        //COURSE_D:    B, C

        DefaultListModel<String> actual = courseMatchManager.createModelByLabel(STUDENT_A.getUserID(), "None", 1);
        DefaultListModel<String> expected = new DefaultListModel<>();
        for (Student student : Arrays.asList(STUDENT_B, STUDENT_C)) {
            expected.addElement(student.getFullName());
        }
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        actual = courseMatchManager.createModelByLabel(STUDENT_A.getUserID(), "None", 2);
        expected = new DefaultListModel<>();
        expected.addElement(STUDENT_B.getFullName());
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        expected = new DefaultListModel<>();
        for(int i = 3; i < 12; i++){
            actual = courseMatchManager.createModelByLabel(STUDENT_A.getUserID(), "None", i);
            for(int j = 0; j < actual.getSize(); j++){
                Assertions.assertEquals(expected.get(j), actual.get(j));
            }
        }
    }

    @Test
    void createLabelByModelTestB() {
        //COURSE_A: A, B, C
        //COURSE_B: A, B
        //COURSE_C:    B
        //COURSE_D:    B, C

        DefaultListModel<String> actual = courseMatchManager.createModelByLabel(STUDENT_B.getUserID(), "None", 1);
        DefaultListModel<String> expected = new DefaultListModel<>();
        for (Student student : Arrays.asList(STUDENT_A, STUDENT_C)) {
            expected.addElement(student.getFullName());
        }
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        actual = courseMatchManager.createModelByLabel(STUDENT_B.getUserID(), "None", 2);
        expected = new DefaultListModel<>();
        for (Student student : Arrays.asList(STUDENT_A, STUDENT_C)) {
            expected.addElement(student.getFullName());
        }
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        expected = new DefaultListModel<>();
        for(int i = 3; i < 12; i++){
            actual = courseMatchManager.createModelByLabel(STUDENT_B.getUserID(), "None", i);
            for(int j = 0; j < actual.getSize(); j++){
                Assertions.assertEquals(expected.get(j), actual.get(j));
            }
        }
    }

    @Test
    void createLabelByModelTestC() {
        //COURSE_A: A, B, C
        //COURSE_B: A, B
        //COURSE_C:    B
        //COURSE_D:    B, C
        DefaultListModel<String> actual = courseMatchManager.createModelByLabel(STUDENT_C.getUserID(), "None", 1);
        DefaultListModel<String> expected = new DefaultListModel<>();
        for (Student student : Arrays.asList(STUDENT_B, STUDENT_A)) {
            expected.addElement(student.getFullName());
        }
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        actual = courseMatchManager.createModelByLabel(STUDENT_C.getUserID(), "None", 2);
        expected = new DefaultListModel<>();
        expected.addElement(STUDENT_B.getFullName());
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        expected = new DefaultListModel<>();
        for(int i = 3; i < 12; i++){
            actual = courseMatchManager.createModelByLabel(STUDENT_C.getUserID(), "None", i);
            for(int j = 0; j < actual.getSize(); j++){
                Assertions.assertEquals(expected.get(j), actual.get(j));
            }
        }
    }

    @Test
    void createLabelByModelLabelTestA() {
        //Meet: A, B
        //Collaborate: A
        //Discuss: B, C
        DefaultListModel<String> actual = courseMatchManager.createModelByLabel(STUDENT_A.getUserID(), "Want to Meet", 1);
        DefaultListModel<String> expected = new DefaultListModel<>();
        expected.addElement(STUDENT_B.getFullName());
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        actual = courseMatchManager.createModelByLabel(STUDENT_A.getUserID(), "Want to Collaborate", 2);
        expected = new DefaultListModel<>();
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        expected = new DefaultListModel<>();
        expected.addElement(STUDENT_B.getFullName());
        expected.addElement(STUDENT_C.getFullName());
        for(int i = 3; i < 12; i++){
            actual = courseMatchManager.createModelByLabel(STUDENT_A.getUserID(), "Want to Discuss", i);
            for(int j = 0; j < actual.getSize(); j++){
                Assertions.assertEquals(expected.get(j), actual.get(j));
            }
        }
    }
    @Test
    void createLabelByModelLabelTestB() {
        //Meet: A, B
        //Collaborate: A
        //Discuss: B, C
        DefaultListModel<String> actual = courseMatchManager.createModelByLabel(STUDENT_B.getUserID(), "Want to Meet", 1);
        DefaultListModel<String> expected = new DefaultListModel<>();
        expected.addElement(STUDENT_A.getFullName());
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        actual = courseMatchManager.createModelByLabel(STUDENT_B.getUserID(), "Want to Collaborate", 2);
        expected = new DefaultListModel<>();
        expected.addElement(STUDENT_A.getFullName());
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        expected = new DefaultListModel<>();
        expected.addElement(STUDENT_C.getFullName());
        for(int i = 3; i < 12; i++){
            actual = courseMatchManager.createModelByLabel(STUDENT_B.getUserID(), "Want to Discuss", i);
            for(int j = 0; j < actual.getSize(); j++){
                Assertions.assertEquals(expected.get(j), actual.get(j));
            }
        }
    }

    @Test
    void createLabelByModelLabelTestC() {
        //Meet: A, B
        //Collaborate: A
        //Discuss: B, C
        DefaultListModel<String> actual = courseMatchManager.createModelByLabel(STUDENT_C.getUserID(), "Want to Meet", 1);
        DefaultListModel<String> expected = new DefaultListModel<>();
        expected.addElement(STUDENT_B.getFullName());
        expected.addElement(STUDENT_A.getFullName());
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        actual = courseMatchManager.createModelByLabel(STUDENT_C.getUserID(), "Want to Collaborate", 2);
        expected = new DefaultListModel<>();
        expected.addElement(STUDENT_A.getFullName());
        for(int i = 0; i < actual.getSize(); i++){
            Assertions.assertEquals(expected.get(i), actual.get(i));
        }

        expected = new DefaultListModel<>();
        expected.addElement(STUDENT_B.getFullName());
        for(int i = 3; i < 12; i++){
            actual = courseMatchManager.createModelByLabel(STUDENT_C.getUserID(), "Want to Discuss", i);
            for(int j = 0; j < actual.getSize(); j++){
                Assertions.assertEquals(expected.get(j), actual.get(j));
            }
        }
    }

}