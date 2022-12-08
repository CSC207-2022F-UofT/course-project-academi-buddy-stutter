package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.entities.Student;
import model.usecases.RegisterManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class RegisterManagerTest extends LocalTempDataFactory {

    final ArrayList<?> managers;

    {
        managers = super.initializeStaticDatabase();
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);

    final RegisterManager registerManager = new RegisterManager(cb, ub, tb);

    @Test
    void getWarnings(){
        //test if the methods correctly returns the desired list. Different password inputs are in StrengthCheckerTest.
        String password = " ";
        List<String> actual = registerManager.getWarnings(password);

        List<String> expected = new ArrayList<>();
        expected.add("Empty space not allowed");
        expected.add("Whitespace not allowed");
        expected.add("Password too short");
        expected.add("Add a number");
        expected.add("Add a symbol");

        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void getWarningsString(){
        //this method is a string builder for ui, so any possible error can only be related to ui, which will be noticed during gui testing.
        //nothing displays if a password meets criteria, since we used html to display multiple lines, size of returned string would be 23.
        String actual = registerManager.getWarningString("a.1234567890");
        Assertions.assertEquals(23, actual.length());
    }

    @Test
    void register() {
        //test when id exists
        Assertions.assertFalse(registerManager.register("STUDENT_TEMP", "111", "a.1234567890", "a.1234567890"));
        Assertions.assertFalse(registerManager.register("STUDENT_TEMP", "222", "a.1234567890", "a.1234567890"));
        Assertions.assertFalse(registerManager.register("STUDENT_TEMP", "333", "a.1234567890", "a/.1234567890"));


        //test when password does not meet criteria
        Assertions.assertFalse(registerManager.register("STUDENT_TEMP", "666", "pass", "pass"));
        Assertions.assertFalse(registerManager.register("STUDENT_TEMP", "666", " ", " "));
        Assertions.assertFalse(registerManager.register("STUDENT_TEMP", "666", "", ""));

        //test when password does not match the confirmation
        Assertions.assertFalse(registerManager.register("STUDENT_TEMP", "666", "a.1234567890", "b.1234567890"));
        Assertions.assertFalse(registerManager.register("STUDENT_TEMP", "666", "a.1234567890", " "));
        Assertions.assertFalse(registerManager.register("STUDENT_TEMP", "666", "a.1234567890", null));

        //test when all conditions are met
        Assertions.assertFalse(ub.existByID("666"));
        Assertions.assertTrue(registerManager.register("STUDENT_TEMP", "666", "a.1234567890", "a.1234567890"));
        Assertions.assertTrue(ub.existByID("666"));
        //verify user information is recorded correctly
        Student STUDENT_TEMP = (Student) ub.getUserByID("666");
        Assertions.assertEquals("666", STUDENT_TEMP.getUserID());
        Assertions.assertEquals("STUDENT_TEMP", STUDENT_TEMP.getFullName());
        Assertions.assertEquals("a.1234567890", STUDENT_TEMP.getUserPassword());
    }

    @Test
    void update() {
        registerManager.register("STUDENT_TEMP_ANOTHER", "777", "a.1234567890", "a.1234567890");
        Student STUDENT_TEMP_ANOTHER = (Student) ub.getUserByID("777");
        Assertions.assertNull(STUDENT_TEMP_ANOTHER.getEmail());
        Assertions.assertEquals("", STUDENT_TEMP_ANOTHER.getUserInfo());
        registerManager.updateEmailAndInfo("STUDENT@email.com", "A test subject");
        Assertions.assertEquals("STUDENT@email.com", STUDENT_TEMP_ANOTHER.getEmail());
        Assertions.assertEquals("A test subject", STUDENT_TEMP_ANOTHER.getUserInfo());
    }

}