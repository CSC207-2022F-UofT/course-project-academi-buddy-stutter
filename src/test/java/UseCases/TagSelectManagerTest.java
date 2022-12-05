package UseCases;

import Entities.InterestTag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class TagSelectManagerTest extends TestDataFactory {

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
        for(InterestTag tag: STUDENTA.getTags()){
            Assertions.assertTrue(tagSelectManager.getStudentTagState(STUDENTA, tag.getName()));
        }
    }

    @Test
    void updateStudentTag(){
        for(InterestTag tag: STUDENTA.getTags()){
            Assertions.assertTrue(tagSelectManager.getStudentTagState(STUDENTA, tag.getName()));
            tagSelectManager.updateStudentTag(STUDENTA, tag.getName(), false);
            Assertions.assertFalse(tagSelectManager.getStudentTagState(STUDENTA, tag.getName()));
            tagSelectManager.updateStudentTag(STUDENTA, tag.getName(), true);
            Assertions.assertTrue(tagSelectManager.getStudentTagState(STUDENTA, tag.getName()));
        }
    }

}