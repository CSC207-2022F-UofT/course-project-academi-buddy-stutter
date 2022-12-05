package UseCases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileManagerTest extends TestDataFactory {
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

    ProfileManager profileManager = new ProfileManager(cb, ub, tb);


    @Test
    void GetNameTest() throws Exception {
        for(String userID: ub.getUserIDList()){
            Assertions.assertEquals(ub.getUserByID(userID).getFullName(), profileManager.getName(userID));
        }

    }

}

