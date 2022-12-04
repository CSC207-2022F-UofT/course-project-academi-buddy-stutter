package UIController;

import Entities.Admin;
import Entities.Student;
import UseCases.*;
import Entities.User;
import GUI.*;

import java.io.IOException;


public class UIController{

    public int STATUS;
    public static int FROM_REGISTER = 0;
    public static int FROM_PROFILE = 1;

    private User self;
    private CourseDataManager courseManager;
    private UserDataManager userManager;
    private TagDataManager tagManager;

    protected LoginUIControl loginUIControl;
    protected RegisterUIControl registerUIControl;
    private LabelSelectUIControl labelSelectUIControl;

    private ProfileUIControl profileUIControl;
    private ProfileDisplayUIControl profileDisplayUIControl;

    private HomeUIControl homeUIControl;

    private FriendListUIControl friendListUIControl;
    private TagMatchUIControl tagMatchUIControl;
    private TagSelectUIControl tagSelectUIControl;

    private CourseMatchUIControl matchUIControl;

    private FileUploadUIControl fileUploadUIControl;

    private CommonSessionUIControl commonSessionUIControl;

    private AllStudentsUIControl allStudentsUIControl;

    private AdminUIControl adminUIControl;
    public AdminUIControl getAdminUIControl() {return adminUIControl;}

    public LoginUIControl getLoginUIControl() {
        return loginUIControl;
    }

    public RegisterUIControl getRegisterUIControl() {
        return registerUIControl;
    }

    public TagMatchUIControl getTagMatchUIControl() {
        return tagMatchUIControl;
    }

    public TagSelectUIControl getTagSelectUIControl() {
        return tagSelectUIControl;
    }

    public LabelSelectUIControl getLabelSelectUIControl() {return labelSelectUIControl;}

    public ProfileUIControl getProfileUIControl(){return profileUIControl;}

    public ProfileDisplayUIControl getProfileDisplayUIControl(){return profileDisplayUIControl;}

    public HomeUIControl getHomeUIControl(){return homeUIControl;}

    public FriendListUIControl getFriendListUIControl(){return friendListUIControl;}

    public CourseMatchUIControl getMatchUIControl(){return matchUIControl;}

    public FileUploadUIControl getFileUploadUIControl(){return  fileUploadUIControl;}

    public CommonSessionUIControl getCommonSessionUIControl() {return commonSessionUIControl;}

    public AllStudentsUIControl getAllStudentsUIControl() {return allStudentsUIControl;}

    public UIController(User user, CourseDataManager courseManager, UserDataManager userManager, TagDataManager tagManager){
        this.self = user;
        this.courseManager = courseManager;
        this.userManager = userManager;
        this.tagManager = tagManager;

        // UseCases



        this.loginUIControl = new LoginUIControl(courseManager, userManager);
        this.registerUIControl = new RegisterUIControl(courseManager, userManager);


    }
    public void updateUser(){
        this.self = this.loginUIControl.getUser();
    }

    public void unloadUser() {
        this.self = null;
    }

    public boolean loggedIn(){
        return this.self != null;
    }

    public void toLogin(){
        LoginFrame loginFrame = new LoginFrame(this);
        this.unloadUser();
    }

    public void toHome(){
        System.out.println(self.getClass());
        if(self instanceof Student){
            initializeAfterLogin();
            HomeFrame HomeFrame = new HomeFrame(this);
        }else if(self instanceof Admin){
            initializeAdminAfterLogin();
            AdminFrame adminFrame = new AdminFrame(this);
        }
    }

    public void toMatch(){
        CourseMatchFrame matchFrame = new CourseMatchFrame(this);
    }

    public void toRegister(){
        RegisterFrame registerFrame = new RegisterFrame(this);
    }

    public void toRegisterProfile(){
        STATUS = FROM_REGISTER;
        ProfileRegFrame profileRegFrame = new ProfileRegFrame(this);
    }

    public void toProfile(){
        STATUS = FROM_PROFILE;
        ProfileFrame profileFrame = new ProfileFrame(this);
    }

    public void toProfileCompleteFrame(){
        RegCmplFrame regCmplFrame = new RegCmplFrame(this);
    }

    public void toTagMatch(){
        TagMatchFrame tagMatchFrame = new TagMatchFrame(this);
    }

    public void toTagSelect(){
        TagSelectFrame tagSelectFrame = new TagSelectFrame(this);
    }

    public void toLabelSelect(){
        LabelSelectFrame LabelSelectFrame = new LabelSelectFrame(this);
    }

    public void toCalendarUpload() {
        this.fileUploadUIControl = new FileUploadUIControl(self, courseManager, userManager);
        CalendarUploadFrame fileUploadFrame = new CalendarUploadFrame(this, STATUS);
    }

    public void toProfileDisplay(String userID){ProfileDisplayFrame profileDisplayFrame = new ProfileDisplayFrame(this, userID);}

    public void toFriendList() {FriendListFrame friendListFrame = new FriendListFrame(this);}

    public void toCommonSession(String targetUserID) {CommonSessionFrame commonSessionFrame = new CommonSessionFrame(this, targetUserID);}

    public void toAllStudents() {AllStudentsFrame allStudentsFrame = new AllStudentsFrame(this);}

    private void initializeAfterLogin(){
        this.tagMatchUIControl = new TagMatchUIControl(self, courseManager, userManager, tagManager);
        this.tagSelectUIControl = new TagSelectUIControl(self, courseManager, userManager, tagManager);
        this.labelSelectUIControl = new LabelSelectUIControl(self, courseManager, userManager);
        this.profileUIControl = new ProfileUIControl(self, courseManager, userManager);
        this.profileDisplayUIControl = new ProfileDisplayUIControl(courseManager, userManager);
        this.homeUIControl = new HomeUIControl(self, courseManager,userManager);
        this.friendListUIControl = new FriendListUIControl(self, courseManager, userManager);
        this.matchUIControl = new CourseMatchUIControl(self, courseManager, userManager);
        this.commonSessionUIControl = new CommonSessionUIControl(self, courseManager, userManager);
        this.allStudentsUIControl = new AllStudentsUIControl(self, courseManager, userManager);
    }

    private void initializeAdminAfterLogin(){
        this.adminUIControl = new AdminUIControl(self, courseManager, userManager, tagManager);
    }

}
