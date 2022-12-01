package UIController;

import Entities.Student;
import Entities.User;
import UseCases.CourseDataManager;
import UseCases.FriendListManager;
import UseCases.UserDataManager;

import java.util.ArrayList;

public class FriendListUIControl {
    private FriendListManager friendListManager;
    private User self;
    public FriendListUIControl(User self, CourseDataManager courseManager, UserDataManager userManager) {
        this.friendListManager = new FriendListManager(courseManager, userManager);
        this.self = self;
    }

    public ArrayList<Student> getFriendList() {
        return friendListManager.getFriendList(self.getUserID());
    }

}
