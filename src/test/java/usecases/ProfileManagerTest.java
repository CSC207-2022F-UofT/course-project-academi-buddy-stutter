package usecases;

import database.local.LocalTempDataBuilder;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.usecases.ProfileManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ProfileManagerTest extends LocalTempDataBuilder {
    final ArrayList<?> managers;

    {
        managers = super.initializeStaticDatabase();
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);

    final ProfileManager profileManager = new ProfileManager(cb, ub, tb);


    @Test
    void GetNameTest() {
        for(String userID: ub.getUserIDList()){
            Assertions.assertEquals(ub.getUserByID(userID).getFullName(), profileManager.getName(userID));
        }
    }

}

