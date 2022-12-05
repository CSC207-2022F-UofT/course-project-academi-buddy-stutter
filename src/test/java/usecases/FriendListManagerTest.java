package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class FriendListManagerTest extends LocalTempDataFactory {

    ArrayList<?> managers;

    {
        try {
            managers = super.initializeStaticDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    LocalUserData ub = (LocalUserData) managers.get(0);
    LocalCourseData cb = (LocalCourseData) managers.get(1);
    LocalTagData tb = (LocalTagData) managers.get(2);

    FriendListManager friendListManager = new FriendListManager(cb, ub, tb);

    @Test
    void acceptFriendRequest() throws Exception {
        ArrayList<String> friendAList = STUDENTA.getFriendList();
        ArrayList<String> friendCList = STUDENTC.getFriendList();

        // Send friend request and accept
        STUDENTC.sendFriendRequest(STUDENTA.getUserID());
        STUDENTA.acceptFriendRequest(STUDENTC.getUserID());
        friendAList.add(STUDENTC.getUserID());
        friendCList.add(STUDENTA.getUserID());

        ArrayList<String> updatedFriendAList = STUDENTA.getFriendList();
        ArrayList<String> updatedFriendCList = STUDENTC.getFriendList();

        Assertions.assertEquals(friendAList, updatedFriendAList);
        Assertions.assertEquals(friendCList, updatedFriendCList);
    }

    @Test
    void sendFriendRequest() throws Exception {
        ArrayList<String> friendListB = STUDENTB.getFriendList();
        ArrayList<String> friendListC = STUDENTC.getFriendList();

        STUDENTB.sendFriendRequest(STUDENTC.getUserID());
        STUDENTC.receiveFriendRequest(STUDENTB.getUserID());
        STUDENTC.acceptFriendRequest(STUDENTB.getUserID());

        ArrayList<String> updatedFriendListB = STUDENTB.getFriendList();
        ArrayList<String> updatedFriendListC = STUDENTC.getFriendList();

        friendListB.add(STUDENTC.getUserID());
        friendListC.add(STUDENTB.getUserID());

        Assertions.assertEquals(friendListB, updatedFriendListB);
        Assertions.assertEquals(friendListC, updatedFriendListC);
    }
}