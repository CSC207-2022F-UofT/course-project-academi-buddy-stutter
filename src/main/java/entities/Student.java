package entities;
import java.util.*;

/**
 * Implements Student class that represents a Student type user
 */
public class Student extends User{
    private ArrayList<InterestTag> tags_of_interests;
    private ArrayList<Label> labels;
    private String email;
    private ArrayList<String> enrolled_course_codes;
    private ArrayList<String> friendList;
    private ArrayList<String> friend_request_list;
    private ArrayList<String> friendRequestSentList;

    /**
     * Constructs a Student user
     * @param UID user id
     * @param UPass user password
     * @param full_name user's full name
     * @param info user info
     */
    public Student(String UID, String UPass, String full_name, String info){
        super(UID, UPass, full_name, info);
        this.tags_of_interests = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.enrolled_course_codes = new ArrayList<>();
        this.friendList = new ArrayList<>();
        this.friend_request_list = new ArrayList<>();
        this.friendRequestSentList = new ArrayList<>();
    }

    public void setTabs_of_interests(ArrayList<InterestTag> tags_of_interests) {
        this.tags_of_interests = tags_of_interests;
    }

    /**
     * Set courses that the student is enrolled in
     * @param enrolled_courses a list of courses to be added to student
     */
    public void setEnrolledCourses(ArrayList<String> enrolled_courses) {
        ArrayList<String> courseCodeList = new ArrayList<>();
        for (String course: enrolled_courses){
            courseCodeList.add(course);
        }
        this.enrolled_course_codes = courseCodeList;
    }

    /**
     * Sets student's Email
     * @param email user's Email
     */
    public void setEmail(String email){this.email = email;}

    /**
     * @return user's Email
     */
    public String getEmail(){return email;}

    /**
     * @return user's interest tags
     */
    public ArrayList<InterestTag> getTags() {return tags_of_interests;}

    /**
     * @return user's labels
     */
    public ArrayList<Label> getLabels() {return labels;}

    /**
     * @return a list of course codes from courses that the user is enrolled in
     */
    public ArrayList<String> getEnrolledCourseCodes() {
        ArrayList<String> courseCodes = new ArrayList<>();
        courseCodes.addAll(this.enrolled_course_codes);
        return courseCodes;
    }

    /**
     * @return a list of user's friends
     */
    public ArrayList<String> getFriendList() {return this.friendList;}

    /**
     * @return a list of pending friend requests
     */
    public ArrayList<String> getFriendListRequest() {return this.friend_request_list;}

    /**
     * @return a list of users that are waiting to be accepted
     */
    public ArrayList<String> getFriendRequestSentList() {
        return this.friendRequestSentList;
    }

    /**
     * Updates user's interest tags
     * @param tag an interest tag
     * @param selected whether the tag is selected or not
     */
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

    /**
     * Updates user's labels
     * @param label a label
     * @param selected whether the label is selected or not
     */
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

    public void setFriendList(ArrayList<String> friends) { this.friendList = friends;}
    public void setFriendRequestList(ArrayList<String> requests) { this.friend_request_list = requests;}
    public void setFriendRequestSentList(ArrayList<String> sentList) {this.friendRequestSentList = sentList;}

    /**
     * Updates friend list
     * @param friendID a user id to be added to user's friend list
     */
    public void updateFriendList(String friendID) {
        this.friendList.add(friendID);
    }

    /**
     * Updates friend request list
     * @param userID a user id to be added to user's friend request list
     */
    public void updateFriendRequestList(String userID) {this.friend_request_list.add(userID);}

    /**
     * Updates friend request sent list
     * @param userID a user id to be added to user's friend request sent list
     */
    public void updateFriendRequestSentList(String userID) {this.friendRequestSentList.add(userID);};

    /**
     * Add a user as friend
     * @param userID a user id to be added as user's friend
     */
    private void addFriend(String userID) {this.friendList.add(userID);}

    /**
     * Accept a friend request
     * @param userID a user id to be accepted as a friend
     */
    public void acceptFriendRequest(String userID) {
        if (this.friend_request_list.contains(userID)) {
            this.friend_request_list.remove(userID);
            this.addFriend(userID.trim().strip());
        }
    }

    /**
     * Accepted friend request. Remove userID friend request sent list
     * @param userID a user id
     */
    public void acceptedRequest(String userID) {
        userID = userID.trim().strip();
        if (this.friendRequestSentList.contains(userID)) {
            this.friendRequestSentList.remove(userID);
            this.addFriend(userID);
        }
    }

    /**
     * Receive friend request
     * @param userID a user id
     */
    public void receiveFriendRequest(String userID) {
        this.friend_request_list.add(userID);
    }

    /**
     * Send friend request
     * @param friendID a user id
     */
    public void sendFriendRequest(String friendID) {
        this.friendRequestSentList.add(friendID);
    }

    /**
     * Add a course to user's enrolled course list
     * @param course a course
     * @return whether the course successfully added
     */
    public boolean addCourse(Course course){
        if(enrolled_course_codes.contains(course.getCourseCode())){
            return false;
        }
        enrolled_course_codes.add(course.getCourseCode());
        return true;
    }

    /**
     * Remove a course from user's enrolled course list
     * @param course a course
     * @return whether the course is successfully removed from user's enrolled course list
     */
    public boolean removeCourse(Course course){
        if(enrolled_course_codes.contains(course.getCourseCode())){
            enrolled_course_codes.remove(course.getCourseCode());
            return true;
        }
        return false;
    }

    /**
     * @param label a label
     * @return whether the label is selected by user
     */
    public boolean isLabelSelected(Label label){
        ArrayList<String> labelNames = new ArrayList<>();
        for(Label l: labels){
            labelNames.add(l.getName());
        }
        return labelNames.contains(label.getName());
    }

    /**
     * @param tag an interest tag
     * @return whether an interest tag is selected by user
     */
    public boolean isTagSelected(InterestTag tag){
        ArrayList<String> tagNames = new ArrayList<>();
        for(InterestTag t: tags_of_interests){
            tagNames.add(t.getName());
        }
        return tagNames.contains(tag.getName());
    }
}
