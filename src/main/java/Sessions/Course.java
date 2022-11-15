package Sessions;

import Users.Student;

import java.sql.Time;
import java.util.*;
import java.time.*;

public class Course{
    private String course_code;
    private String course_type;
    private String session_number;
    private String course_name;
    private String day_of_week;
    private String start_time;
    private ArrayList<Student> enrolled_students;
    private String year;

    public Course(String course_code, String course_type, String session_number, String course_name, String day_of_week, String start_time, String year){
        this.course_code = course_code;
        this.course_type = course_type;
        this.session_number = session_number;
        this.course_name = course_name;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.year = year;
        enrolled_students = new ArrayList<>();
    }

    public String getCourseCode(){return course_code;}
    public String getCourseType(){return course_type;}
    public String getCourseName(){return course_name;}
    public String getSessionNumber(){return session_number;}
    public String getDayOfWeek(){return day_of_week;}
    public String getStartTime(){return start_time;}
    public String getYear(){return year;}
    public ArrayList<Student> getEnrolledStudents(){return enrolled_students;}

    public void setCourseCode(String course_code){this.course_code = course_code;}
    public void setCourseType(String course_type){this.course_type = course_code;}
    public void setSessionNumber(String session_number){this.session_number = course_code;}
    public void setDayOfWeek(String day_of_week){this.day_of_week = day_of_week;}
    public void setStartTime(String start_time){this.start_time = start_time;}
    public void setYear(String year){this.year = year;}

    public void setEnrolledStudents(ArrayList<Student> studentList){this.enrolled_students = studentList;}
    public boolean isEnrolled(Student student){
        if(enrolled_students.contains(student)){
            return true;
        }
        return false;
    }

    public int enrolledSize(){
        return enrolled_students.size();
    }


    public boolean addStudent(Student student){
        if(isEnrolled(student)){
            return false;
        }
        enrolled_students.add(student);
        return true;
    }
    public boolean removeStudent(Student student){
        if(isEnrolled(student)){
            enrolled_students.remove(student);
            return true;
        }
        return false;
    }

    public ArrayList<String> getEnrolledID(){
        ArrayList<String> IDList = new ArrayList<>();
        for(Student s: enrolled_students){
            IDList.add(s.GetUserID());
        }
        return IDList;
    }
}