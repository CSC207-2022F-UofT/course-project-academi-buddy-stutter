package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.usecases.LoginManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class LoginManagerTest extends LocalTempDataFactory {

    ArrayList<?> managers;

    {
        try {
            managers = super.initializeStaticDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    LocalUserData ub = (LocalUserData) managers.get(0);
    LocalCourseData cb = (LocalCourseData) managers.get(1);
    LocalTagData tb = (LocalTagData) managers.get(2);

    LoginManager loginManager = new LoginManager(cb, ub, tb);

    @Test
    void userExists() throws IOException {
        //Test non-exist user ids
        Assertions.assertFalse(loginManager.login("555", "pass"));
        Assertions.assertFalse(loginManager.login("", ""));
        Assertions.assertFalse(loginManager.login(null, null));

        //Test exist user with incorrect passswords
        Assertions.assertFalse( loginManager.login("111", "PASS"));
        Assertions.assertFalse( loginManager.login("222", ""));
        Assertions.assertFalse( loginManager.login("333", null));

        //Test correct credentials
        Assertions.assertTrue( loginManager.login("111", "pass"));
        Assertions.assertTrue( loginManager.login("222", "pass"));
        Assertions.assertTrue( loginManager.login("333", "pass"));
        Assertions.assertTrue( loginManager.login("444", "pass"));


    }
    @Test
    void getActiveUserID() throws IOException {
        loginManager.login("111", "pass");
        Assertions.assertEquals("111", loginManager.getActiveUserID());
        loginManager.login("222", "pass");
        Assertions.assertEquals("222", loginManager.getActiveUserID());
        loginManager.login("333", "pass");
        Assertions.assertEquals("333", loginManager.getActiveUserID());
        loginManager.login("444", "pass");
        Assertions.assertEquals("444", loginManager.getActiveUserID());

    }

    @Test
    void getUserTypeString() throws IOException {
        loginManager.login("111", "pass");
        Assertions.assertEquals("Student", loginManager.getUserTypeString());
        loginManager.login("222", "pass");
        Assertions.assertEquals("Student", loginManager.getUserTypeString());
        loginManager.login("333", "pass");
        Assertions.assertEquals("Student", loginManager.getUserTypeString());
        loginManager.login("444", "pass");
        Assertions.assertEquals("Admin", loginManager.getUserTypeString());
    }

}