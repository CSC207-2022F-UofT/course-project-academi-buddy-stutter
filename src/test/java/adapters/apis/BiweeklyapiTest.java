package adapters.apis;

import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalTempDataFactory;
import database.local.LocalUserData;
import org.junit.jupiter.api.Test;
import usecases.ProfileManager;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiweeklyapiTest extends LocalTempDataFactory {
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
    void GetLengthTest() {
        //BiweeklyAPI x = new BiweeklyAPI();
        //CalendarInterface y =
        //CalendarInterpreter z = new CalendarInterpreter();
        //x.readCalendar()
        //assertEquals(expected , actual);
        assertEquals(1 , 1);

    }

}
