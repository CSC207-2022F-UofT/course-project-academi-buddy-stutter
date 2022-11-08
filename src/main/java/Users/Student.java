package Users;
import Sessions.*;
import java.sql.Array;
import java.util.*;

public class Student extends User{
    private ArrayList<Tabs> tabs_of_interests;
    private ArrayList<Course> enrolled_courses;

    //init
    public Student(String UID, String UPass, String full_name, String info){
        super(UID, UPass, full_name, info);
        this.tabs_of_interests = new ArrayList<>();
        this.enrolled_courses = new ArrayList<>();
    }

    /*
    UpdateStudentTOI: updates the student's tabs of interest given the index of the tab required for change
    param: index
    return: none
     */
    private void UpdateStudentTOI(int index, Boolean value){
        this.tabs_of_interests.get(index).value = value;
    }

    //@TODO update database for these methods
    /*
    public boolean addCourse(Course course){
        if(enrolled_courses.contains(course)){
            return false;
        }
        enrolled_courses.add(course);
        course.addStudent(this);
        return true;
    }

    public boolean removeCourse(Course course){
        if(enrolled_courses.contains(course)){
            enrolled_courses.remove(course);
            course.removeStudent(this);
            return true;
        }
        return false;
    }
    */
}
