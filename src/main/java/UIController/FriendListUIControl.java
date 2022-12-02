package UIController;

import Entities.Student;
import Entities.User;
import UseCases.CourseDataManager;
import UseCases.FriendListManager;
import UseCases.UserDataManager;

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

    public ArrayList<String> getFriendList() {
        return friendListManager.getFriendList(user.getUserID());
    }

    public ArrayList<String> getFriendRequestList() {return friendListManager.getFriendRequestList(user.getUserID());}
    public String getFriendFullName(String userID) {return friendListManager.getFriendFullName(userID);}

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
