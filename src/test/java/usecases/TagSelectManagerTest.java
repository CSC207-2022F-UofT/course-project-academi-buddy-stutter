package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.entities.InterestTag;
import model.usecases.TagSelectManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class TagSelectManagerTest extends LocalTempDataFactory {

    final ArrayList<?> managers;

    {
        managers = super.initializeStaticDatabase();
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);

    final TagSelectManager tagSelectManager = new TagSelectManager(cb, ub, tb);

    @Test
    void getStudentState(){
        for(InterestTag tag: STUDENT_A.getTags()){
            Assertions.assertTrue(tagSelectManager.getStudentTagState(STUDENT_A.getUserID(), tag.getName()));
        }
    }

    @Test
    void updateStudentTag(){
        for(InterestTag tag: STUDENT_A.getTags()){
            Assertions.assertTrue(tagSelectManager.getStudentTagState(STUDENT_A.getUserID(), tag.getName()));
            tagSelectManager.updateStudentTag(STUDENT_A.getUserID(), tag.getName(), false);
            Assertions.assertFalse(tagSelectManager.getStudentTagState(STUDENT_A.getUserID(), tag.getName()));
            tagSelectManager.updateStudentTag(STUDENT_A.getUserID(), tag.getName(), true);
            Assertions.assertTrue(tagSelectManager.getStudentTagState(STUDENT_A.getUserID(), tag.getName()));
        }
    }

}