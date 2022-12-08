package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.entities.Student;
import model.usecases.CourseMatchManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class CourseMatchManagerTest extends LocalTempDataFactory {

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

    final CourseMatchManager courseMatchManager = new CourseMatchManager(cb, ub, tb);

    @Test
    void getTopCommonSessionStudentTestA() throws IOException {
        //COURSE_A: A, B, C
        //COURSE_B: A, B
        //COURSE_C:    B
        //COURSE_D:    B, C

        ArrayList<Student> actual = courseMatchManager.getTopSameSessionStudents(STUDENT_A, 1);
        ArrayList<Student> expected = new ArrayList<>();
        expected.add(STUDENT_B);
        expected.add(STUDENT_C);
        Assertions.assertIterableEquals(expected, actual);

        actual = courseMatchManager.getTopSameSessionStudents(STUDENT_A, 2);
        expected = new ArrayList<>();
        expected.add(STUDENT_B);
        Assertions.assertIterableEquals(expected, actual);

        expected = new ArrayList<>();
        for(int i = 3; i < 12; i++){
            actual = courseMatchManager.getTopSameSessionStudents(STUDENT_A, i);
            Assertions.assertIterableEquals(expected, actual);
        }
    }

    @Test
    void getTopCommonSessionStudentTestB() throws IOException {
        //COURSE_A: A, B, C
        //COURSE_B: A, B
        //COURSE_C:    B
        //COURSE_D:    B, C

        ArrayList<Student> actual = courseMatchManager.getTopSameSessionStudents(STUDENT_B, 1);
        ArrayList<Student> expected = new ArrayList<>();
        expected.add(STUDENT_A);
        expected.add(STUDENT_C);
        Assertions.assertIterableEquals(expected, actual);

        expected = new ArrayList<>();
        expected.add(STUDENT_A);
        expected.add(STUDENT_C);
        actual = courseMatchManager.getTopSameSessionStudents(STUDENT_B, 2);
        Assertions.assertIterableEquals(expected, actual);

        expected = new ArrayList<>();
        for(int i = 3; i < 12; i++){
            actual = courseMatchManager.getTopSameSessionStudents(STUDENT_B, i);
            Assertions.assertIterableEquals(expected, actual);
        }
    }

    @Test
    void getTopCommonSessionStudentTestC() throws IOException {
        //COURSE_A: A, B, C
        //COURSE_B: A, B
        //COURSE_C:    B
        //COURSE_D:    B, C

        ArrayList<Student> actual = courseMatchManager.getTopSameSessionStudents(STUDENT_C, 1);
        ArrayList<Student> expected = new ArrayList<>();
        expected.add(STUDENT_B);
        expected.add(STUDENT_A);//list should be sorted by number of common sessions in descending order. B has 2, A has 1.
        Assertions.assertIterableEquals(expected, actual);

        actual = courseMatchManager.getTopSameSessionStudents(STUDENT_C, 2);
        expected = new ArrayList<>();
        expected.add(STUDENT_B);
        Assertions.assertIterableEquals(expected, actual);

        expected = new ArrayList<>();
        for(int i = 3; i < 12; i++){
            actual = courseMatchManager.getTopSameSessionStudents(STUDENT_C, i);
            Assertions.assertIterableEquals(expected, actual);
        }
    }

    @Test
    void filterByLabelTestA() throws IOException {
        //Meet: A, B
        //Collaborate: A
        //Discuss: B, C
        ArrayList<Student> matchedList = courseMatchManager.getTopSameSessionStudents(STUDENT_A, 1);

        ArrayList<Student> actual = courseMatchManager.filterByLabel(matchedList, "Want to Meet");
        ArrayList<Student> expected = new ArrayList<>();
        expected.add(STUDENT_B);
        Assertions.assertIterableEquals(expected, actual);

        actual = courseMatchManager.filterByLabel(matchedList, "Want to Collaborate");
        expected = new ArrayList<>();
        Assertions.assertIterableEquals(expected, actual);

        actual = courseMatchManager.filterByLabel(matchedList, "Want to Discuss");
        expected = new ArrayList<>();
        expected.add(STUDENT_B);
        expected.add(STUDENT_C);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void filterByLabelTestB() throws IOException {
        //Meet: A, B
        //Collaborate: A
        //Discuss: B, C
        ArrayList<Student> matchedList = courseMatchManager.getTopSameSessionStudents(STUDENT_B, 1);

        ArrayList<Student> actual = courseMatchManager.filterByLabel(matchedList, "Want to Meet");
        ArrayList<Student> expected = new ArrayList<>();
        expected.add(STUDENT_A);
        Assertions.assertIterableEquals(expected, actual);

        actual = courseMatchManager.filterByLabel(matchedList, "Want to Collaborate");
        expected = new ArrayList<>();
        expected.add(STUDENT_A);
        Assertions.assertIterableEquals(expected, actual);

        actual = courseMatchManager.filterByLabel(matchedList, "Want to Discuss");
        expected = new ArrayList<>();
        expected.add(STUDENT_C);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void filterByLabelTestC() throws IOException {
        //Meet: A, B
        //Collaborate: A
        //Discuss: B, C
        ArrayList<Student> matchedList = courseMatchManager.getTopSameSessionStudents(STUDENT_C, 1);

        ArrayList<Student> actual = courseMatchManager.filterByLabel(matchedList, "Want to Meet");
        ArrayList<Student> expected = new ArrayList<>();
        expected.add(STUDENT_B);
        expected.add(STUDENT_A);
        Assertions.assertIterableEquals(expected, actual);

        actual = courseMatchManager.filterByLabel(matchedList, "Want to Collaborate");
        expected = new ArrayList<>();
        expected.add(STUDENT_A);
        Assertions.assertIterableEquals(expected, actual);

        actual = courseMatchManager.filterByLabel(matchedList, "Want to Discuss");
        expected = new ArrayList<>();
        expected.add(STUDENT_B);
        Assertions.assertIterableEquals(expected, actual);
    }
}