package Calendar;

import Sessions.Course;
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
    getCourses: returns courses as an Arraylist given the courses information as Course objects
    param: calendar_raw as String
    return: Arraylist containing courses a student is taking.
    */
    public ArrayList<Course> getCourses(String calendar_raw) {

        ICalendar ical = Biweekly.parse(calendar_raw).first();


        ArrayList<Course> courses = new ArrayList<Course>();

        int length = ical.getEvents().size();

        for(int i = 0; i < length; i++){
            VEvent event = ical.getEvents().get(i);

            String course_code = event.getSummary().getValue().substring(0,8);
            String sess_type = event.getSummary().getValue().substring(9,12);
            String sess_number = event.getSummary().getValue().substring(12,16);
            String course_name = event.getDescription().getValue().split("\n")[0];
            String day_of_week = event.getDateStart().getValue().toString().substring(0,3);
            String start = event.getDateStart().getValue().toString().substring(11,16);
            String end = event.getDateStart().getValue().toString().substring(24,28);

            Course course1 = new Course(course_code, sess_type, sess_number, course_name, day_of_week, start, end);


            if (!(courses.contains(course1))){
                courses.add(course1);
             }
        }

        return courses;

}
}