package UseCases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;



class CommonSessionManagerTest extends TestDataFactory {

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

    CommonSessionManager commonSessionManager = new CommonSessionManager(cb, ub, tb);

    @Test
    void getName() {
        Assertions.assertEquals(STUDENTA.getFullName(), commonSessionManager.getName(STUDENTA.getUserID()));
        Assertions.assertEquals(STUDENTB.getFullName(), commonSessionManager.getName(STUDENTB.getUserID()));
        Assertions.assertEquals(STUDENTC.getFullName(), commonSessionManager.getName(STUDENTC.getUserID()));

    }

    @Test
    void getNumberOfCommonSessions(){
        commonSessionManager.getCommonSessions(STUDENTA.getUserID(), STUDENTB.getUserID());
        Assertions.assertEquals(2, commonSessionManager.getNumberOfCommonSessions());
        commonSessionManager.getCommonSessions(STUDENTA.getUserID(), STUDENTC.getUserID());
        Assertions.assertEquals(1, commonSessionManager.getNumberOfCommonSessions());
        commonSessionManager.getCommonSessions(STUDENTB.getUserID(), STUDENTC.getUserID());
        Assertions.assertEquals(2, commonSessionManager.getNumberOfCommonSessions());

    }

    @Test
    void getCommonSessions(){
        Assertions.assertTrue(commonSessionManager.getCommonSessions(STUDENTA.getUserID(), STUDENTB.getUserID()).contains("999"));
        Assertions.assertTrue(commonSessionManager.getCommonSessions(STUDENTA.getUserID(), STUDENTB.getUserID()).contains("888"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENTA.getUserID(), STUDENTB.getUserID()).contains("777"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENTA.getUserID(), STUDENTB.getUserID()).contains("666"));

        Assertions.assertTrue(commonSessionManager.getCommonSessions(STUDENTA.getUserID(), STUDENTC.getUserID()).contains("999"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENTA.getUserID(), STUDENTC.getUserID()).contains("888"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENTA.getUserID(), STUDENTC.getUserID()).contains("777"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENTA.getUserID(), STUDENTC.getUserID()).contains("666"));

        Assertions.assertTrue(commonSessionManager.getCommonSessions(STUDENTB.getUserID(), STUDENTC.getUserID()).contains("999"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENTB.getUserID(), STUDENTC.getUserID()).contains("888"));
        Assertions.assertFalse(commonSessionManager.getCommonSessions(STUDENTB.getUserID(), STUDENTC.getUserID()).contains("777"));
        Assertions.assertTrue(commonSessionManager.getCommonSessions(STUDENTB.getUserID(), STUDENTC.getUserID()).contains("666"));

    }

}