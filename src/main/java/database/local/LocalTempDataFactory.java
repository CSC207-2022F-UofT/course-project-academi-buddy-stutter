package database.local;

import model.entities.*;

import java.io.IOException;
import java.util.ArrayList;

public class LocalTempDataFactory {

    protected final Course COURSE_A = new Course(
            "999",
            "LEC",
            "909",
            "Astrophysics",
            "Tuesday",
            "12:00",
            "2003"
    );

    protected final Course COURSE_B = new Course(
            "888",
            "LEC",
            "808",
            "Chemistry",
            "Tuesday",
            "12:00",
            "2003"
    );

    protected final Course COURSE_C = new Course(
            "777",
            "LEC",
            "707",
            "Biology",
            "Tuesday",
            "12:00",
            "2003"
    );

    protected final Course COURSE_D = new Course(
            "666",
            "LEC",
            "606",
            "Computer Science",
            "Tuesday",
            "12:00",
            "2003"
    );

    protected final Student STUDENT_A = new Student(
            "111", "pass", "studenta", ""
    );

    protected final Student STUDENT_B = new Student(
            "222", "pass", "studentb", ""
    );

    protected final Student STUDENT_C = new Student(
            "333", "pass", "studentc", ""
    );

    protected final Admin ADMIN = new Admin(
            "444", "pass", "admin", ""
    );


    protected ArrayList<Object> initializeStaticDatabase() throws IOException {

        LocalUserData ub = new LocalUserData();
        LocalCourseData cb = new LocalCourseData(ub);
        LocalTagData tb = new LocalTagData();
        ArrayList<Object> managers = new ArrayList<>();

        cb.addCourse(COURSE_A);
        cb.addCourse(COURSE_B);
        cb.addCourse(COURSE_C);
        cb.addCourse(COURSE_D);

        InterestTag adventure = new InterestTag("Adventure");
        InterestTag music = new InterestTag("Music");
        InterestTag beer = new InterestTag("Beer");
        InterestTag cat = new InterestTag("Cat");

        ArrayList<InterestTag> tagA = new ArrayList<>();
        tb.addStudent(adventure, STUDENT_A);
        tb.addStudent(beer, STUDENT_A);
        tagA.add(adventure);
        tagA.add(beer);

        ArrayList<InterestTag> tagB = new ArrayList<>();
        tb.addStudent(adventure, STUDENT_B);
        tb.addStudent(music, STUDENT_B);
        tagB.add(adventure);
        tagB.add(music);

        ArrayList<InterestTag> tagC = new ArrayList<>();
        tb.addStudent(music, STUDENT_C);
        tb.addStudent(cat, STUDENT_C);
        tagC.add(music);
        tagC.add(cat);

        STUDENT_A.setTabs_of_interests(tagA);
        STUDENT_B.setTabs_of_interests(tagB);
        STUDENT_C.setTabs_of_interests(tagC);

        Label meetLabel = new Label("Want to Meet");
        Label collaborateLabel = new Label("Want to Collaborate");
        Label discussLabel = new Label("Want to Discuss");

        STUDENT_A.updateLabel(meetLabel, true);
        STUDENT_B.updateLabel(meetLabel, true);

        STUDENT_A.updateLabel(collaborateLabel, true);

        STUDENT_B.updateLabel(discussLabel, true);
        STUDENT_C.updateLabel(discussLabel, true);

        ArrayList<String> studentAFriendList = new ArrayList<>();
        ArrayList<String> studentBFriendList = new ArrayList<>();
        ArrayList<String> studentAFriendRequestList = new ArrayList<>();
        ArrayList<String> studentCFriendRequestSentList = new ArrayList<>();

        studentAFriendList.add(STUDENT_B.getUserID());
        studentBFriendList.add(STUDENT_A.getUserID());

        studentAFriendRequestList.add(STUDENT_C.getUserID());
        studentCFriendRequestSentList.add(STUDENT_A.getUserID());

        STUDENT_A.setFriendList(studentAFriendList);
        STUDENT_B.setFriendList(studentBFriendList);
        STUDENT_A.setFriendRequestList(studentAFriendRequestList);
        STUDENT_C.setFriendRequestSentList(studentCFriendRequestSentList);

        ub.addStudentUser(STUDENT_A);
        ub.addStudentUser(STUDENT_B);
        ub.addStudentUser(STUDENT_C);

        ub.addUser(ADMIN);

        cb.addStudent(COURSE_A.getCourseCode(), "LEC", STUDENT_A);
        cb.addStudent(COURSE_A.getCourseCode(), "LEC", STUDENT_B);
        cb.addStudent(COURSE_A.getCourseCode(), "LEC", STUDENT_C);

        cb.addStudent(COURSE_B.getCourseCode(), "LEC", STUDENT_A);
        cb.addStudent(COURSE_B.getCourseCode(), "LEC", STUDENT_B);

        cb.addStudent(COURSE_C.getCourseCode(), "LEC", STUDENT_B);

        cb.addStudent(COURSE_D.getCourseCode(), "LEC", STUDENT_B);
        cb.addStudent(COURSE_D.getCourseCode(), "LEC", STUDENT_C);


        managers.add(ub);
        managers.add(cb);
        managers.add(tb);


        return managers;
    }

    public ArrayList<Object> getLocalDatabase() throws IOException {
        return initializeStaticDatabase();
    }
}
