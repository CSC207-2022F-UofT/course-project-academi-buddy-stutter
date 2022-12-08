package entities;

import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalTempDataFactory;
import database.local.LocalUserData;
import model.usecases.ProfileManager;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    void getUserIDTest() {Assertions.assertEquals("111", STUDENTA.getUserID());}
    @Test
    void getFullNameTest() {
        Assertions.assertEquals("studenta", STUDENTA.getFullName());
    }
    @Test
    void getUserInfoTest(){
        Assertions.assertEquals("", STUDENTA.getUserInfo());
    }
    @Test
    void setUserInfoTest(){
        String infoUpdate = "HI";
        Assertions.assertEquals("", STUDENTA.getUserInfo());
        STUDENTA.setUserInfo(infoUpdate);
        Assertions.assertEquals(infoUpdate, STUDENTA.getUserInfo());
    }
    @Test
    void setUserIDTest(){
        String idUpdate = "11111";
        Assertions.assertEquals("111", STUDENTA.getUserID());
        STUDENTA.setUserID(idUpdate);
        Assertions.assertEquals(idUpdate, STUDENTA.getUserID());
    }

}
