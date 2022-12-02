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

    public String getUserId() {
        return self.getUserID();
    }

    public ArrayList<Student> getFriendList() {
        return friendListManager.getFriendList(self.getUserID());
    }

    public ArrayList<Student> getFriendRequestList() {
        return friendListManager.getFriendRequestList(self.getUserID());
    }

    public boolean acceptFriendRequest(String userID, String friendID) {
        System.out.println("friendlist ui control");
        return friendListManager.acceptFriendRequest(userID, friendID);
    }

    public void updateFriendList(String userID) {
        friendListManager.updateFriendList(userID);
    }

    public void acceptedRequest(String friendID, String userID) {
        friendListManager.acceptedRequest(friendID, userID);
    }

}
