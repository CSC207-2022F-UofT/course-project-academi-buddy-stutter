package Users;
import Sessions.*;
import java.sql.Array;
import java.util.*;

public class Student extends User{
    private ArrayList<Tabs> tabs_of_interests;

    public void setTabs_of_interests(ArrayList<Tabs> tabs_of_interests) {
        this.tabs_of_interests = tabs_of_interests;
    }

    public void setEnrolled_courses(ArrayList<Course> enrolled_courses) {
        this.enrolled_courses = enrolled_courses;
    }

    private ArrayList<Course> enrolled_courses;

    public ArrayList<Tabs> getTabs_of_interests() {return tabs_of_interests;}

    public ArrayList<Course> getEnrolledCourses() {return enrolled_courses;}

    public ArrayList<String> getEnrolled_courseCodes() {
        ArrayList<String> courseCodes = new ArrayList<>();
        for(int i = 0; i < enrolled_courses.size(); i++){
            courseCodes.add(enrolled_courses.get(i).getCourseCode());
        }

        return courseCodes;
    }

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


    public boolean addCourse(Course course){
        if(enrolled_courses.contains(course)){
            return false;
        }
        enrolled_courses.add(course);
        return true;
    }

    public boolean removeCourse(Course course){
        if(enrolled_courses.contains(course)){
            enrolled_courses.remove(course);
            return true;
        }
        return false;
    }
}
