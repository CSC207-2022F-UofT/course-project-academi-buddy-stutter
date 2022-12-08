package entities;

import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalTempDataFactory;
import database.local.LocalUserData;
import model.usecases.ProfileManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class UserTest extends LocalTempDataFactory {
    final ArrayList<?> managers;

    {
        try {
            managers = super.initializeStaticDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);


    @Test
    void getUserIDTest() {Assertions.assertEquals("111", STUDENT_A.getUserID());}
    @Test
    void getFullNameTest() {
        Assertions.assertEquals("studenta", STUDENT_A.getFullName());
    }
    @Test
    void getUserInfoTest(){
        Assertions.assertEquals("", STUDENT_A.getUserInfo());
    }
    @Test
    void setUserInfoTest(){
        String infoUpdate = "HI";
        Assertions.assertEquals("", STUDENT_A.getUserInfo());
        STUDENT_A.setUserInfo(infoUpdate);
        Assertions.assertEquals(infoUpdate, STUDENT_A.getUserInfo());
    }
    @Test
    void setUserIDTest(){
        String idUpdate = "11111";
        Assertions.assertEquals("111", STUDENT_A.getUserID());
        STUDENT_A.setUserID(idUpdate);
        Assertions.assertEquals(idUpdate, STUDENT_A.getUserID());
    }

}
