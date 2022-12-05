package UIController;

import Entities.Admin;
import Entities.Student;
import UseCases.*;
import Entities.User;
import GUI.*;


import java.io.IOException;

/**
 * parent class of all controllers
 * creates instances of all controller classes
 * creates instances of all JFrames in the gui package
 */
public class UIController{

    public int STATUS;
    public static int FROM_REGISTER = 0;
    public static int FROM_PROFILE = 1;

    private String self;
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

    private LoginManager loginManager;

    /**
     * returns adminUIControl
     */
    public AdminUIControl getAdminUIControl() {return adminUIControl;}

    /**
     * returns loginUIControl
     */
    public LoginUIControl getLoginUIControl() {
        return loginUIControl;
    }

    /**
     * returns registerUIControl
     */
    public RegisterUIControl getRegisterUIControl() {
        return registerUIControl;
    }

    /**
     * returns tagMatchUIControl
     */
    public TagMatchUIControl getTagMatchUIControl() {
        return tagMatchUIControl;
    }

    /**
     * returns tagSelectUIControl
     */
    public TagSelectUIControl getTagSelectUIControl() {
        return tagSelectUIControl;
    }

    /**
     * returns labelSelectUIControl
     */
    public LabelSelectUIControl getLabelSelectUIControl() {return labelSelectUIControl;}

    /**
     * returns profileUIControl
     */
    public ProfileUIControl getProfileUIControl(){return profileUIControl;}

    /**
     * returns profileDisplayUIControl
     */
    public ProfileDisplayUIControl getProfileDisplayUIControl(){return profileDisplayUIControl;}

    /**
     * returns homeUIControl
     */
    public HomeUIControl getHomeUIControl(){return homeUIControl;}

    /**
     * returns friendListUIControl
     */
    public FriendListUIControl getFriendListUIControl(){return friendListUIControl;}

    /**
     * returns matchUIControl
     */
    public CourseMatchUIControl getMatchUIControl(){return matchUIControl;}

    /**
     * returns fileUploadUIControl
     */
    public FileUploadUIControl getFileUploadUIControl(){return  fileUploadUIControl;}

    /**
     * returns commonSessionUIControl
     */
    public CommonSessionUIControl getCommonSessionUIControl() {return commonSessionUIControl;}

    public AllStudentsUIControl getAllStudentsUIControl() {return allStudentsUIControl;}

    /**
     * Constructs a UIController with 4 parameters
     * @param userID represents a userID
     * @param courseManager instance of courseManager class
     * @param userManager instance of userManager class
     * @param tagManager instance of tagManager class
     */
    public UIController(String userID, CourseDataManager courseManager, UserDataManager userManager, TagDataManager tagManager) throws IOException {
        this.self = userID;
        this.courseManager = courseManager;
        this.userManager = userManager;
        this.tagManager = tagManager;

        // UseCases
        this.loginUIControl = new LoginUIControl(userID, courseManager, userManager, tagManager);
        this.registerUIControl = new RegisterUIControl(userID, courseManager, userManager, tagManager);
        this.loginManager = new LoginManager(courseManager, userManager, tagManager);
    }

    /**
     * updates user by calling the getUser() method from loginUIControl
     */
    public void updateUser(){
        this.self = this.loginUIControl.self;
    }

    /**
     * set self user to null
     */
    public void unloadUser() {
        this.self = null;
    }

    /**
     * returns whether the user is logged in or not
     */
    public boolean loggedIn(){
        return this.self != null;
    }

    /**
     * takes user to LoginFrame
     */
    public void toLogin(){
        LoginFrame loginFrame = new LoginFrame(this);
        this.unloadUser();
    }

    /**
     * takes user to HomeFrame
     */
    public void toHome() throws IOException {
        User user = (Student)loginManager.getUserByID(this.self);
        if(user instanceof Student){
            initializeAfterLogin();
            HomeFrame HomeFrame = new HomeFrame(this);
        }else if(user instanceof Admin){
            initializeAdminAfterLogin();
            AdminFrame adminFrame = new AdminFrame(this);
        }
    }

    /**
     * takes user to MatchFrame
     */
    public void toMatch(){
        CourseMatchFrame matchFrame = new CourseMatchFrame(this);
    }

    /**
     * takes user to RegisterFrame
     */
    public void toRegister(){
        RegisterFrame registerFrame = new RegisterFrame(this);
    }

    /**
     * takes user to ProfileRegFrame
     */
    public void toRegisterProfile(){
        STATUS = FROM_REGISTER;
        ProfileRegFrame profileRegFrame = new ProfileRegFrame(this);
    }

    /**
     * takes user to ProfileFrame
     */
    public void toProfile() throws IOException {
        STATUS = FROM_PROFILE;
        ProfileFrame profileFrame = new ProfileFrame(this);
    }

    /**
     * takes user to RegCmplFrame
     */
    public void toProfileCompleteFrame(){
        RegCmplFrame regCmplFrame = new RegCmplFrame(this);
    }

    /**
     * takes user to TagMatchFrame
     */
    public void toTagMatch() throws IOException {
        TagMatchFrame tagMatchFrame = new TagMatchFrame(this);
    }

    /**
     * takes user to TagSelectFrame
     */
    public void toTagSelect() throws IOException {
        TagSelectFrame tagSelectFrame = new TagSelectFrame(this);
    }

    /**
     * takes user to LabelSelectFrame
     */
    public void toLabelSelect() throws IOException {
        LabelSelectFrame LabelSelectFrame = new LabelSelectFrame(this);
    }

    /**
     * takes user to CalendarUploadFrame
     */
    public void toCalendarUpload() {
        this.fileUploadUIControl = new FileUploadUIControl(self, courseManager, userManager, tagManager);
        CalendarUploadFrame fileUploadFrame = new CalendarUploadFrame(this, STATUS);
    }

    /**
     * takes user to JFrame that displays userID's profile
     * @param userID profile of this user id will be shown on the frame
     */
    public void toProfileDisplay(String userID){ProfileDisplayFrame profileDisplayFrame = new ProfileDisplayFrame(this, userID);}

    /**
     * takes user to friendListFrame
     */
    public void toFriendList() throws IOException {FriendListFrame friendListFrame = new FriendListFrame(this);}

    /**
     * takes user to commonSessionFrame
     * @param targetUserID shows common sessions with targetUserID
     */
    public void toCommonSession(String targetUserID) throws IOException {CommonSessionFrame commonSessionFrame = new CommonSessionFrame(this, targetUserID);}

    /**
     * takes user to AllStudentsFrame
     */
    public void toAllStudents() throws IOException {AllStudentsFrame allStudentsFrame = new AllStudentsFrame(this);}

    /**
     * initializes all controllers after user(student) logged in
     */
    private void initializeAfterLogin(){
        this.tagMatchUIControl = new TagMatchUIControl(self, courseManager, userManager, tagManager);
        this.tagSelectUIControl = new TagSelectUIControl(self, courseManager, userManager, tagManager);
        this.labelSelectUIControl = new LabelSelectUIControl(self, courseManager, userManager, tagManager);
        this.profileUIControl = new ProfileUIControl(self, courseManager, userManager, tagManager);
        this.profileDisplayUIControl = new ProfileDisplayUIControl(self, courseManager, userManager, tagManager);
        this.homeUIControl = new HomeUIControl(self, courseManager,userManager, tagManager);
        this.friendListUIControl = new FriendListUIControl(self, courseManager, userManager, tagManager);
        this.matchUIControl = new CourseMatchUIControl(self, courseManager, userManager, tagManager);
        this.commonSessionUIControl = new CommonSessionUIControl(self, courseManager, userManager, tagManager);
        this.allStudentsUIControl = new AllStudentsUIControl(self, courseManager, userManager, tagManager);
    }

    /**
     * initializes adminUIControl after an admin user logged in
     */
    private void initializeAdminAfterLogin(){
        this.adminUIControl = new AdminUIControl(self, courseManager, userManager, tagManager);
    }

}
