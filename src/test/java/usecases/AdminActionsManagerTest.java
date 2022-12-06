package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.entities.InterestTag;
import model.usecases.AdminActionsManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class AdminActionsManagerTest extends LocalTempDataFactory{
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

    AdminActionsManager adminActionsManager = new AdminActionsManager(cb, ub, tb);

    @Test
    //It also tests the private helper methods removeStudentFromCourse(), removeStudentFromTag() and removeStudentFromFriend() as well.
    void removeUser() throws Exception {
        ArrayList<String> courses = STUDENTA.getEnrolledCourseCodes();
        ArrayList<InterestTag> tags = STUDENTA.getTags();

        //before removing user
        for (String courseCode: courses){
            Assertions.assertTrue(cb.getCourse(courseCode, "LEC").getEnrolledIDList().contains(STUDENTA.getUserID()));
        }
        for(InterestTag tag: tags){
            Assertions.assertTrue(tb.allTags.get(tag.getName()).containsKey(STUDENTA.getUserID()));
        }
        Assertions.assertTrue(STUDENTB.getFriendList().contains(STUDENTA.getUserID()));
        Assertions.assertTrue(STUDENTC.getFriendRequestSentList().contains(STUDENTA.getUserID()));

        Assertions.assertTrue(ub.getUserIDList().contains(STUDENTA.getUserID()));

        adminActionsManager.removeUser(STUDENTA.getUserID());

        //after removing user
        for (String courseCode: courses){
            Assertions.assertFalse(cb.getCourse(courseCode, "LEC").getEnrolledIDList().contains(STUDENTA.getUserID()));
        }
        for(InterestTag tag: tags){
            Assertions.assertFalse(tb.allTags.get(tag.getName()).containsKey(STUDENTA.getUserID()));
        }
        Assertions.assertFalse(STUDENTB.getFriendList().contains(STUDENTA.getUserID()));
        Assertions.assertFalse(STUDENTC.getFriendRequestSentList().contains(STUDENTA.getUserID()));

        Assertions.assertFalse(ub.getUserIDList().contains(STUDENTA.getUserID()));
    }

}