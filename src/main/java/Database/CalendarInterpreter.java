package Database;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CalendarInterpreter {
    /*
    Reading from uploaded calendar and converting into information to store into database.
    Download calendar from acorn
    */

    public static String readCalendar(String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
                line = reader.readLine();
            }
            return builder.toString();
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    //TODO example calendar. to be deleted
    public static void main(String[] args) {
        System.out.println(getSummary(readCalendar("coursesCalendar.ics")));
    }



    public static List<List<String>> getSummary(String calendar) {
        /*  return a list of courses with their info.
                  0               1               2               3                 4                 5         6
            ["Course code", "Session type", "Session number", "Course name", "day of the week", "start time", "year"]
            [CSC207H1, LEC, 0301, Software Design, Tue, 15:00, 2022]
         */
        List<ICalendar> icalList = Biweekly.parse(calendar).all();
        ICalendar ical = icalList.get(0);
        List<List<String>> summary = new ArrayList<>();
        for(VEvent event: ical.getEvents()){
            List<String> courseInfo = new ArrayList<String>();
            courseInfo.add(event.getSummary().getValue().substring(0,8)); //Course Code
            courseInfo.add(event.getSummary().getValue().substring(9,12)); //Sessions.Session Type
            courseInfo.add(event.getSummary().getValue().substring(12,16)); //Sessions.Session number
            courseInfo.add(event.getDescription().getValue().split("\n")[0]); //Course name
            courseInfo.add(event.getDateStart().getValue().toString().substring(0,3)); // day of the week
            courseInfo.add(event.getDateStart().getValue().toString().substring(11,16)); //start time
            courseInfo.add(event.getDateStart().getValue().toString().substring(24,28)); // year
            summary.add(courseInfo);
        }
        return summary;
    }

}