package usecases;

import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import model.usecases.FriendListManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class FriendListManagerTest extends LocalTempDataFactory {

    final ArrayList<?> managers;

    {
        try {
            managers = super.initializeStaticDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);

    final FriendListManager friendListManager = new FriendListManager(cb, ub, tb);

    @Test
    void acceptAndUpdateFriendRequestTest() {
        ArrayList<String> friendAList = new ArrayList<>();
        ArrayList<String> friendCList = new ArrayList<>();
        friendAList.add(STUDENT_B.getUserID());
        friendCList.add(STUDENT_A.getUserID());
        System.out.println(friendCList);

        // C is inside request list
        Assertions.assertEquals(friendAList, friendListManager.getFriendList(STUDENT_A.getUserID()));
        // A is inside request sent list
        Assertions.assertEquals(friendCList, friendListManager.getFriendRequestSentList(STUDENT_C.getUserID()));

        // accept friend request and update both Student's friend list
        friendListManager.acceptFriendRequest(STUDENT_A.getUserID(), STUDENT_C.getUserID());
        friendListManager.acceptedRequest(STUDENT_C.getUserID(), STUDENT_A.getUserID());

        // friend list has been updated
        friendAList.add(STUDENT_C.getUserID());
        friendCList.remove(STUDENT_A.getUserID());
        Assertions.assertEquals(friendAList, friendListManager.getFriendList(STUDENT_A.getUserID()));
        Assertions.assertEquals(friendCList, friendListManager.getFriendRequestSentList(STUDENT_C.getUserID()));

        // both request and request sent list has been cleared
        Assertions.assertEquals(new ArrayList<>(), friendListManager.getFriendRequestList(STUDENT_A.getUserID()));
        Assertions.assertEquals(new ArrayList<>(), friendListManager.getFriendRequestSentList(STUDENT_C.getUserID()));
    }

    @Test
    void sendAndReceiveFriendRequestTest() {
        ArrayList<String> friendListB = new ArrayList<>();
        ArrayList<String> friendListC = new ArrayList<>();

        // Check no friend request has been sent for B and C
        Assertions.assertEquals(friendListB, friendListManager.getFriendRequestSentList(STUDENT_B.getUserID()));
        Assertions.assertEquals(friendListC, friendListManager.getFriendRequestList(STUDENT_C.getUserID()));

        // B send friend request to C
        friendListManager.sendFriendRequest(STUDENT_B.getUserID(), STUDENT_C.getUserID());
        friendListManager.receiveFriendRequest(STUDENT_B.getUserID(), STUDENT_C.getUserID());

        // Check B and C's friend request sent and request list
        friendListB.add(STUDENT_C.getUserID());
        friendListC.add(STUDENT_B.getUserID());
        Assertions.assertEquals(friendListB, friendListManager.getFriendRequestSentList(STUDENT_B.getUserID()));
        Assertions.assertEquals(friendListC, friendListManager.getFriendRequestList(STUDENT_C.getUserID()));
    }

    @Test
    void isRequestSentTest() {
        Assertions.assertFalse(friendListManager.isRequestSent(STUDENT_A.getUserID(), STUDENT_C.getUserID()));
        Assertions.assertFalse(friendListManager.isRequestSent(STUDENT_A.getUserID(), STUDENT_B.getUserID()));
        Assertions.assertTrue(friendListManager.isRequestSent(STUDENT_B.getUserID(), STUDENT_C.getUserID()));
    }

}