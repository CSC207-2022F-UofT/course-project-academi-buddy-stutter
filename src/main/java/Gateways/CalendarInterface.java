package Gateways;

/**
 * Implements CalendarInterface
 */

import java.util.ArrayList;

public interface CalendarInterface {
    /**
     * @param calendar_raw unfiltered ics calendar
     * @return length of calendar
     */
    int getLength(String calendar_raw);

    /**
     * Get course information
     * @param calendar_raw unfiltered ics calendar
     * @param num number of courses
     * @return a list of course information
     */
    ArrayList<String> getCourseInfo(String calendar_raw, int num);
}
