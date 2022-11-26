package UseCases;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

public class CalendarInterpreter {
/* Reading from uploaded calendar and converting into information to store into database.
Download calendar from acorn */


     /*
    GetCourse_to_time: returns Mapping of a student's courses to the courses times
    param: calendar_raw
    return: Mapping<String,String>
     */

   /* public  Map<String, String> GetCourse_to_time(String calendar_raw){

        VEvent courses = GetCourses(calendar_raw);

        ICalendar ical = Biweekly.parse(calendar_raw).first();

        VEvent event = ical.getEvents().get(0);



        return courses_to_time;
    } */

    /*
    GetCourses: returns VEvent of student's courses
    param: calendar_raw
    return: VEvent
     */

    public VEvent GetCourses(String calendar_raw){
        ICalendar ical = Biweekly.parse(calendar_raw).first();

        VEvent event = ical.getEvents().get(0);

        return event;
    }



    public String getSummary(String calendar) {

        String w =
                "BEGIN:VCALENDAR\r\n" +
                        "VERSION:2.0\r\n" +
                        "PRODID:-//Microsoft Corporation//Outlook 14.0 MIMEDIR//EN\r\n" +
                        "BEGIN:VEVENT\r\n" +
                        "UID:0123\r\n" +
                        "DTSTAMP:20130601T080000Z\r\n" +
                        "SUMMARY;LANGUAGE=en-us:Team Meeting\r\n" +
                        "DTSTART:20130610T120000Z\r\n" +
                        "DURATION:PT1H\r\n" +
                        "RRULE:FREQ=WEEKLY;INTERVAL=2\r\n" +
                        "END:VEVENT\r\n" +
                        "END:VCALENDAR\r\n";

        ICalendar ical = Biweekly.parse(w).first();

        VEvent event = ical.getEvents().get(0);

        String summary = event.getSummary().getValue();

        ical.getEvents();
        event.getSummary();


        return summary;


}
}