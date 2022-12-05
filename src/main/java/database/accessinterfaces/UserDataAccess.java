package database.accessinterfaces;

import entities.*;

import java.io.IOException;
import java.util.ArrayList;

public interface UserDataAccess {
    ArrayList<String> getUserIDList();
    boolean addUser(User user);
    boolean addStudentUser(Student student) throws IOException;
    void updateStudentCourses(Student student);
    boolean removeUser(String userID);

    User getUserByID(String userID) throws IOException;
    ArrayList<String> getCommonSessionCode(String selfUserID, String targetUserID) throws IOException;

    boolean exist(User user);


    boolean existByID(String ID);

    // FriendList Methods
    void updateFriendList(Student student);
    void updateFriendRequestList(Student student);
    void updateFriendRequestsSentList(Student student);
    ArrayList<String> getAdminIDs();
}

