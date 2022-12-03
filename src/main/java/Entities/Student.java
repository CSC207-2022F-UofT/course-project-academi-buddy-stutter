package Entities;
import java.lang.reflect.Array;
import java.util.*;

public class Student extends User{
    private ArrayList<InterestTag> tags_of_interests;
    private ArrayList<Label> labels;

    private String email;

    private ArrayList<String> friendList;
    private ArrayList<String> friend_request_list;
    private ArrayList<String> friendRequestSentList;

    public Student(String UID, String UPass, String full_name, String info){
        super(UID, UPass, full_name, info);
        this.tags_of_interests = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.enrolled_course_codes = new ArrayList<>();
        this.friendList = new ArrayList<>();
        this.friend_request_list = new ArrayList<>();
        this.friendRequestSentList = new ArrayList<>();
    }

    public void setFriend_list(ArrayList<String> friend_list_to_add) {
        this.friendList = friend_list_to_add;
    }

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

    public ArrayList<String> getFriendList() {return this.friendList;}

    public ArrayList<String> getFriendListRequest() {return this.friend_request_list;}

    public ArrayList<String> getFriendRequestSentList() {
        return this.friendRequestSentList;
    }
    //init

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

    public void updateFriendList(String friendID) {
        this.friendList.add(friendID);
    }
    public void updateFriendRequestList(String userID) {this.friend_request_list.add(userID);}
    public void updateFriendRequestSentList(String userID) {this.friendRequestSentList.add(userID);};

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

    private void addFriend(String userID) {this.friendList.add(userID);}


    public void acceptFriendRequest(String userID) {
        if (this.friend_request_list.contains(userID)) {
            this.friend_request_list.remove(userID);
            this.addFriend(userID.trim().strip());
        }
    }

    public void acceptedRequest(String userID) {
        userID = userID.trim().strip();
        if (this.friendRequestSentList.contains(userID)) {
            this.friendRequestSentList.remove(userID);
            this.addFriend(userID);
        }

    }
    public void receiveFriendRequest(String userID) {
        this.friend_request_list.add(userID);
    }
    public void sendFriendRequest(Student student) {
        student.receiveFriendRequest(this.user_id);
        this.friendRequestSentList.add(student.user_id);
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
