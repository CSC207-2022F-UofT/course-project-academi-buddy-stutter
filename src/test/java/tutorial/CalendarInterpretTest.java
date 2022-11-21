package tutorial;

import Calendar.CalendarInterpret;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class CalendarInterpretTest {


        @Test
        public void CalendarInterpretGetCoursesTest() {
            String ex_file = "";

            try {
                File TestCalendar = new File("src/test/java/tutorial/TestCalendar");
                Scanner myReader = new Scanner(TestCalendar);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    ex_file = ex_file + data;
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            CalendarInterpret ci = new CalendarInterpret();
            Assertions.assertEquals("Team Meeting", ci.getSummary(ex_file));

        }
}
