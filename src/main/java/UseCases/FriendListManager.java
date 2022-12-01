package UseCases;

import Entities.Student;
import Entities.User;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FriendListManager extends UseCase{
    public FriendListManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public ArrayList<Student> getFriendList(String userID) {
        try {
            ArrayList<Student> friendList;
            Student user = (Student) this.ub.getUserByID(userID);
            friendList = user.getFriendList();
            return friendList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
