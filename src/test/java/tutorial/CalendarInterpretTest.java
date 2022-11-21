package tutorial;

import Calendar.CalendarInterpret;
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


           ArrayList<String> expected = new ArrayList<String>();
            expected.add("CSC207H1 LEC0201");
            expected.add("CSC207H1 TUT0401");
            expected.add("CSC236H1 LEC0401");
            expected.add("CSC236H1 TUT0401");
            expected.add("CSC258H1 LEC0201");
            expected.add("ETH201H1 LEC0101");
            expected.add("PDC220H1 LEC5101");
            expected.add("STA237H1 LEC0101");
            expected.add("STA237H1 TUT0101");
            expected.add("CSC209H1 LEC0201");
            expected.add("CSC209H1 TUT0201");
            expected.add("CSC263H1 LEC5101");
            expected.add("CSC263H1 TUT0104");
            expected.add("HPS110H1 LEC0101");
            expected.add("HPS110H1 TUT0801");
            expected.add("PDC221H1 LEC5101");
            expected.add("PSY260H1 LEC0101");
            expected.add("STA238H1 LEC0101");
            expected.add("STA238H1 TUT0104");

            CalendarInterpret ci = new CalendarInterpret();
            Assertions.assertEquals(expected, ci.getCourses(ex_file));

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
