package UseCases;

import Entities.Course;
import Entities.Student;
import TestAPI.TestDataAPI;

import java.util.ArrayList;

public class Tests {

    protected Course COURSEA = new Course(
            "999",
            "LEC",
            "909",
            "Astrophysics",
            "Tuesday",
            "12:00",
            "2003"
    );

    protected Course COURSEB = new Course(
            "888",
            "LEC",
            "808",
            "Chemistry",
            "Tuesday",
            "12:00",
            "2003"
    );

    protected Course COURSEC = new Course(
            "777",
            "LEC",
            "707",
            "Biology",
            "Tuesday",
            "12:00",
            "2003"
    );

    protected Course COURSED = new Course(
            "666",
            "LEC",
            "606",
            "Computer Science",
            "Tuesday",
            "12:00",
            "2003"
    );

    protected Student STUDENTA = new Student(
            "111", "pass", "studenta", ""
    );

    protected Student STUDENTB = new Student(
            "222", "pass", "studentb", ""
    );

    protected Student STUDENTC = new Student(
            "333", "pass", "studentc", ""
    );


    protected ArrayList<Object> initializeStaticDatabase(){
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


        ArrayList<String> STUDENTA_ENROLLED_COURSES = new ArrayList<String>() {
            {
                add("111");
                add("222");
                add("333");
            }
        };

        STUDENTA.setEnrolledCourses(STUDENTA_ENROLLED_COURSES);
        return managers;
    }
}
