package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.entities.InterestTag;
import model.usecases.TagSelectManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class TagSelectManagerTest extends LocalTempDataFactory {

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

    TagSelectManager tagSelectManager = new TagSelectManager(cb, ub, tb);

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