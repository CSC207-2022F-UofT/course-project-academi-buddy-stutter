package UIController;

import Entities.User;
import Entities.Student;
import UseCases.CourseDataManager;
import UseCases.CourseMatchManager;
import UseCases.UserDataManager;

public class MatchUIControl {
    private CourseMatchManager courseMatchManager;
    public User self;
    public MatchUIControl(User self, CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.self = (Student)self;
        this.courseMatchManager = new CourseMatchManager(courseDatabase, userDatabase);
    }
//
//
//    public getMatches(numCommon, 4){
//        this.courseMatchManager.getTopSameSessionStudents(this.self)
//    }

}
