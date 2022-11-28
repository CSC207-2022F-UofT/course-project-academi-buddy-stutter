package TestDataFactory;

import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;

public class DataFactory {
    protected CourseDataManager courseDataManager;
    protected UserDataManager userDataManager;
    protected TagDataManager tagDataManager;
    public DataFactory(CourseDataManager courseDataManager, UserDataManager userDataManager, TagDataManager tagDataManager){
        this.courseDataManager = courseDataManager;
        this.userDataManager = userDataManager;
        this.tagDataManager = tagDataManager;
    }
}
