package ui;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import usecaseio.*;
import usecases.*;


import java.io.IOException;

/**
 * parent class of all controllers
 * creates instances of all controller classes
 * creates instances of all JFrames in the gui package
 */
public class FrameNavigator {

    public int STATUS;
    public static int FROM_REGISTER = 0;
    public static int FROM_PROFILE = 1;

    private String selfID;
    private CourseDataAccess courseManager;
    private UserDataAccess userManager;
    private TagDataAccess tagManager;

    protected LoginIO loginIO;
    protected RegisterIO registerIO;
    private LabelSelectIO labelSelectIO;

    private ProfileIO profileIO;
    private ProfileDisplayIO profileDisplayIO;

    private HomeIO homeIO;

    private FriendListIO friendListIO;
    private TagMatchIO tagMatchIO;
    private TagSelectIO tagSelectIO;

    private CourseMatchIO matchUIControl;

    private FileUploadIO fileUploadIO;

    private CommonSessionIO commonSessionIO;

    private AllStudentsIO allStudentsIO;

    private AdminIO adminUIControl;

    private LoginManager loginManager;

    /**
     * returns adminUIControl
     */
    public AdminIO getAdminUIControl() {return adminUIControl;}

    /**
     * returns loginIO
     */
    public LoginIO getLoginUIControl() {
        return loginIO;
    }

    /**
     * returns registerIO
     */
    public RegisterIO getRegisterUIControl() {
        return registerIO;
    }

    /**
     * returns tagMatchIO
     */
    public TagMatchIO getTagMatchUIControl() {
        return tagMatchIO;
    }

    /**
     * returns tagSelectIO
     */
    public TagSelectIO getTagSelectUIControl() {
        return tagSelectIO;
    }

    /**
     * returns labelSelectIO
     */
    public LabelSelectIO getLabelSelectUIControl() {return labelSelectIO;}

    /**
     * returns profileIO
     */
    public ProfileIO getProfileUIControl(){return profileIO;}

    /**
     * returns profileDisplayIO
     */
    public ProfileDisplayIO getProfileDisplayUIControl(){return profileDisplayIO;}

    /**
     * returns homeIO
     */
    public HomeIO getHomeUIControl(){return homeIO;}

    /**
     * returns friendListIO
     */
    public FriendListIO getFriendListUIControl(){return friendListIO;}

    /**
     * returns matchUIControl
     */
    public CourseMatchIO getMatchUIControl(){return matchUIControl;}

    /**
     * returns fileUploadIO
     */
    public FileUploadIO getFileUploadUIControl(){return fileUploadIO;}

    /**
     * returns commonSessionIO
     */
    public CommonSessionIO getCommonSessionUIControl() {return commonSessionIO;}

    public AllStudentsIO getAllStudentsUIControl() {return allStudentsIO;}

    /**
     * Constructs a FrameNavigator with 4 parameters
     * @param userID represents a userID
     * @param courseManager instance of courseManager class
     * @param userManager instance of userManager class
     * @param tagManager instance of tagManager class
     */
    public FrameNavigator(String userID, CourseDataAccess courseManager, UserDataAccess userManager, TagDataAccess tagManager) throws IOException {
        this.selfID = userID;
        this.courseManager = courseManager;
        this.userManager = userManager;
        this.tagManager = tagManager;

        // UseCases
        this.loginIO = new LoginIO(userID, courseManager, userManager, tagManager);
        this.registerIO = new RegisterIO(userID, courseManager, userManager, tagManager);
        this.loginManager = new LoginManager(courseManager, userManager, tagManager);
    }

    /**
     * updates user by calling the getUser() method from loginIO
     */
    public void updateUser(){
        this.selfID = this.loginIO.selfID;
    }

    /**
     * set selfID user to null
     */
    public void unloadUser() {
        this.selfID = null;
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
            initializeAfterLogin();
            HomeFrame HomeFrame = new HomeFrame(this);
    }


    /**
     * takes user to Admin Frame
     */
    public void toAdmin() throws IOException {
        initializeAdminAfterLogin();
        AdminFrame adminFrame = new AdminFrame(this);
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
        this.fileUploadIO = new FileUploadIO(selfID, courseManager, userManager, tagManager);
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
        this.tagMatchIO = new TagMatchIO(selfID, courseManager, userManager, tagManager);
        this.tagSelectIO = new TagSelectIO(selfID, courseManager, userManager, tagManager);
        this.labelSelectIO = new LabelSelectIO(selfID, courseManager, userManager, tagManager);
        this.profileIO = new ProfileIO(selfID, courseManager, userManager, tagManager);
        this.profileDisplayIO = new ProfileDisplayIO(selfID, courseManager, userManager, tagManager);
        this.homeIO = new HomeIO(selfID, courseManager,userManager, tagManager);
        this.friendListIO = new FriendListIO(selfID, courseManager, userManager, tagManager);
        this.matchUIControl = new CourseMatchIO(selfID, courseManager, userManager, tagManager);
        this.commonSessionIO = new CommonSessionIO(selfID, courseManager, userManager, tagManager);
        this.allStudentsIO = new AllStudentsIO(selfID, courseManager, userManager, tagManager);
    }

    /**
     * initializes adminUIControl after an admin user logged in
     */
    private void initializeAdminAfterLogin(){
        this.adminUIControl = new AdminIO(selfID, courseManager, userManager, tagManager);
    }
}
