package TestDataFactory;

import UseCases.CloudCourseData;
import UseCases.CloudTagData;
import UseCases.CloudUserData;

public class DataFactory {
    protected CloudCourseData courseDataManager;
    protected CloudUserData userDataManager;
    protected CloudTagData tagDataManager;
    public DataFactory(CloudCourseData courseDataManager, CloudUserData userDataManager, CloudTagData tagDataManager){
        this.courseDataManager = courseDataManager;
        this.userDataManager = userDataManager;
        this.tagDataManager = tagDataManager;
    }
}
