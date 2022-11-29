package UseCases;

import Entities.Student;
import Entities.User;

import java.io.IOException;
import java.util.List;

public class FriendListManager extends UseCase{
    public FriendListManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public List<User> getFriendList(String userID) {
        try {
            Student user = (Student) this.ub.getUserByID(userID);
            return user.getFriendList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
