package usecases;

import adapters.apis.BiweeklyAPI;
import database.local.LocalTempDataBuilder;
import model.entities.Course;
import model.usecases.CalendarInterpreter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarInterpreterTest extends LocalTempDataBuilder {

    @Test
    void GetCoursesTest() {

        BiweeklyAPI x = new BiweeklyAPI();
        CalendarInterpreter y = new CalendarInterpreter(x);
        ArrayList<Course>  actual = y.getCourses("coursesCalendar.ics");

        assertEquals("CSC207H1" , actual.get(0).getCourseCode());
        assertEquals("LEC" , actual.get(0).getCourseType());
        assertEquals("0301" , actual.get(0).getSessionNumber());
        assertEquals("Software Design" , actual.get(0).getCourseName());
        assertEquals("Tue" , actual.get(0).getDayOfWeek());
        assertEquals("15:00" , actual.get(0).getStartTime());
        assertEquals("2022" , actual.get(0).getYear());


    }


}
