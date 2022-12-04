package UseCases;

import Entities.Course;
import Entities.Student;

import java.io.IOException;
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


    protected ArrayList<Object> initializeStaticDatabase() throws IOException {

        UserDataLocal ub = new UserDataLocal();
        CourseDataLocal cb = new CourseDataLocal(ub);
        TagDataLocal tb = new TagDataLocal(ub);
        ArrayList<Object> managers = new ArrayList<>();
        managers.add(ub);
        managers.add(cb);
        managers.add(tb);

        cb.addCourse(COURSEA);
        cb.addCourse(COURSEB);
        cb.addCourse(COURSEC);
        cb.addCourse(COURSED);

        ub.addStudentUser(STUDENTA);
        ub.addStudentUser(STUDENTB);
        ub.addStudentUser(STUDENTC);
        cb.addStudent(COURSEA.getCourseCode(), "LEC", STUDENTA);
        cb.addStudent(COURSEA.getCourseCode(), "LEC", STUDENTB);
        cb.addStudent(COURSEA.getCourseCode(), "LEC", STUDENTC);

        cb.addStudent(COURSEB.getCourseCode(), "LEC", STUDENTA);
        cb.addStudent(COURSEB.getCourseCode(), "LEC", STUDENTB);

        cb.addStudent(COURSEC.getCourseCode(), "LEC", STUDENTB);

        cb.addStudent(COURSED.getCourseCode(), "LEC", STUDENTB);
        cb.addStudent(COURSED.getCourseCode(), "LEC", STUDENTC);


        return managers;
    }
}
