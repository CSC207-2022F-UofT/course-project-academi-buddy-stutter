package entities;

import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalTempDataFactory;
import database.local.LocalUserData;
import model.entities.Course;
import org.junit.jupiter.api.Test;
import model.usecases.ProfileManager;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest extends LocalTempDataFactory {
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

    ProfileManager profileManager = new ProfileManager(cb, ub, tb);


    @Test
    void GetCourseListTest() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("999LEC");
        expected.add("888LEC");
        expected.add("777LEC");
        expected.add("666LEC");
        expected.add("CSC236lec");
        Course x = new Course("CSC236","lec",
                "401","Theory of Computation","Monday","1","2");
        cb.addCourse(x);
        ArrayList<String> actual =  cb.getCourseCodeList();
        assertEquals(expected , actual);
    }
}
