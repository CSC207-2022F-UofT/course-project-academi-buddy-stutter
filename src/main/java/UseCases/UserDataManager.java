package UseCases;

import Entities.*;
import Gateways.DatabaseInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface UserDataManager {
    ArrayList<String> getUserIDList();
    boolean addUser(User user);
    boolean addStudentUser(Student student) throws IOException;
    void updateStudentCourses(Student student);


    boolean addAdminUser(Admin admin) throws IOException;

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

