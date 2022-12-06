package usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import entities.InterestTag;
import entities.Student;

import java.io.IOException;

/**
 * Use case operations for selecting a tag.
 */
public class TagSelectManager extends UseCase {

    /**
     * Initializer
     * @param courseDatabase The course database.
     * @param userDatabase The user database.
     * @param tagDatabase The tag manager.
     */
    public TagSelectManager(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess tagDatabase) {
        super(courseDatabase, userDatabase, tagDatabase);
    }

    /**
     * Get the current state of the tag on current student.
     * @param self The current student.
     * @param tagName The name of the tag to look at.
     * @return True if the tag is selected, false otherwise.
     */
    public boolean getStudentTagState(String selfID, String tagName){
        Student self = (Student) this.getUserByID(selfID);
        InterestTag tag = new InterestTag(tagName);
        return (self.isTagSelected(tag));
    }

    /**
     * Change the status of current tag for current student.
     * @param self The current student.
     * @param tagName The current tag that is being updated.
     * @param selected If the tag is selected then true, false otherwise.
     */
    public void updateStudentTag(String selfID, String tagName, boolean selected){
        Student self = (Student) this.getUserByID(selfID);
        InterestTag tag = new InterestTag(tagName);
        if(selected){
            try {
                tb.addStudent(tag, self);
                self.updateStudentTOI(tag, true);
                ub.addStudentUser(self);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                tb.removeStudent(tag, self);
                self.updateStudentTOI(tag, false);
                ub.addStudentUser(self);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}