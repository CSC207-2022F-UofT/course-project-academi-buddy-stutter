package UseCases;

import Entities.Student;

import java.io.IOException;
import java.util.ArrayList;

public class FriendListManager extends UseCase{
    public FriendListManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public ArrayList<String> getFriendList(String userID) {
        try {
            ArrayList<String> friendList;
            Student user = (Student) this.ub.getUserByID(userID);
            friendList = user.getFriendList();
            return friendList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getFriendRequestList(String userID) {
        try {
            ArrayList<String> friendRequestList;
            Student user = (Student) this.ub.getUserByID(userID);
            friendRequestList = user.getFriendListRequest();
            return friendRequestList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFriendFullName(String userID) {
        try {
            return this.ub.getUserByID(userID).getFullName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean acceptFriendRequest(String userId, String friendID) {
        try {
            System.out.println("this is my id: " + userId);
            Student user = (Student) this.ub.getUserByID(userId);
            System.out.println("this is friend id: " + friendID);

            Student friend = (Student) this.ub.getUserByID(friendID);
            System.out.println("i'm finally here");

            user.acceptFriendRequest(userId);
            System.out.println(user.getFullName() + " accepted " + friend.getFullName());
            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFriendList(String userID) {
        try {
            Student user = (Student) this.ub.getUserByID(userID);
            this.ub.updateFriendList(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptedRequest(String friendID, String userID) {
        try{
            Student friend = (Student) this.ub.getUserByID(friendID);
            friend.acceptedRequest(userID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
