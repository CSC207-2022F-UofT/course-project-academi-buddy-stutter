package model.usecases;

import adapters.gateways.CalendarInterface;
import model.entities.Course;

import java.io.*;
import java.util.ArrayList;

/** Reading from uploaded calendar and converting into information to store into database.
Download calendar from acorn */
public class CalendarInterpreter {
    CalendarInterface ci;

    public CalendarInterpreter(CalendarInterface ci){
        this.ci = ci;
    }

    /**
     * Returns String containing file information that can be understood by the Biweekly library.
     * @param fileName the name of the file as a String.
     * @return calendar as a String.
     */
    public String readCalendar(String fileName) {

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
                line = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
    Returns courses as an Arraylist given the courses information as Course objects
    @param calendar the calendar as String.
    @return Arraylist containing courses a student is taking.
    */
    public ArrayList<Course> getCourses(String calendar) {
        String calendarRaw = readCalendar(calendar);
        ArrayList<Course> courses = new ArrayList<>();
        int length = ci.getLength(calendarRaw);
        for(int i = 0; i < length; i++){
            ArrayList<String>  info = ci.getCourseInfo(calendarRaw, i);
            Course course1 = new Course(info.get(0), info.get(1), info.get(2), info.get(3),
                    info.get(4), info.get(5), info.get(6));
            if (!(courses.contains(course1))){
                courses.add(course1);
            }
        }
        return courses;

    }
}