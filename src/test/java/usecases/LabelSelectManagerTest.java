package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.usecases.LabelSelectManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class LabelSelectManagerTest extends LocalTempDataFactory {

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

    LabelSelectManager labelSelectManager = new LabelSelectManager(cb, ub, tb);

    @Test
        //It also tests the private helper methods removeStudentFromCourse(), removeStudentFromTag() and removeStudentFromFriend() as well.
    void getStudentLabelState(){
        //Meet: A, B
        //Collaborate: A
        //Discuss: B, C
        Assertions.assertTrue(labelSelectManager.getStudentLabelState(STUDENTA.getUserID(), "Want to Meet"));
        Assertions.assertTrue(labelSelectManager.getStudentLabelState(STUDENTA.getUserID(), "Want to Collaborate"));
        Assertions.assertFalse(labelSelectManager.getStudentLabelState(STUDENTA.getUserID(), "Want to Discuss"));

        Assertions.assertTrue(labelSelectManager.getStudentLabelState(STUDENTB.getUserID(), "Want to Meet"));
        Assertions.assertFalse(labelSelectManager.getStudentLabelState(STUDENTB.getUserID(), "Want to Collaborate"));
        Assertions.assertTrue(labelSelectManager.getStudentLabelState(STUDENTB.getUserID(), "Want to Discuss"));

        Assertions.assertFalse(labelSelectManager.getStudentLabelState(STUDENTC.getUserID(), "Want to Meet"));
        Assertions.assertFalse(labelSelectManager.getStudentLabelState(STUDENTC.getUserID(), "Want to Collaborate"));
        Assertions.assertTrue(labelSelectManager.getStudentLabelState(STUDENTC.getUserID(), "Want to Discuss"));
    }

    @Test
    void updateStudentLabel(){
        //Remove all labels.
        //Meet:
        //Collaborate:
        //Discuss:
        labelSelectManager.updateStudentLabel(STUDENTA.getUserID(), "Want to Meet", false);
        labelSelectManager.updateStudentLabel(STUDENTB.getUserID(), "Want to Meet", false);
        labelSelectManager.updateStudentLabel(STUDENTC.getUserID(), "Want to Meet", false);
        labelSelectManager.updateStudentLabel(STUDENTA.getUserID(), "Want to Collaborate", false);
        labelSelectManager.updateStudentLabel(STUDENTB.getUserID(), "Want to Collaborate", false);
        labelSelectManager.updateStudentLabel(STUDENTC.getUserID(), "Want to Collaborate", false);
        labelSelectManager.updateStudentLabel(STUDENTA.getUserID(), "Want to Discuss", false);
        labelSelectManager.updateStudentLabel(STUDENTB.getUserID(), "Want to Discuss", false);
        labelSelectManager.updateStudentLabel(STUDENTC.getUserID(), "Want to Discuss", false);

        Assertions.assertEquals(0, STUDENTA.getLabels().size());
        Assertions.assertEquals(0, STUDENTB.getLabels().size());
        Assertions.assertEquals(0, STUDENTC.getLabels().size());

        //Add all labels.
        //Meet: A, B, C
        //Collaborate: A, B, C
        //Discuss: A, B, C
        labelSelectManager.updateStudentLabel(STUDENTA.getUserID(), "Want to Meet", true);
        labelSelectManager.updateStudentLabel(STUDENTB.getUserID(), "Want to Meet", true);
        labelSelectManager.updateStudentLabel(STUDENTC.getUserID(), "Want to Meet", true);
        labelSelectManager.updateStudentLabel(STUDENTA.getUserID(), "Want to Collaborate", true);
        labelSelectManager.updateStudentLabel(STUDENTB.getUserID(), "Want to Collaborate", true);
        labelSelectManager.updateStudentLabel(STUDENTC.getUserID(), "Want to Collaborate", true);
        labelSelectManager.updateStudentLabel(STUDENTA.getUserID(), "Want to Discuss", true);
        labelSelectManager.updateStudentLabel(STUDENTB.getUserID(), "Want to Discuss", true);
        labelSelectManager.updateStudentLabel(STUDENTC.getUserID(), "Want to Discuss", true);

        Assertions.assertEquals(3, STUDENTA.getLabels().size());
        Assertions.assertEquals(3, STUDENTB.getLabels().size());
        Assertions.assertEquals(3, STUDENTC.getLabels().size());


    }

}