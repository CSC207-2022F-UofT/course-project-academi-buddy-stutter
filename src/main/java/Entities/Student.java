package Entities;
import java.util.*;

public class Student extends User{
    private ArrayList<InterestTag> tags_of_interests;
    private ArrayList<Label> labels;

    private String email;

    public ArrayList<User> friend_list;

    public void setTabs_of_interests(ArrayList<InterestTag> tags_of_interests) {
        this.tags_of_interests = tags_of_interests;
    }

    public void setEnrolledCourses(ArrayList<String> enrolled_courses) {
        ArrayList<String> courseCodeList = new ArrayList<>();
        for (String course: enrolled_courses){
            courseCodeList.add(course);
        }
        this.enrolled_course_codes = courseCodeList;
    }

    public void setEmail(String email){this.email = email;}

    public String getEmail(){return email;}

    private ArrayList<String> enrolled_course_codes;

    public ArrayList<InterestTag> getTags() {return tags_of_interests;}
    public ArrayList<Label> getLabels() {return labels;}

    public ArrayList<String> getEnrolledCourseCodes() {
        ArrayList<String> courseCodes = new ArrayList<>();
        courseCodes.addAll(this.enrolled_course_codes);
        return courseCodes;
    }

    public ArrayList<User> getFriendList() {
        ArrayList<User> friendList = new ArrayList<>();
        friendList.addAll(this.friend_list);
        return friendList;
    }

    //init
    public Student(String UID, String UPass, String full_name, String info){
        super(UID, UPass, full_name, info);
        this.tags_of_interests = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.enrolled_course_codes = new ArrayList<>();
        this.friend_list = new ArrayList<>();
    }

    public void updateStudentTOI(InterestTag tag, boolean selected){
        if(selected && !(isTagSelected(tag))){
            tags_of_interests.add(tag);
        } else if (!selected && (isTagSelected(tag))) {
            ArrayList<InterestTag> newTags = new ArrayList<>();
            for (InterestTag t: tags_of_interests){
                if(!tag.getName().equals(t.getName())){
                    newTags.add(t);
                }
                this.tags_of_interests = newTags;
            }
        }
    }

    public void updateLabel(Label label, boolean selected){
        if(selected && !(isLabelSelected(label))){
            labels.add(label);
        } else if (!selected && isLabelSelected(label)) {
            ArrayList<Label> newLabels = new ArrayList<>();
            for (Label l: labels){
                if(!label.getName().equals(l.getName())){
                    newLabels.add(l);
                }
                this.labels = newLabels;
            }
        }
    }


    public boolean addCourse(Course course){
        if(enrolled_course_codes.contains(course.getCourseCode())){
            return false;
        }
        enrolled_course_codes.add(course.getCourseCode());
        return true;
    }

    public boolean removeCourse(Course course){
        if(enrolled_course_codes.contains(course.getCourseCode())){
            enrolled_course_codes.remove(course.getCourseCode());
            return true;
        }
        return false;
    }

    public boolean isLabelSelected(Label label){
        ArrayList<String> labelNames = new ArrayList<>();
        for(Label l: labels){
            labelNames.add(l.getName());
        }
        return labelNames.contains(label.getName());
    }
    public boolean isTagSelected(InterestTag tag){
        ArrayList<String> tagNames = new ArrayList<>();
        for(InterestTag t: tags_of_interests){
            tagNames.add(t.getName());
        }
        return tagNames.contains(tag.getName());}
}
