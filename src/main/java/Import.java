import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import java.util.*;

public class Import {
/* Reading from uploaded calendar and converting into information to store into database. Download calendar from acorn */

    private int student_id;


    //example calendar. to be deleted
    public static void main(String[] args) {

        String cal = "BEGIN:VCALENDAR\n" +
                "PRODID:-//Ben Fortuna//iCal4j 1.0//EN\n" +
                "VERSION:2.0\n" +
                "CALSCALE:GREGORIAN\n" +
                "BEGIN:VEVENT\n" +
                "DTSTAMP:20220926T205247Z\n" +
                "UID:08e12780-eb41-435a-a673-eb55531b6d67\n" +
                "SUMMARY:CSC207H1 LEC0301\n" +
                "DTSTART;TZID=America/Toronto:20220913T150000\n" +
                "DTEND;TZID=America/Toronto:20220913T160000\n" +
                "STATUS:CONFIRMED\n" +
                "DESCRIPTION:Software Design\\nBrennan Hall\n" +
                "LOCATION:BR 200\n" +
                "CREATED:20220926T205247Z\n" +
                "RRULE:FREQ=WEEKLY;WKST=MO;UNTIL=20221207T235959\n" +
                "EXDATE;TZID=America/Toronto:20221108T150000\n" +
                "BEGIN:VALARM\n" +
                "TRIGGER:-PT1H\n" +
                "REPEAT:4\n" +
                "DURATION:PT15M\n" +
                "ACTION:DISPLAY\n" +
                "DESCRIPTION:Software Design\n" +
                "END:VALARM\n" +
                "END:VEVENT\n" +
                "BEGIN:VEVENT\n" +
                "DTSTAMP:20220926T205247Z\n" +
                "UID:8ae82189-568c-4de4-8404-d6864ebc924b\n" +
                "SUMMARY:CSC207H1 LEC0301\n" +
                "DTSTART;TZID=America/Toronto:20220908T150000\n" +
                "DTEND;TZID=America/Toronto:20220908T160000\n" +
                "STATUS:CONFIRMED\n" +
                "DESCRIPTION:Software Design\\nBrennan Hall\n" +
                "LOCATION:BR 200\n" +
                "CREATED:20220926T205247Z\n" +
                "RRULE:FREQ=WEEKLY;WKST=MO;UNTIL=20221207T235959\n" +
                "EXDATE;TZID=America/Toronto:20221110T150000\n" +
                "BEGIN:VALARM\n" +
                "TRIGGER:-PT1H\n" +
                "REPEAT:4\n" +
                "DURATION:PT15M\n" +
                "ACTION:DISPLAY\n" +
                "DESCRIPTION:Software Design\n" +
                "END:VALARM\n" +
                "END:VEVENT\n" +
                "BEGIN:VEVENT\n" +
                "DTSTAMP:20220926T205247Z\n" +
                "UID:50ec0659-fe9c-49dc-baf0-be12914e1daa\n" +
                "SUMMARY:CSC207H1 TUT0401\n" +
                "DTSTART;TZID=America/Toronto:20220912T160000\n" +
                "DTEND;TZID=America/Toronto:20220912T180000\n" +
                "STATUS:CONFIRMED\n" +
                "DESCRIPTION:Software Design\\n\n" +
                "LOCATION: \n" +
                "CREATED:20220926T205247Z\n" +
                "RRULE:FREQ=WEEKLY;WKST=MO;UNTIL=20221207T235959\n" +
                "EXDATE;TZID=America/Toronto:20221010T160000,20221107T160000\n" +
                "BEGIN:VALARM\n" +
                "TRIGGER:-PT1H\n" +
                "REPEAT:4\n" +
                "DURATION:PT15M\n" +
                "ACTION:DISPLAY\n" +
                "DESCRIPTION:Software Design\n" +
                "END:VALARM\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR\n";
        System.out.println(getSummary(cal));
    }
    public static List<List<String>> getSummary(String calendar) {
        /*  return a list of courses with their info.
                  0               1               2               3                 4              5
            ["Course code", "Session type", "Session number", "Course name", "day of the week", "year"]
            [CSC207H1, LEC, 0301, Software Design, Tue, 2022]
         */

        List<ICalendar> icalList = Biweekly.parse(calendar).all();
        ICalendar ical = icalList.get(0);
        int eventSize = ical.getEvents().size();
        List<List<String>> summary = new ArrayList<>();
        for(VEvent event: ical.getEvents()){
            List<String> courseInfo = new ArrayList<String>();
            courseInfo.add(event.getSummary().getValue().substring(0,8)); //Course Code
            courseInfo.add(event.getSummary().getValue().substring(9,12)); //Session Type
            courseInfo.add(event.getSummary().getValue().substring(12,16)); //Session number
            courseInfo.add(event.getDescription().getValue().split("\n")[0]); //Course name
            courseInfo.add(event.getDateStart().getValue().toString().substring(0,3)); // day of the week
            courseInfo.add(event.getDateStart().getValue().toString().substring(24,28)); // year
            summary.add(courseInfo);
        }

        return summary;
    }

}
