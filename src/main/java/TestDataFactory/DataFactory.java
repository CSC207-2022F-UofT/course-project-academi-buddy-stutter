package TestDataFactory;

import UseCases.CourseDataCloud;
import UseCases.TagDataCloud;
import UseCases.UserDataCloud;

public class DataFactory {
    protected CourseDataCloud courseDataManager;
    protected UserDataCloud userDataManager;
    protected TagDataCloud tagDataManager;
    public DataFactory(CourseDataCloud courseDataManager, UserDataCloud userDataManager, TagDataCloud tagDataManager){
        this.courseDataManager = courseDataManager;
        this.userDataManager = userDataManager;
        this.tagDataManager = tagDataManager;
    }
}
