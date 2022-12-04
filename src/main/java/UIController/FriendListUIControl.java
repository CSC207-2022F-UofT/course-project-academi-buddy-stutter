package UIController;

import Entities.User;
import UseCases.CourseDataManager;
import UseCases.FriendListManager;
import UseCases.UserDataManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Implements FriendListUIControl for FriendListFrame
 */
public class FriendListUIControl {
    private FriendListManager friendListManager;
    private User user;
    public FriendListUIControl(User user, CourseDataManager courseManager, UserDataManager userManager) {
        this.friendListManager = new FriendListManager(courseManager, userManager);
        this.user = user;
    }

    /**
     * @return user id
     */
    public String getUserId() {
        return user.getUserID();
    }

    /**
     * @return a list of friends
     */
    public ArrayList<String> getFriendList() { return friendListManager.getFriendList(user.getUserID());}

    /**
     * @return a list of requested friends
     */
    public ArrayList<String> getFriendRequestList() {return friendListManager.getFriendRequestList(user.getUserID());}
    public ArrayList<String> getFriendRequestSentList() {return friendListManager.getFriendRequestSentList(user.getUserID());}

    /**
     * Get friend's full name
     * @param userID full name of the user id
     * @return full name of target user id
     */
    public String getFriendFullName(String userID) {return friendListManager.getFriendFullName(userID);}

    /**
     * @param friendID target user id that we want to determine from
     * @return whether friend request is sent to the target user id
     */
    public boolean isRequestSent(String friendID) {return friendListManager.isRequestSent(user.getUserID(), friendID);}

    /**
     * Accept a friend request
     * @param friendID user id of one user
     * @param userID user id of another user
     */
    public void acceptedRequest(String friendID, String userID) {
        friendListManager.acceptedRequest(friendID, userID);
    }

    /**
     * Accept Friend Request
     * @param userID user id of one user
     * @param friendID user id of another user
     * @return whether the two users are friends
     */
    public boolean acceptFriendRequest(String userID, String friendID) {
        return friendListManager.acceptFriendRequest(userID, friendID);
    }

    /**
     * Send Friend request
     * @param userID user id of one user
     * @param friendID user id of another user
     */
    public void sendFriendRequest(String userID, String friendID) {
        friendListManager.sendFriendRequest(userID, friendID);
    }

    /**
     * Get friends request from other users
     * @param userID user id of one user
     * @param friendID user id of another user
     */
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