package UIController;

import Entities.User;
import UseCases.CourseDataManager;
import UseCases.FriendListManager;
import UseCases.UserDataManager;

public class FriendListUIControl {
    private FriendListManager friendListManager;
    private User self;
    public FriendListUIControl(User self, CourseDataManager courseManager, UserDataManager userManager) {
        this.friendListManager = new FriendListManager(courseManager, userManager);
        this.self = self;
    }

}
