package UIController;

import Entities.Student;
import Entities.User;
import UseCases.CourseDataManager;
import UseCases.FriendListManager;
import UseCases.UserDataManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FriendListUIControl {
    private FriendListManager friendListManager;
    private User user;
    public FriendListUIControl(User user, CourseDataManager courseManager, UserDataManager userManager) {
        this.friendListManager = new FriendListManager(courseManager, userManager);
        this.user = user;
    }

    public String getUserId() {
        return user.getUserID();
    }
    public ArrayList<String> getFriendList() { return friendListManager.getFriendList(user.getUserID());}
    public ArrayList<String> getFriendRequestList() {return friendListManager.getFriendRequestList(user.getUserID());}
    public String getFriendFullName(String userID) {return friendListManager.getFriendFullName(userID);}
    public boolean isRequestSent(String friendID) {return friendListManager.isRequestSent(user.getUserID(), friendID);}
    public void acceptedRequest(String friendID, String userID) {
        friendListManager.acceptedRequest(friendID, userID);
    }
    public boolean acceptFriendRequest(String userID, String friendID) {
        return friendListManager.acceptFriendRequest(userID, friendID);
    }
    public void sendFriendRequest(String userID, String friendID) {
        friendListManager.sendFriendRequest(userID, friendID);
    }
    public void receiveFriendRequest(String userID, String friendID) {
        friendListManager.receiveFriendRequest(userID, friendID);
    }
}