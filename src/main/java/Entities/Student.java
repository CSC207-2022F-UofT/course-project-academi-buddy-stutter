package Entities;
import java.util.*;

public class Student extends User{
    private ArrayList<InterestTag> tags_of_interests;
    private ArrayList<Label> labels;

    public void setTabs_of_interests(ArrayList<InterestTag> tags_of_interests) {
        this.tags_of_interests = tags_of_interests;
    }

    public void setEnrolledCourses(ArrayList<Course> enrolled_courses) {
        this.enrolled_courses = enrolled_courses;
    }

    private ArrayList<Course> enrolled_courses;

    public ArrayList<InterestTag> getTags() {return tags_of_interests;}
    public ArrayList<Label> getLabels() {return labels;}

    public ArrayList<Course> getEnrolledCourses() {return enrolled_courses;}

    public ArrayList<String> getEnrolledCourseCodes() {
        ArrayList<String> courseCodes = new ArrayList<>();
        for(int i = 0; i < enrolled_courses.size(); i++){
            courseCodes.add(enrolled_courses.get(i).getCourseCode());
        }

        return courseCodes;
    }

    //init
    public Student(String UID, String UPass, String full_name, String info){
        super(UID, UPass, full_name, info);
        this.tags_of_interests = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.enrolled_courses = new ArrayList<>();
    }

    public void updateStudentTOI(InterestTag tag, boolean selected){
        if(selected && !(tags_of_interests.contains(tag))){
            tags_of_interests.add(tag);
        } else if (!selected && tags_of_interests.contains(tag)) {
            tags_of_interests.remove(tag);
        }
    }

    public void updateLabel(Label label, boolean selected){
        if(selected && !(labels.contains(label))){
            labels.add(label);
        } else if (!selected && labels.contains(label)) {
            labels.remove(label);
        }
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

    public boolean isLabelSelected(Label label){
        return labels.contains(label);
    }
    public boolean isTagSelected(InterestTag tag){return tags_of_interests.contains(tag);}
}
