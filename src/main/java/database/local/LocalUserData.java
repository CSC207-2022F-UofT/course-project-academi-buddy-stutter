package database.local;

import database.accessinterfaces.UserDataAccess;
import model.entities.Admin;
import model.entities.Student;
import model.entities.User;

import java.util.ArrayList;

public class LocalUserData implements UserDataAccess {
    ArrayList<User> allUsers;

    public LocalUserData(){
        allUsers = new ArrayList<>();
    }
    @Override
    public ArrayList<String> getUserIDList() {
        ArrayList<String> idList = new ArrayList<>();
        for(User user: this.allUsers){
            idList.add(user.getUserID());
        }
        return idList;
    }

    @Override
    public boolean addUser(User user) {
        if(!existByID(user.getUserID())){
            allUsers.add(user);
            return true;
        }
        return false;
    }

    @Override
    public void addStudentUser(Student student){
        if(!existByID(student.getUserID())){
            allUsers.add(student);
        }
    }

    @Override
    public void updateStudentCourses(Student student){
        updateArrayListOfUser(student);
    }

    @Override
    public void removeUser(String userID) {
        if(existByID(userID)){
            for (User user:allUsers){
                if (user.getUserID().equals(userID)){
                    allUsers.remove(user);
                    return;
                }
            }
        }
    }

    @Override
    public User getUserByID(String userID){
        for (User user:allUsers){
            if (user.getUserID().equals(userID)){
                return user;
            }
        }
        return null;
    }

    @Override
    public ArrayList<String> getCommonSessionCode(String selfUserID, String targetUserID){
        Student self = (Student) getUserByID(selfUserID);
        Student target = (Student) getUserByID(targetUserID);
        System.out.println(targetUserID);
        ArrayList<String> targetCourseCode = target.getEnrolledCourseCodes();
        ArrayList<String> commonCode = new ArrayList<>();
        for(String courseCode: self.getEnrolledCourseCodes()){
            if(targetCourseCode.contains(courseCode)){
                commonCode.add(courseCode);
            }
        }
        return commonCode;
    }

    @Override
    public boolean existByID(String ID) {
        for(User user: allUsers){
            if(user.getUserID().equals(ID)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateFriendList(Student student) {
        updateArrayListOfUser(student);
    }

    @Override
    public void updateFriendRequestList(Student student) {
        updateArrayListOfUser(student);
    }

    @Override
    public void updateFriendRequestsSentList(Student student) {
        updateArrayListOfUser(student);
    }

    @Override
    public void removeFromFriendList(Student student, Student friend) {
        student.removeFriendList(friend.getUserID());
        friend.removeFriendList(student.getUserID());
    }

    @Override
    public void removeFromFriendRequestSentList(Student student, Student friend) {
        student.removeFriendRequestSentList(friend.getUserID());
        friend.removeFriendRequestList(student.getUserID());
    }

    @Override
    public void removeFromFriendRequestList(Student student, Student friend) {
        student.removeFriendRequestList(friend.getUserID());
        friend.removeFriendRequestSentList(student.getUserID());
    }

    @Override
    public ArrayList<String> getAdminIDs() {
        ArrayList<String> adminIDs = new ArrayList<>();
        for (User user: this.allUsers) {
            if (user instanceof Admin) {
                adminIDs.add(user.getUserID());
            }
        }
        return adminIDs;
    }

    private void updateArrayListOfUser(Student student){
        ArrayList<User> newUserList = new ArrayList<>();
        for (User user: allUsers){
            if (!user.getUserID().equals(student.getUserID())){
                newUserList.add(user);
            }
            else{
                newUserList.add(student);
            }
        }
        allUsers = newUserList;
    }
}
