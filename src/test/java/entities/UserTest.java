package entities;

import database.local.LocalTempDataFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest extends LocalTempDataFactory {

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
