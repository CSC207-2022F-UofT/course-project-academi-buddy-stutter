package model.usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.entities.Student;

import java.util.ArrayList;

/**
 * Use case operations for the friend list
 */
public class FriendListManager extends UseCase{

    /**
     * Initializer
     * @param courseDatabase the course database
     * @param userDatabase the user database
     */
    public FriendListManager(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess tagDatabase) {
        super(courseDatabase, userDatabase, tagDatabase);
    }

    /**
     * Get the friend list for the current user.
     * @param userID The user id to get the friend list for
     * @return An ArrayList of Strings of friends ids.
     */
    public ArrayList<String> getFriendList(String userID) {
        ArrayList<String> friendList;
        Student user = (Student) this.ub.getUserByID(userID);
        friendList = user.getFriendList();
        return friendList;
    }

    /**
     * Get the friend request list for the current user
     * @param userID the id of the current user.
     * @return An ArrayList of Strings of friend requests for the user.
     */
    public ArrayList<String> getFriendRequestList(String userID) {
        ArrayList<String> friendRequestList;
        Student user = (Student) this.ub.getUserByID(userID);
        friendRequestList = user.getFriendListRequest();
        return friendRequestList;
    }

    /**
     * Gets ArrayList of strings for the friend request sent list
     * @param userID The user's id to check
     * @return An ArrayList of Strings of friend request sent
     */
    public ArrayList<String> getFriendRequestSentList(String userID) {
        ArrayList<String> friendRequestSentList;
        Student user = (Student) this.ub.getUserByID(userID);
        friendRequestSentList = user.getFriendRequestSentList();
        return friendRequestSentList;
    }

    /**
     * Gets the friend's full name.
     * @param userID The id of the user to check
     * @return the full name of the user.
     */
    public String getFriendFullName(String userID) {
        return this.ub.getUserByID(userID).getFullName();
    }

    /**
     * To accept someone's friend request
     *
     * @param userId   the user to accept the friend request
     * @param friendID the friend id to accept
     */
    public void acceptFriendRequest(String userId, String friendID) {
        //Updates Student entity data
        Student user = (Student) this.ub.getUserByID(userId);
        Student friend = (Student) this.ub.getUserByID(friendID);
        System.out.println(user.getFullName() + " accepted " + friend.getFullName());

        user.acceptFriendRequest(friendID);

        this.ub.updateFriendRequestList(user);

        this.ub.updateFriendList(user);

    }

    /**
     * Updates the database for accepting a friend request
     * @param friendID the friend's id
     * @param userID the user's id
     */
    public void acceptedRequest(String friendID, String userID) {
        //Updates Student entity data
        Student friend = (Student) this.ub.getUserByID(friendID);
        friend.acceptedRequest(userID);
        this.ub.updateFriendRequestsSentList(friend);
        this.ub.updateFriendList(friend);

    }

    /**
     * Sending a friend request from user to friend.
     * @param userID the user's id
     * @param friendID the friend's id
     */
    public void sendFriendRequest(String userID, String friendID) {
        // Send friend requests to friendID
        Student user = (Student) this.ub.getUserByID(userID);
        user.sendFriendRequest(friendID);
        this.ub.updateFriendRequestsSentList(user);
    }

    /**
     * Receiving a friend request from friend to user.
     * @param userID the user's id
     * @param friendID the friend's id
     */
    public void receiveFriendRequest(String userID, String friendID) {
        // receive friend requests to friendID
        Student friend = (Student) this.ub.getUserByID(friendID);
        friend.receiveFriendRequest(userID);
        this.ub.updateFriendRequestList(friend);
    }

    /**
     * checks if the request has been sent
     * @param userID the user's id
     * @param friendID the friend's id
     * @return True if the friend request has been sent successfully.
     */
    public boolean isRequestSent(String userID, String friendID) {
        // check if friend request is sent or is friend already
        Student user = (Student) this.ub.getUserByID(userID);
        Student friend = (Student) this.ub.getUserByID(friendID);
        if (user.getFriendList().contains(friendID)) {
            return false;
        } else return !user.getFriendListRequest().contains(friendID) && !friend.getFriendListRequest().contains(userID);

    }
}