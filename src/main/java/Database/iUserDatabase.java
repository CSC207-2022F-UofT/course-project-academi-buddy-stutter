package Database;


import Users.Admin;
import Users.Student;
import Users.User;

import java.util.ArrayList;

public interface iUserDatabase {

    void getData();

    void refreshData();

    ArrayList<String> getUserIDList();

    boolean addStudentUser(Student student);

    boolean addAdminUser(Admin admin);

    boolean removeUser(User user);

    User getUserByID(String userID);

    boolean userExists(User user);


}
