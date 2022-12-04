package UseCases;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileManagerTest extends Tests {
    ArrayList<?> managers;

    {
        try {
            managers = super.initializeStaticDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    UserDataLocal ub = (UserDataLocal) managers.get(0);
    CourseDataLocal cb = (CourseDataLocal) managers.get(1);
    TagDataLocal tb = (TagDataLocal) managers.get(2);


    @Test
    void GetNameTest() throws Exception {
        ProfileManager p = new ProfileManager(cb, ub);

    }

}

