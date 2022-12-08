package model.usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.entities.Course;
import model.entities.InterestTag;
import model.entities.Student;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Use case class for Admin operations on the database. Specifically, removing students from courses.
 */
public class AdminActionsManager extends UseCase{

    /**
     * Initializer
     * @param courseDatabase The Course Database.
     * @param userDatabase The User Database.
     * @param tagDatabase The Tag Database.
     */
    public AdminActionsManager(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess
            tagDatabase) {
        super(courseDatabase, userDatabase, tagDatabase);
    }

    /**
     * Removes a student from all databases.
     * @param userID the user id of the student being removed
     * @return True if student is removed from all databases, false if student does not exist.
     */
    public boolean removeUser(String userID){
        System.out.println(userID);
        if(!userExist(userID)){
            return false;
        }
        try {
            Student student = (Student) this.getUserByID(userID);
            removeStudentFromCourse(student);
            removeStudentFromTag(student);
            removeStudentFromFriends(student);
            this.ub.removeUser(userID);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method for removeUser, removes user from Course Database.
     * @param student the student that is being removed.
     */
    public void removeStudentFromCourse(Student student) throws IOException {
        for(String c: student.getEnrolledCourseCodes()){
            Course course = this.cb.getCourse(c, "LEC");
            this.cb.removeStudent(course.getCourseCode(), course.getCourseType(), student);
            student.removeCourse(course);
        }
        if(!student.getEnrolledCourseCodes().isEmpty()){
            for(String c: student.getEnrolledCourseCodes()){
                Course course = this.cb.getCourse(c, "TUT");
                this.cb.removeStudent(course.getCourseCode(), course.getCourseType(), student);
                student.removeCourse(course);
            }
        }
    }

    public void removeStudentFromFriends(Student student) throws IOException {
        ArrayList<String> allStudents = this.ub.getUserIDList();
        ArrayList<String> allAdmins = this.ub.getAdminIDs();
        String studentID = student.getUserID();
        for (String id: allStudents) {
            if (!allAdmins.contains(id)) {
                // remove from friend list
                if (student.getFriendList().contains(id)) {
                    Student friend = (Student) this.getUserByID(id);
                    boolean removeFriend = student.removeFriendList(friend.getUserID());
                    boolean removeStudent = friend.removeFriendList(studentID);
                    if (removeFriend && removeStudent) {this.ub.removeFromFriendList(student, friend);}
                    System.out.println("Removed " + id + " from " + studentID);
                }
                // remove friend from friend request list
                else if (student.getFriendListRequest().contains(id)) {
                    Student friend = (Student) this.getUserByID(id);
                    boolean removeFriend = student.removeFriendRequestList(friend.getUserID());
                    boolean removeStudent = friend.removeFriendRequestSentList(studentID);
                    if (removeFriend && removeStudent) {this.ub.removeFromFriendRequestSentList(student, friend);}
                    System.out.println("Removed " + id + " from " + studentID);
                }
                // remove from friend request sent list
                else if (student.getFriendRequestSentList().contains(id)) {
                    Student friend = (Student) this.getUserByID(id);
                    boolean removeFriend = student.removeFriendRequestSentList(friend.getUserID());
                    boolean removeStudent = friend.removeFriendRequestList(studentID);
                    if (removeFriend && removeStudent) {this.ub.removeFromFriendRequestList(student, friend);}
                    System.out.println("Removed " + id + " from " + studentID);
                }
            }
        }
    }

    /**
     * Helper method for removeUser, removes user from Tag Database.
     * @param student the student that is being removed.
     */
    private void removeStudentFromTag(Student student) throws IOException{
        for(InterestTag t: student.getTags()){
            if(!t.getName().equals("")){
                this.tb.removeStudent(t, student);
            }
        }
    }

    /**
     * Method that returns true if the user is in the user database.
     * @param userID the user to check in the user database.
     * @return true if user exists in user database, false if not.
     */
    public boolean userExist(String userID){
        return this.ub.existByID(userID);
    }
}