package UseCases;

public class FriendListManager extends UseCase{
    public FriendListManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }
}
