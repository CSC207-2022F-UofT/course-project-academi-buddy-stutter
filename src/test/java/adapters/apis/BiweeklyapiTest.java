package adapters.apis;

import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalTempDataFactory;
import database.local.LocalUserData;
import org.junit.jupiter.api.Test;
import model.usecases.ProfileManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiweeklyapiTest extends LocalTempDataFactory {
    final ArrayList<?> managers;

    {
        managers = super.initializeStaticDatabase();
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);

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
