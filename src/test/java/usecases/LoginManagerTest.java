package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.usecases.LoginManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class LoginManagerTest extends LocalTempDataFactory {

    final ArrayList<?> managers;

    {
        managers = super.initializeStaticDatabase();
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);

    final LoginManager loginManager = new LoginManager(cb, ub, tb);

    @Test
    void userExists() {
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
    void getActiveUserID() {
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
    void getUserTypeString() {
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