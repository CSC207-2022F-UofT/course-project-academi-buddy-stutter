package entities;

import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalTempDataFactory;
import database.local.LocalUserData;
import model.entities.Course;
import model.entities.InterestTag;
import model.entities.Label;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.usecases.ProfileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest extends LocalTempDataFactory {

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

    ProfileManager profileManager = new ProfileManager(cb, ub, tb);

    @Test
    void setAndGetTabsOfInterestsTest() {
        ArrayList<InterestTag> tags = new ArrayList<>();
        InterestTag tag1 = new InterestTag("tag1");
        InterestTag tag2 = new InterestTag("tag2");

        tags.add(tag1);
        tags.add(tag2);

        STUDENTA.setTabs_of_interests(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), STUDENTA.getTags());
        STUDENTA.setTabs_of_interests(tags);
        Assertions.assertEquals(tags, STUDENTA.getTags());
    }

    @Test
    void setAndGetEnrolledCoursesTest() {
        ArrayList<String> courses = new ArrayList<>();
        String code = "CSC207";
        String code2 = "CSC777";
        courses.add(code);
        courses.add(code2);
        STUDENTA.setEnrolledCourses(courses);
        Assertions.assertEquals(courses, STUDENTA.getEnrolledCourseCodes());
    }

    @Test
    void updateStudentTOITest() {
        InterestTag tag1 = new InterestTag("tag1");
        ArrayList<InterestTag> tags = STUDENTA.getTags();
        tags.add(tag1);
        // Add this tag from list because it's selected
        STUDENTA.updateStudentTOI(tag1, true);
        Assertions.assertEquals(tags, STUDENTA.getTags());

        // Remove this tag from list because it's not selected
        STUDENTA.updateStudentTOI(tag1, false);
        tags.remove(tag1);
        Assertions.assertEquals(tags, STUDENTA.getTags());
    }

    @Test
    void getAndUpdateLabelTest() {
        Label label1 = new Label("label 1");
        ArrayList<Label> lables = STUDENTA.getLabels();
        // Add label to label list
        lables.add(label1);
        STUDENTA.updateLabel(label1, true);
        Assertions.assertTrue(STUDENTA.getLabels().contains(label1));

        // Remove label from label list
        STUDENTA.updateLabel(label1, false);
        lables.remove(label1);
        Assertions.assertFalse(STUDENTA.getLabels().contains(label1));
    }

    @Test
    void addCourseTest() {
        ArrayList<String> courses = new ArrayList<>();
        courses.add("999");
        courses.add("888");
        // Check current courses
        Assertions.assertEquals(courses, STUDENTA.getEnrolledCourseCodes());

        // Add new course
        courses.add("777");
        STUDENTA.addCourse(COURSEC);
        Assertions.assertEquals(courses, STUDENTA.getEnrolledCourseCodes());
    }

    @Test
    void removeCourseTest() {
        ArrayList<String> courses = new ArrayList<>();
        courses.add("999");
        courses.add("888");
        // Check current courses
        Assertions.assertEquals(courses, STUDENTA.getEnrolledCourseCodes());

        // Remove COURSEB from the list
        STUDENTA.removeCourse(COURSEB);
        courses.remove("888");
        Assertions.assertEquals(courses, STUDENTA.getEnrolledCourseCodes());
    }

    @Test
    void GetEmailTest() {
        String expected = "Hello";
        STUDENTA.setEmail("Hello");
        String actual =  STUDENTA.getEmail();
        assertEquals(expected , actual);
    }

    @Test
    void getFriendListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("222");
        Assertions.assertEquals(friends, STUDENTA.getFriendList());
    }
    @Test
    void getFriendListRequestTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("333");
        Assertions.assertEquals(friends, STUDENTA.getFriendListRequest());
    }

    @Test
    void getFriendRequestSentListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("111");
        Assertions.assertEquals(friends, STUDENTC.getFriendRequestSentList());
    }

    @Test
    void setFriendListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("111");
        STUDENTC.setFriendList(friends);
        Assertions.assertEquals(friends, STUDENTC.getFriendList());
        STUDENTC.setFriendList(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), STUDENTC.getFriendList());
    }

    @Test
    void setFriendRequestListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("333");
        STUDENTB.setFriendRequestList(friends);
        Assertions.assertEquals(friends, STUDENTB.getFriendListRequest());
        STUDENTB.setFriendRequestList(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), STUDENTB.getFriendListRequest());
    }

    @Test
    void setFriendRequestSentListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("333");
        STUDENTB.setFriendRequestSentList(friends);
        Assertions.assertEquals(friends, STUDENTB.getFriendRequestSentList());
        STUDENTB.setFriendRequestSentList(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), STUDENTB.getFriendRequestSentList());
    }

    @Test
    void updateFriendListTest() {
        String friendID = "444";
        ArrayList<String> friends = new ArrayList<>();
        friends.add("222");
        Assertions.assertEquals(friends, STUDENTA.getFriendList());
        STUDENTA.updateFriendList(friendID);
        friends.add(friendID);
        Assertions.assertEquals(friends, STUDENTA.getFriendList());

    }

    @Test
    void updateFriendRequestListTest() {
        String friendID = "444";
        ArrayList<String> friends = new ArrayList<>();
        friends.add("333");
        Assertions.assertEquals(friends, STUDENTA.getFriendListRequest());
        STUDENTA.updateFriendRequestList(friendID);
        friends.add(friendID);
        Assertions.assertEquals(friends, STUDENTA.getFriendListRequest());
    }

    @Test
    void updateFriendRequestSentListTest() {
        String friendID = "444";
        ArrayList<String> friends = new ArrayList<>();
        Assertions.assertEquals(friends, STUDENTA.getFriendRequestSentList());
        STUDENTA.updateFriendRequestSentList(friendID);
        friends.add(friendID);
        Assertions.assertEquals(friends, STUDENTA.getFriendRequestSentList());
    }

    @Test
    void acceptFriendRequestTest() {
        ArrayList<String> requests = new ArrayList<>();
        requests.add("333");
        ArrayList<String> friends = new ArrayList<>();
        friends.add("222");
        Assertions.assertEquals(requests, STUDENTA.getFriendListRequest());
        Assertions.assertEquals(friends, STUDENTA.getFriendList());
        STUDENTA.acceptFriendRequest("333");
        requests.remove("333");
        friends.add("333");
        Assertions.assertEquals(requests, STUDENTA.getFriendListRequest());
        Assertions.assertEquals(friends, STUDENTA.getFriendList());
    }

    @Test
    void acceptedRequestTest() {
        ArrayList<String> requestSent = new ArrayList<>();
        requestSent.add("111");
        ArrayList<String> friends = new ArrayList<>();
        Assertions.assertEquals(requestSent, STUDENTC.getFriendRequestSentList());
        Assertions.assertEquals(friends, STUDENTC.getFriendList());
        STUDENTC.acceptedRequest("111");
        requestSent.remove("111");
        Assertions.assertEquals(requestSent, STUDENTC.getFriendRequestSentList());
        friends.add("111");
        Assertions.assertEquals(friends, STUDENTC.getFriendList());
    }

    @Test
    void receiveFriendRequestTest() {
        ArrayList<String> request = new ArrayList<>();
        Assertions.assertEquals(request, STUDENTC.getFriendListRequest());
        request.add("444");
        STUDENTC.receiveFriendRequest("444");
        Assertions.assertEquals(request, STUDENTC.getFriendListRequest());
    }

    @Test
    void sendFriendRequestTest() {
        ArrayList<String> requestSend = new ArrayList<>();
        Assertions.assertEquals(requestSend, STUDENTB.getFriendRequestSentList());
        requestSend.add("444");
        STUDENTB.sendFriendRequest("444");
        Assertions.assertEquals(requestSend, STUDENTB.getFriendRequestSentList());
    }

    @Test
    void removeFriendListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("222");
        Assertions.assertEquals(friends, STUDENTA.getFriendList());
        STUDENTA.removeFriendList("222");
        friends.remove("222");
        Assertions.assertEquals(friends, STUDENTA.getFriendList());
    }

    @Test
    void removeFriendRequestListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("333");
        Assertions.assertEquals(friends, STUDENTA.getFriendListRequest());
        STUDENTA.removeFriendRequestList("333");
        friends.remove("333");
        Assertions.assertEquals(friends, STUDENTA.getFriendListRequest());
    }

    @Test
    void removeFriendRequestSentListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("111");
        Assertions.assertEquals(friends, STUDENTC.getFriendRequestSentList());
        STUDENTC.removeFriendRequestSentList("111");
        friends.remove("111");
        Assertions.assertEquals(friends, STUDENTC.getFriendRequestSentList());
    }
}
