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
        //Updates Student entity data
        try {
            Student user = (Student) this.ub.getUserByID(userId);
            Student friend = (Student) this.ub.getUserByID(friendID);
            System.out.println(user.getFullName() + " accepted " + friend.getFullName());

            user.acceptFriendRequest(friendID);

            System.out.println("Added friend to Entity model");
            this.ub.updateFriendRequestList(user);

            this.ub.updateFriendList(user);
            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptedRequest(String friendID, String userID) {
        //Updates Student entity data
        try{
            Student friend = (Student) this.ub.getUserByID(friendID);
            friend.acceptedRequest(userID);
            this.ub.updateFriendRequestsSentList(friend);
            this.ub.updateFriendList(friend);
            System.out.println("Friend's friend list: " + friend.getFriendList().toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
