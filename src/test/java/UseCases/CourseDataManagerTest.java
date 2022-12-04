package UseCases;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


class CourseDataManagerTest extends Tests{
    ArrayList<?> managers;

    {
        try {
            managers = super.initializeStaticDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    UserDataLocal ub = (UserDataLocal) managers.get(0);
    CourseDataLocal cb = (CourseDataLocal) managers.get(1);
    TagDataLocal tb = (TagDataLocal) managers.get(2);

    @Test
    void getCourseCodeList() throws Exception {



        assertEquals(4, cb.getCourseCodeList().size());


    }

    @Test
    void updateCourse() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void removeStudent() {
    }

    @Test
    void getCourse() {
    }
}