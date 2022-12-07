package entities;

import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalTempDataFactory;
import database.local.LocalUserData;
import model.usecases.ProfileManager;

import java.io.IOException;
import java.util.ArrayList;

public class UserTest extends LocalTempDataFactory {
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

    ProfileManager profileManager = new ProfileManager(cb, ub, tb);

    /*@Test
    void GetUserIDTest() {
        String expected = "222";
         User x = new User();
         x.user_id = "222";
         String actual = x.getUserID();
        assertEquals(expected , actual);
    }*/


}
