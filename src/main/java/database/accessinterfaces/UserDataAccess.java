package database.accessinterfaces;

import model.entities.Student;
import model.entities.User;

import java.util.ArrayList;

public interface UserDataAccess {
    ArrayList<String> getUserIDList();

    boolean addUser(User user);

    void addStudentUser(Student student);

    void updateStudentCourses(Student student);

    void removeUser(String userID);

    User getUserByID(String userID);

    ArrayList<String> getCommonSessionCode(String selfUserID, String targetUserID);

    boolean existByID(String ID);

    // FriendList Methods
    void updateFriendList(Student student);

    void updateFriendRequestList(Student student);

    void updateFriendRequestsSentList(Student student);

    void removeFromFriendList(Student student, Student friend);

    void removeFromFriendRequestSentList(Student student, Student friend);

    void removeFromFriendRequestList(Student student, Student friend);

    ArrayList<String> getAdminIDs();
}

