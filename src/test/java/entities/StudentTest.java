package entities;

import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalTempDataBuilder;
import database.local.LocalUserData;
import model.entities.InterestTag;
import model.entities.Label;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest extends LocalTempDataBuilder {

    final ArrayList<?> managers;

    {
        managers = super.initializeStaticDatabase();
    }

    final LocalUserData ub = (LocalUserData) managers.get(0);
    final LocalCourseData cb = (LocalCourseData) managers.get(1);
    final LocalTagData tb = (LocalTagData) managers.get(2);


    @Test
    void setAndGetTabsOfInterestsTest() {
        ArrayList<InterestTag> tags = new ArrayList<>();
        InterestTag tag1 = new InterestTag("tag1");
        InterestTag tag2 = new InterestTag("tag2");

        tags.add(tag1);
        tags.add(tag2);

        STUDENT_A.setTabs_of_interests(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), STUDENT_A.getTags());
        STUDENT_A.setTabs_of_interests(tags);
        Assertions.assertEquals(tags, STUDENT_A.getTags());
    }

    @Test
    void setAndGetEnrolledCoursesTest() {
        ArrayList<String> courses = new ArrayList<>();
        String code = "CSC207";
        String code2 = "CSC777";
        courses.add(code);
        courses.add(code2);
        STUDENT_A.setEnrolledCourses(courses);
        Assertions.assertEquals(courses, STUDENT_A.getEnrolledCourseCodes());
    }

    @Test
    void updateStudentTOITest() {
        InterestTag tag1 = new InterestTag("tag1");
        ArrayList<InterestTag> tags = STUDENT_A.getTags();
        tags.add(tag1);
        // Add this tag from list because it's selected
        STUDENT_A.updateStudentTOI(tag1, true);
        Assertions.assertEquals(tags, STUDENT_A.getTags());

        // Remove this tag from list because it's not selected
        STUDENT_A.updateStudentTOI(tag1, false);
        tags.remove(tag1);
        Assertions.assertEquals(tags, STUDENT_A.getTags());
    }

    @Test
    void getAndUpdateLabelTest() {
        Label label1 = new Label("label 1");
        ArrayList<Label> lables = STUDENT_A.getLabels();
        // Add label to label list
        lables.add(label1);
        STUDENT_A.updateLabel(label1, true);
        Assertions.assertTrue(STUDENT_A.getLabels().contains(label1));

        // Remove label from label list
        STUDENT_A.updateLabel(label1, false);
        lables.remove(label1);
        Assertions.assertFalse(STUDENT_A.getLabels().contains(label1));
    }

    @Test
    void addCourseTest() {
        ArrayList<String> courses = new ArrayList<>();
        courses.add("999");
        courses.add("888");
        // Check current courses
        Assertions.assertEquals(courses, STUDENT_A.getEnrolledCourseCodes());

        // Add new course
        courses.add("777");
        STUDENT_A.addCourse(COURSE_C);
        Assertions.assertEquals(courses, STUDENT_A.getEnrolledCourseCodes());
    }

    @Test
    void removeCourseTest() {
        ArrayList<String> courses = new ArrayList<>();
        courses.add("999");
        courses.add("888");
        // Check current courses
        Assertions.assertEquals(courses, STUDENT_A.getEnrolledCourseCodes());

        // Remove COURSE_B from the list
        STUDENT_A.removeCourse(COURSE_B);
        courses.remove("888");
        Assertions.assertEquals(courses, STUDENT_A.getEnrolledCourseCodes());
    }

    @Test
    void GetEmailTest() {
        String expected = "Hello";
        STUDENT_A.setEmail("Hello");
        String actual =  STUDENT_A.getEmail();
        assertEquals(expected , actual);
    }

    @Test
    void getFriendListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("222");
        Assertions.assertEquals(friends, STUDENT_A.getFriendList());
    }
    @Test
    void getFriendListRequestTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("333");
        Assertions.assertEquals(friends, STUDENT_A.getFriendListRequest());
    }

    @Test
    void getFriendRequestSentListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("111");
        Assertions.assertEquals(friends, STUDENT_C.getFriendRequestSentList());
    }

    @Test
    void setFriendListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("111");
        STUDENT_C.setFriendList(friends);
        Assertions.assertEquals(friends, STUDENT_C.getFriendList());
        STUDENT_C.setFriendList(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), STUDENT_C.getFriendList());
    }

    @Test
    void setFriendRequestListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("333");
        STUDENT_B.setFriendRequestList(friends);
        Assertions.assertEquals(friends, STUDENT_B.getFriendListRequest());
        STUDENT_B.setFriendRequestList(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), STUDENT_B.getFriendListRequest());
    }

    @Test
    void setFriendRequestSentListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("333");
        STUDENT_B.setFriendRequestSentList(friends);
        Assertions.assertEquals(friends, STUDENT_B.getFriendRequestSentList());
        STUDENT_B.setFriendRequestSentList(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), STUDENT_B.getFriendRequestSentList());
    }

    @Test
    void updateFriendListTest() {
        String friendID = "444";
        ArrayList<String> friends = new ArrayList<>();
        friends.add("222");
        Assertions.assertEquals(friends, STUDENT_A.getFriendList());
        STUDENT_A.updateFriendList(friendID);
        friends.add(friendID);
        Assertions.assertEquals(friends, STUDENT_A.getFriendList());

    }

    @Test
    void updateFriendRequestListTest() {
        String friendID = "444";
        ArrayList<String> friends = new ArrayList<>();
        friends.add("333");
        Assertions.assertEquals(friends, STUDENT_A.getFriendListRequest());
        STUDENT_A.updateFriendRequestList(friendID);
        friends.add(friendID);
        Assertions.assertEquals(friends, STUDENT_A.getFriendListRequest());
    }

    @Test
    void updateFriendRequestSentListTest() {
        String friendID = "444";
        ArrayList<String> friends = new ArrayList<>();
        Assertions.assertEquals(friends, STUDENT_A.getFriendRequestSentList());
        STUDENT_A.updateFriendRequestSentList(friendID);
        friends.add(friendID);
        Assertions.assertEquals(friends, STUDENT_A.getFriendRequestSentList());
    }

    @Test
    void acceptFriendRequestTest() {
        ArrayList<String> requests = new ArrayList<>();
        requests.add("333");
        ArrayList<String> friends = new ArrayList<>();
        friends.add("222");
        Assertions.assertEquals(requests, STUDENT_A.getFriendListRequest());
        Assertions.assertEquals(friends, STUDENT_A.getFriendList());
        STUDENT_A.acceptFriendRequest("333");
        requests.remove("333");
        friends.add("333");
        Assertions.assertEquals(requests, STUDENT_A.getFriendListRequest());
        Assertions.assertEquals(friends, STUDENT_A.getFriendList());
    }

    @Test
    void acceptedRequestTest() {
        ArrayList<String> requestSent = new ArrayList<>();
        requestSent.add("111");
        ArrayList<String> friends = new ArrayList<>();
        Assertions.assertEquals(requestSent, STUDENT_C.getFriendRequestSentList());
        Assertions.assertEquals(friends, STUDENT_C.getFriendList());
        STUDENT_C.acceptedRequest("111");
        requestSent.remove("111");
        Assertions.assertEquals(requestSent, STUDENT_C.getFriendRequestSentList());
        friends.add("111");
        Assertions.assertEquals(friends, STUDENT_C.getFriendList());
    }

    @Test
    void receiveFriendRequestTest() {
        ArrayList<String> request = new ArrayList<>();
        Assertions.assertEquals(request, STUDENT_C.getFriendListRequest());
        request.add("444");
        STUDENT_C.receiveFriendRequest("444");
        Assertions.assertEquals(request, STUDENT_C.getFriendListRequest());
    }

    @Test
    void sendFriendRequestTest() {
        ArrayList<String> requestSend = new ArrayList<>();
        Assertions.assertEquals(requestSend, STUDENT_B.getFriendRequestSentList());
        requestSend.add("444");
        STUDENT_B.sendFriendRequest("444");
        Assertions.assertEquals(requestSend, STUDENT_B.getFriendRequestSentList());
    }

    @Test
    void removeFriendListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("222");
        Assertions.assertEquals(friends, STUDENT_A.getFriendList());
        STUDENT_A.removeFriendList("222");
        friends.remove("222");
        Assertions.assertEquals(friends, STUDENT_A.getFriendList());
    }

    @Test
    void removeFriendRequestListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("333");
        Assertions.assertEquals(friends, STUDENT_A.getFriendListRequest());
        STUDENT_A.removeFriendRequestList("333");
        friends.remove("333");
        Assertions.assertEquals(friends, STUDENT_A.getFriendListRequest());
    }

    @Test
    void removeFriendRequestSentListTest() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("111");
        Assertions.assertEquals(friends, STUDENT_C.getFriendRequestSentList());
        STUDENT_C.removeFriendRequestSentList("111");
        friends.remove("111");
        Assertions.assertEquals(friends, STUDENT_C.getFriendRequestSentList());
    }
}
