package UseCases;

import Entities.Student;
import Entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FriendListManager extends UseCase{
    public FriendListManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public ArrayList<Student> getFriendList(String userID) {
        try {
            Student student = (Student) this.ub.getUserByID(userID);
            return student.getFriendList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
