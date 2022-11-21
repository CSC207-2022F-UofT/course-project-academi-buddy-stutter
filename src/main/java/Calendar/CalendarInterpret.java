package Calendar;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CalendarInterpret {
/* Reading from uploaded calendar and converting into information to store into database.
Download calendar from acorn */


    /*
    readCalendar: returns String containing file information that can be understood by the Biweekly library
    param: file_name as String
    return: calendar_raw as String
    */
    public String readCalendar(String file_name) {

        String calendar_raw = "";

        try {
            File TestCalendar = new File( "src/test/java/tutorial/"+ file_name);
            Scanner myReader = new Scanner(TestCalendar);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                calendar_raw = calendar_raw + (data+'\n');
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return calendar_raw;
        }



    /*
    getCourses: returns courses as an Arraylist given the calendar information as a String
    param: calendar_raw as String
    return: Arraylist containing courses a student is taking.
    */
    public ArrayList<String> getCourses(String calendar_raw) {

        ICalendar ical = Biweekly.parse(calendar_raw).first();

        ArrayList<String> courses = new ArrayList<String>();

        int length = ical.getEvents().size();

        for(int i = 0; i < length; i++){
            VEvent event = ical.getEvents().get(i);
            String course = event.getSummary().getValue();

            if (!(courses.contains(course))){
                courses.add(course);
             }
        }

        return courses;

}
}