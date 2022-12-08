package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.usecases.FriendListManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Executable;
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
    void acceptAndUpdateFriendRequestTest() throws Exception {
        ArrayList<String> friendAList = new ArrayList<>();
        ArrayList<String> friendCList = new ArrayList<>();
        friendAList.add(STUDENTB.getUserID());
        friendCList.add(STUDENTA.getUserID());
        System.out.println(friendCList);

        // C is inside request list
        Assertions.assertEquals(friendAList, friendListManager.getFriendList(STUDENTA.getUserID()));
        // A is inside request sent list
        Assertions.assertEquals(friendCList, friendListManager.getFriendRequestSentList(STUDENTC.getUserID()));

        // accept friend request and update both Student's friend list
        friendListManager.acceptFriendRequest(STUDENTA.getUserID(), STUDENTC.getUserID());
        friendListManager.acceptedRequest(STUDENTC.getUserID(), STUDENTA.getUserID());

        // friend list has been updated
        friendAList.add(STUDENTC.getUserID());
        friendCList.remove(STUDENTA.getUserID());
        Assertions.assertEquals(friendAList, friendListManager.getFriendList(STUDENTA.getUserID()));
        Assertions.assertEquals(friendCList, friendListManager.getFriendRequestSentList(STUDENTC.getUserID()));

        // both request and request sent list has been cleared
        Assertions.assertEquals(new ArrayList<>(), friendListManager.getFriendRequestList(STUDENTA.getUserID()));
        Assertions.assertEquals(new ArrayList<>(), friendListManager.getFriendRequestSentList(STUDENTC.getUserID()));
    }

    @Test
    void sendAndReceiveFriendRequestTest() throws Exception {
        ArrayList<String> friendListB = new ArrayList<>();
        ArrayList<String> friendListC = new ArrayList<>();

        // Check no friend request has been sent for B and C
        Assertions.assertEquals(friendListB, friendListManager.getFriendRequestSentList(STUDENTB.getUserID()));
        Assertions.assertEquals(friendListC, friendListManager.getFriendRequestList(STUDENTC.getUserID()));

        // B send friend request to C
        friendListManager.sendFriendRequest(STUDENTB.getUserID(), STUDENTC.getUserID());
        friendListManager.receiveFriendRequest(STUDENTB.getUserID(), STUDENTC.getUserID());

        // Check B and C's friend request sent and request list
        friendListB.add(STUDENTC.getUserID());
        friendListC.add(STUDENTB.getUserID());
        Assertions.assertEquals(friendListB, friendListManager.getFriendRequestSentList(STUDENTB.getUserID()));
        Assertions.assertEquals(friendListC, friendListManager.getFriendRequestList(STUDENTC.getUserID()));
    }

    @Test
    public void isRequestSentTest() {
        Assertions.assertFalse(friendListManager.isRequestSent(STUDENTA.getUserID(), STUDENTC.getUserID()));
        Assertions.assertFalse(friendListManager.isRequestSent(STUDENTA.getUserID(), STUDENTB.getUserID()));
        Assertions.assertTrue(friendListManager.isRequestSent(STUDENTB.getUserID(), STUDENTC.getUserID()));
    }

}