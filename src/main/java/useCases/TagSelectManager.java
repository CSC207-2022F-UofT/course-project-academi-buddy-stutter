package useCases;

import Database.CourseManager;
import Database.TagManager;
import Database.UserManager;
import Users.InterestTag;
import Users.Student;
import Users.User;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TagSelectManager extends UseCase {

    public TagSelectManager(CourseManager courseDatabase, UserManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

}