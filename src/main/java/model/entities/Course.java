package model.entities;

import java.util.*;

/**
 * Implements course with course information
 */
public class Course{
    private final String courseCode;
    private final String courseType;
    private final String sessionNumber;
    private final String courseName;
    private final String dayOfWeek;
    private final String startTime;
    private ArrayList<String> enrolledStudentID;
    private final String year;

    /**
     * Constructs a course
     * @param courseCode Course code
     * @param courseType Course type [lec, tut]
     * @param sessionNumber Session number
     * @param courseName Course title
     * @param dayOfWeek What day in the week does this course take place?
     * @param startTime Time that the course starts
     * @param year Year this course is taken
     */
    public Course(String courseCode, String courseType, String sessionNumber, String courseName, String dayOfWeek, String startTime, String year){
        this.courseCode = courseCode;
        this.courseType = courseType;
        this.sessionNumber = sessionNumber;
        this.courseName = courseName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.year = year;
        enrolledStudentID = new ArrayList<>();
    }

    /**
     * @return Course code
     */
    public String getCourseCode(){return courseCode;}

    /**
     * @return Course type [lec, tut]
     */
    public String getCourseType(){return courseType;}

    /**
     * @return Course title
     */
    public String getCourseName(){return courseName;}

    /**
     * @return Course session number
     */
    public String getSessionNumber(){return sessionNumber;}

    /**
     * @return Day in the week this course takes place in
     */
    public String getDayOfWeek(){return dayOfWeek;}

    /**
     * @return Course start time
     */
    public String getStartTime(){return startTime;}

    /**
     * @return The year of the course is taken
     */
    public String getYear(){return year;}

    /**
     * Append users in studentList to course
     * @param studentList a list of users to be appended to course
     */
    public void setEnrolledStudents(ArrayList<String> studentList){
        this.enrolledStudentID = new ArrayList<>(studentList);
    }

    /**
     * @param student a user
     * @return whether the user is enrolled in this course
     */
    public boolean isEnrolled(Student student){
        return enrolledStudentID.contains(student.getUserID());
    }

    /**
     * @param student a user
     * @return whether a user is successfully added to this course
     */
    public boolean addStudent(Student student){
        if(isEnrolled(student)){
            return false;
        }
        enrolledStudentID.add(student.getUserID());
        return true;
    }

    /**
     * @param student a user
     * @return whether the user is successfully removed from this course
     */
    public boolean removeStudent(Student student){
        if(isEnrolled(student)){
            enrolledStudentID.remove(student.getUserID());
            return true;
        }
        return false;
    }

    /**
     * @return a list of user ids that are enrolled in this course
     */
    public ArrayList<String> getEnrolledIDList(){
        return new ArrayList<>(this.enrolledStudentID);
    }
}