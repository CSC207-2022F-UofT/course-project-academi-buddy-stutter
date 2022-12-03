package UseCases;

import Entities.Student;
import TestAPI.TestDataAPI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CourseDataManagerTest {

    private ArrayList<Object> initializeStaticDatabase(){
        TestDataAPI cbc = new TestDataAPI();
        TestDataAPI ubc = new TestDataAPI();
        TestDataAPI tbc = new TestDataAPI();
        UserDataManager ub = new UserDataManager(ubc);
        CourseDataManager cb = new CourseDataManager(cbc, ub);
        TagDataManager tb = new TagDataManager(tbc, ub);
        ArrayList<Object> managers = new ArrayList<>();
        managers.add(ub);
        managers.add(cb);
        managers.add(tb);
        return managers;
    }


    @Test
    void getCourseCodeList() {
        ArrayList<?> managers = initializeStaticDatabase();
        UserDataManager ub = (UserDataManager) managers.get(0);
        CourseDataManager cb = (CourseDataManager) managers.get(1);
        TagDataManager tb = (TagDataManager) managers.get(2);






        cb.getCourseCodeList();

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