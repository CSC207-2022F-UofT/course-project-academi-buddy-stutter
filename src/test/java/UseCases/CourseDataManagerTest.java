package UseCases;

import Entities.Course;
import Entities.Student;
import TestAPI.TestDataAPI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class CourseDataManagerTest extends Tests{


    @Test
    void getCourseCodeList() {
        ArrayList<?> managers = super.initializeStaticDatabase();
        UserDataManager ub = (UserDataManager) managers.get(0);
        CourseDataManager cb = (CourseDataManager) managers.get(1);
        TagDataManager tb = (TagDataManager) managers.get(2);

        System.out.println(cb.getCourseCodeList());

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