package apis;

import gateways.CalendarInterface;
import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import java.util.ArrayList;
import java.util.List;

public class BiweeklyAPI implements CalendarInterface {

    public int getLength(String calendar){
        List<ICalendar> icalList = Biweekly.parse(calendar).all();
        System.out.println(icalList.size());
        ICalendar ical = icalList.get(0);
        if(ical == null){
            return 0;
        }
        return ical.getEvents().size();
    }

    public Summary getSummary(String calendar, int num){
        List<ICalendar> icalList = Biweekly.parse(calendar).all();
        ICalendar ical = icalList.get(0);
        VEvent event = ical.getEvents().get(num);
        return event.getSummary();
    }
    public ArrayList<String> getCourseInfo(String calendar, int num) {
        ArrayList<String> courseInfo = new ArrayList<>();
        List<ICalendar> icalList = Biweekly.parse(calendar).all();
        ICalendar ical = icalList.get(0);
        VEvent event = ical.getEvents().get(num);
        Summary summary = getSummary(calendar, num);
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
