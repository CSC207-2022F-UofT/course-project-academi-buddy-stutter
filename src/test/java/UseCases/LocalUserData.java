package UseCases;

import Entities.Admin;
import Entities.Student;
import Entities.User;

import java.util.ArrayList;

public class LocalUserData implements UserDataManager{
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
    public boolean addStudentUser(Student student){
        if(!existByID(student.getUserID())){
            allUsers.add(student);
            return true;
        }
        return false;
    }

    @Override
    public void updateStudentCourses(Student student){
        updateArrayListOfUser(student);
    }

    @Override
    public boolean addAdminUser(Admin admin){
        if(!existByID(admin.getUserID())){
            allUsers.add(admin);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUser(String userID) {
        if(existByID(userID)){
            for (User user:allUsers){
                if (user.getUserID().equals(userID)){
                    allUsers.remove(user);
                    return true;
                }
            }
        }
        return false;
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
    public boolean exist(User user) {
        return existByID(user.getUserID());
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
