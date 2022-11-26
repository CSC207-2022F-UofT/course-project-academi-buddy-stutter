package Database;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import java.util.ArrayList;

public class CalendarAPI implements CalenderInterface{

    public int getLength(String calendar_raw){
        ICalendar ical = Biweekly.parse(calendar_raw).first();
        if(ical == null){
            return 0;
        }
        return ical.getEvents().size();
    }

    public Summary getSummary(String calendar_raw, int num){
        ICalendar ical = Biweekly.parse(calendar_raw).first();
        VEvent event = ical.getEvents().get(num);
        return event.getSummary();
    }
    public ArrayList<String> getCourseInfo(String calendar_raw, int num) {
        ArrayList<String> courseInfo = new ArrayList<>();
        ICalendar ical = Biweekly.parse(calendar_raw).first();
        VEvent event = ical.getEvents().get(num);
        Summary summary = getSummary(calendar_raw, num);
        String course_code = summary.getValue().substring(0,8);
        String sess_type = summary.getValue().substring(9,12);
        String sess_number = summary.getValue().substring(12,16);
        String course_name = event.getDescription().getValue().split("\n")[0];
        String day_of_week = event.getDateStart().getValue().toString().substring(0,3);
        String start = event.getDateStart().getValue().toString().substring(11,16);
        String end = event.getDateStart().getValue().toString().substring(24,28);
        courseInfo.add(course_code);
        courseInfo.add(sess_type);
        courseInfo.add(sess_number);
        courseInfo.add(course_name);
        courseInfo.add(day_of_week);
        courseInfo.add(start);
        courseInfo.add(end);
        return courseInfo;

    }
}
