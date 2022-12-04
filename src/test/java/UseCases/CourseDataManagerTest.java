package UseCases;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


class CourseDataManagerTest extends Tests{


    @Test
    void getCourseCodeList() throws Exception {
        ArrayList<?> managers = super.initializeStaticDatabase();
        UserDataManager ub = (UserDataManager) managers.get(0);
        CourseDataManager cb = (CourseDataManager) managers.get(1);
        TagDataManager tb = (TagDataManager) managers.get(2);



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