package model.entities;

import java.util.*;

/**
 * Implements course with course information
 */
public class Course{
    private String course_code;
    private String course_type;
    private String session_number;
    private String course_name;
    private String day_of_week;
    private String start_time;
    private ArrayList<String> enrolled_student_id;
    private String year;

    /**
     * Constructs a course
     * @param course_code Course code
     * @param course_type Course type [lec, tut]
     * @param session_number Session number
     * @param course_name Course title
     * @param day_of_week What day in the week does this course take place?
     * @param start_time Time that the course starts
     * @param year Year this course is taken
     */
    public Course(String course_code, String course_type, String session_number, String course_name, String day_of_week, String start_time, String year){
        this.course_code = course_code;
        this.course_type = course_type;
        this.session_number = session_number;
        this.course_name = course_name;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.year = year;
        enrolled_student_id = new ArrayList<>();
    }

    /**
     * @return Course code
     */
    public String getCourseCode(){return course_code;}

    /**
     * @return Course type [lec, tut]
     */
    public String getCourseType(){return course_type;}

    /**
     * @return Course title
     */
    public String getCourseName(){return course_name;}

    /**
     * @return Course session number
     */
    public String getSessionNumber(){return session_number;}

    /**
     * @return Day in the week this course takes place in
     */
    public String getDayOfWeek(){return day_of_week;}

    /**
     * @return Course start time
     */
    public String getStartTime(){return start_time;}

    /**
     * @return The year of the course is taken
     */
    public String getYear(){return year;}

    /**
     * @return A list of users that are enrolled in this course
     */
    public ArrayList<String> getEnrolledStudentID(){return enrolled_student_id;}

    public void setCourseCode(String course_code){this.course_code = course_code;}
    public void setCourseType(String course_type){this.course_type = course_code;}
    public void setSessionNumber(String session_number){this.session_number = course_code;}
    public void setDayOfWeek(String day_of_week){this.day_of_week = day_of_week;}
    public void setStartTime(String start_time){this.start_time = start_time;}
    public void setYear(String year){this.year = year;}

    /**
     * Append users in studentList to course
     * @param studentList a list of users to be appended to course
     */
    public void setEnrolledStudents(ArrayList<String> studentList){
        ArrayList<String> studentIDs = new ArrayList<>();
        studentIDs.addAll(studentList);
        this.enrolled_student_id = studentIDs;
    }

    /**
     * @param student a user
     * @return whether the user is enrolled in this course
     */
    public boolean isEnrolled(Student student){
        if(enrolled_student_id.contains(student.getUserID())){
            return true;
        }
        return false;
    }

    public int enrolledSize(){
        return enrolled_student_id.size();
    }

    /**
     * @param student a user
     * @return whether a user is successfully added to this course
     */
    public boolean addStudent(Student student){
        if(isEnrolled(student)){
            return false;
        }
        enrolled_student_id.add(student.getUserID());
        return true;
    }

    /**
     * @param student a user
     * @return whether the user is successfully removed from this course
     */
    public boolean removeStudent(Student student){
        if(isEnrolled(student)){
            enrolled_student_id.remove(student.getUserID());
            return true;
        }
        return false;
    }

    /**
     * @return a list of user ids that are enrolled in this course
     */
    public ArrayList<String> getEnrolledIDList(){
        ArrayList<String> IDList = new ArrayList<>();
        IDList.addAll(this.enrolled_student_id);
        return IDList;
    }
}