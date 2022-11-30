package UseCases;

import Entities.Course;
import Entities.Student;
import Entities.User;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfileManager extends UseCase{

    public ProfileManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public String getName(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            return user.getFullName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserEmail(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            return ((Student) user).getEmail();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserInfo(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            return user.getUserInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCourseString(String userID){
        try {
            User user = this.ub.getUserByID(userID);
            ArrayList<String> coursesList = ((Student) user).getEnrolledCourseCodes();
            StringBuilder courseString = new StringBuilder();
            if(coursesList.contains(null)){
                return courseString.toString();
            }
            for(String course: coursesList){
                courseString.append(course);
                courseString.append(": ");
                courseString.append(cb.getCourse(course).getCourseName());
                courseString.append("\n");
            }
            return courseString.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//
//    public ArrayList<String> getFriendList(String userID) {
//        try {
//            User user = this.ub.getUserByID(userID);
//            ArrayList<Student> friendList = ((Student) user).getFriendList();
//            ArrayList<String> friendListString = new ArrayList<>();
//            if (friendList.contains(null)) {
//                return friendListString;
//            }
//            for (Student friend: friendList) {
//                friendListString.add(friend.getFullName());
//            }
//            return friendListString;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public boolean sendFriendRequest(String userID, String viewerUserID) {
//        try {
//            User viewerUser = this.ub.getUserByID(viewerUserID);
//            User receiveRequestUser = this.ub.getUserByID(userID);
//            ((Student) viewerUser).sendFriendRequest((Student )receiveRequestUser);
//            return true;
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
