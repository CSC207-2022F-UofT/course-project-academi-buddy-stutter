package usecases;

import database.local.LocalTempDataBuilder;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.entities.InterestTag;
import model.usecases.AdminActionsManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class AdminActionsManagerTest extends LocalTempDataBuilder {
    final ArrayList<?> managers;

    {
        managers = super.initializeStaticDatabase();
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);

    final AdminActionsManager adminActionsManager = new AdminActionsManager(cb, ub, tb);

    @Test
    //It also tests private helper methods removeStudentFromCourse(), removeStudentFromTag() and removeStudentFromFriend() as well.
    void removeUser() {
        ArrayList<String> courses = STUDENT_A.getEnrolledCourseCodes();
        ArrayList<InterestTag> tags = STUDENT_A.getTags();

        //before removing user
        for (String courseCode: courses){
            Assertions.assertTrue(cb.getCourse(courseCode, "LEC").getEnrolledIDList().contains(STUDENT_A.getUserID()));
        }
        for(InterestTag tag: tags){
            Assertions.assertTrue(tb.allTags.get(tag.getName()).containsKey(STUDENT_A.getUserID()));
        }
        Assertions.assertTrue(STUDENT_B.getFriendList().contains(STUDENT_A.getUserID()));
        Assertions.assertTrue(STUDENT_C.getFriendRequestSentList().contains(STUDENT_A.getUserID()));

        Assertions.assertTrue(ub.getUserIDList().contains(STUDENT_A.getUserID()));

        adminActionsManager.removeUser(STUDENT_A.getUserID());

        //after removing user
        for (String courseCode: courses){
            Assertions.assertFalse(cb.getCourse(courseCode, "LEC").getEnrolledIDList().contains(STUDENT_A.getUserID()));
        }
        for(InterestTag tag: tags){
            Assertions.assertFalse(tb.allTags.get(tag.getName()).containsKey(STUDENT_A.getUserID()));
        }
        Assertions.assertFalse(STUDENT_B.getFriendList().contains(STUDENT_A.getUserID()));
        Assertions.assertFalse(STUDENT_C.getFriendRequestSentList().contains(STUDENT_A.getUserID()));

        Assertions.assertFalse(ub.getUserIDList().contains(STUDENT_A.getUserID()));
    }

}