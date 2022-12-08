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

    AllStudentsManager allStudentsManager = new AllStudentsManager(cb, ub, tb);

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