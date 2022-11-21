package tutorial;

import Calendar.CalendarInterpret;
import Sessions.Course;
import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class CalendarInterpretTest {


    /*Test for the GetCourses method*/
        @Test
        public void CalendarInterpretGetCoursesTest() {
            String ex_file = "";

            try {
                File TestCalendar = new File("src/test/java/tutorial/TestCalendar");
                Scanner myReader = new Scanner(TestCalendar);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    ex_file = ex_file + (data+'\n');
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            ICalendar ical = Biweekly.parse(ex_file).first();

            VEvent event = ical.getEvents().get(0);

            String course_code = event.getSummary().getValue().substring(0,8);
            String sess_type = event.getSummary().getValue().substring(9,12);
            String sess_number = event.getSummary().getValue().substring(12,16);
            String course_name = event.getDescription().getValue().split("\n")[0];
            String day_of_week = event.getDateStart().getValue().toString().substring(0,3);
            String start = event.getDateStart().getValue().toString().substring(11,16);

            CalendarInterpret ci = new CalendarInterpret();

            ArrayList<Course> actual = ci.getCourses(ex_file);
            int x = actual.size();


            for (int i = 0; i < x; i++){
             String c_code = actual.get(i).getCourseCode();
             String s_type = actual.get(i).getCourseType();
             String s_num = actual.get(i).getSessionNumber();
             String c_name = actual.get(i).getCourseName();
             String d_o_w = actual.get(i).getDayOfWeek();
             String s = actual.get(i).getStartTime();

             System.out.println(c_code + " " +s_type +" "+s_num+" "+c_name +" " + d_o_w+" "+s);

            }

            Assertions.assertEquals(course_code, actual.get(0).getCourseCode());
            Assertions.assertEquals(sess_type, actual.get(0).getCourseType());
            Assertions.assertEquals(sess_number, actual.get(0).getSessionNumber());
            Assertions.assertEquals(course_name, actual.get(0).getCourseName());
            Assertions.assertEquals(day_of_week, actual.get(0).getDayOfWeek());
            Assertions.assertEquals(start, actual.get(0).getStartTime());

        }

        /*Test for the readCalendar method*/
    @Test
    public void CalendarInterpretReadCalendarTest() {
        String ex_file = "";

        try {
            File TestCalendar = new File("src/test/java/tutorial/TestCalendar");
            Scanner myReader = new Scanner(TestCalendar);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ex_file = ex_file + (data+'\n');
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String expected = ex_file;

        String file_name = "TestCalendar";

        CalendarInterpret ci = new CalendarInterpret();
        Assertions.assertEquals(expected, ci.readCalendar(file_name));

    }
}
