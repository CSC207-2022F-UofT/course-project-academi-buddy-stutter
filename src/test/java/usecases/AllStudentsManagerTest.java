package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.usecases.AllStudentsManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class AllStudentsManagerTest extends LocalTempDataFactory {

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

    final AllStudentsManager allStudentsManager = new AllStudentsManager(cb, ub, tb);

    @Test
    void getAllStudents(){
        ArrayList<String> actual = allStudentsManager.getAllStudents();

        ArrayList<String> expected = new ArrayList<>();
        expected.add("111");
        expected.add("222");
        expected.add("333");
        expected.add("444");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAdminIDs(){
        ArrayList<String> actual = allStudentsManager.getAdminIDs();

        ArrayList<String> expected = new ArrayList<>();
        expected.add("444");

        Assertions.assertEquals(expected, actual);
    }

}