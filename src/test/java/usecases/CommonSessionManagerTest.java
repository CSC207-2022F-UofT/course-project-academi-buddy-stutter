package usecases;

import database.local.LocalTempDataBuilder;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.usecases.CommonSessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;



class CommonSessionManagerTest extends LocalTempDataBuilder {

    final ArrayList<?> managers;

    {
        managers = super.initializeStaticDatabase();
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);

    final CommonSessionManager commonSessionManager = new CommonSessionManager(cb, ub, tb);

    @Test
    void getName() {
        Assertions.assertEquals(STUDENT_A.getFullName(), commonSessionManager.getName(STUDENT_A.getUserID()));
        Assertions.assertEquals(STUDENT_B.getFullName(), commonSessionManager.getName(STUDENT_B.getUserID()));
        Assertions.assertEquals(STUDENT_C.getFullName(), commonSessionManager.getName(STUDENT_C.getUserID()));

    }

    @Test
    void getNumberOfCommonSessions(){
        commonSessionManager.getCommonSessions(STUDENT_A.getUserID(), STUDENT_B.getUserID());
        Assertions.assertEquals(2, commonSessionManager.getNumberOfCommonSessions());
        commonSessionManager.getCommonSessions(STUDENT_A.getUserID(), STUDENT_C.getUserID());
        Assertions.assertEquals(1, commonSessionManager.getNumberOfCommonSessions());
        commonSessionManager.getCommonSessions(STUDENT_B.getUserID(), STUDENT_C.getUserID());
        Assertions.assertEquals(2, commonSessionManager.getNumberOfCommonSessions());

    }

    @Test
    void getCommonSessions(){
        Assertions.assertTrue(commonSessionManager.getCommonSessions(STUDENT_A.getUserID(), STUDENT_B.getUserID()).contains("999"));
        Assertions.assertTrue(commonSessionManager.getCommonSessions(STUDENT_A.getUserID(), STUDENT_B.getUserID()).contains("888"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENT_A.getUserID(), STUDENT_B.getUserID()).contains("777"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENT_A.getUserID(), STUDENT_B.getUserID()).contains("666"));

        Assertions.assertTrue(commonSessionManager.getCommonSessions(STUDENT_A.getUserID(), STUDENT_C.getUserID()).contains("999"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENT_A.getUserID(), STUDENT_C.getUserID()).contains("888"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENT_A.getUserID(), STUDENT_C.getUserID()).contains("777"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENT_A.getUserID(), STUDENT_C.getUserID()).contains("666"));

        Assertions.assertTrue(commonSessionManager.getCommonSessions(STUDENT_B.getUserID(), STUDENT_C.getUserID()).contains("999"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENT_B.getUserID(), STUDENT_C.getUserID()).contains("888"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENT_B.getUserID(), STUDENT_C.getUserID()).contains("777"));
        Assertions.assertTrue(commonSessionManager.getCommonSessions(STUDENT_B.getUserID(), STUDENT_C.getUserID()).contains("666"));

    }

}