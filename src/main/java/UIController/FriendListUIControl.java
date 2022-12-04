package UIController;

import Entities.User;
import UseCases.CourseDataCloud;
import UseCases.FriendListManager;
import UseCases.UserDataCloud;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FriendListUIControl {
    private FriendListManager friendListManager;
    private User user;
    public FriendListUIControl(User user, CourseDataCloud courseManager, UserDataCloud userManager) {
        this.friendListManager = new FriendListManager(courseManager, userManager);
        this.user = user;
    }

    public String getUserId() {
        return user.getUserID();
    }

    public ArrayList<String> getFriendList() { return friendListManager.getFriendList(user.getUserID());}

    public ArrayList<String> getFriendRequestList() {return friendListManager.getFriendRequestList(user.getUserID());}
    public ArrayList<String> getFriendRequestSentList() {return friendListManager.getFriendRequestSentList(user.getUserID());}

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
    // FriendListFrame Helper Function
    public String[] IdsToFullNames(@NotNull ArrayList<String> ids) {
        ArrayList<String> fullNames = new ArrayList<>();
        String[] fullNamesArr = new String[ids.size()];
        for (String id: ids) {
            fullNames.add(friendListManager.getFriendFullName(id.trim().strip()));
        }
        fullNamesArr = fullNames.toArray(fullNamesArr);
        return fullNamesArr;
    }

}