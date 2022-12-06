package views;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.usecases.LoginManager;
import presenters.*;
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

    protected LoginUIPresenter loginUIPresenter;
    protected RegisterUIPresenter registerUIPresenter;
    private LabelSelectUIPresenter labelSelectUIPresenter;

    private ProfileUIPresenter profileUIPresenter;
    private ProfileDisplayUIPresenter profileDisplayUIPresenter;

    private HomeUIPresenter homeUIPresenter;

    private FriendListUIPresenter friendListUIPresenter;
    private TagMatchUIPresenter tagMatchUIPresenter;
    private TagSelectUIPresenter tagSelectUIPresenter;

    private CourseMatchUIPresenter matchUIControl;

    private FileUploadUIPresenter fileUploadUIPresenter;

    private CommonSessionUIPresenter commonSessionUIPresenter;

    private AllStudentsUIPresenter allStudentsUIPresenter;

    private AdminUIPresenter adminUIPresenter;

    private LoginManager loginManager;

    /**
     * returns adminUIControl
     */
    public AdminUIPresenter getAdminUIPresenter() {return adminUIPresenter;}

    /**
     * returns loginUIPresenter
     */
    public LoginUIPresenter getLoginUIControl() {
        return loginUIPresenter;
    }

    /**
     * returns registerUIPresenter
     */
    public RegisterUIPresenter getRegisterUIControl() {
        return registerUIPresenter;
    }

    /**
     * returns tagMatchUIPresenter
     */
    public TagMatchUIPresenter getTagMatchUIControl() {
        return tagMatchUIPresenter;
    }

    /**
     * returns tagSelectUIPresenter
     */
    public TagSelectUIPresenter getTagSelectUIControl() {
        return tagSelectUIPresenter;
    }

    /**
     * returns labelSelectUIPresenter
     */
    public LabelSelectUIPresenter getLabelSelectUIControl() {return labelSelectUIPresenter;}

    /**
     * returns profileUIPresenter
     */
    public ProfileUIPresenter getProfileUIControl(){return profileUIPresenter;}

    /**
     * returns profileDisplayUIPresenter
     */
    public ProfileDisplayUIPresenter getProfileDisplayUIControl(){return profileDisplayUIPresenter;}

    /**
     * returns homeUIPresenter
     */
    public HomeUIPresenter getHomeUIControl(){return homeUIPresenter;}

    /**
     * returns friendListUIPresenter
     */
    public FriendListUIPresenter getFriendListUIControl(){return friendListUIPresenter;}

    /**
     * returns matchUIControl
     */
    public CourseMatchUIPresenter getMatchUIControl(){return matchUIControl;}

    /**
     * returns fileUploadUIPresenter
     */
    public FileUploadUIPresenter getFileUploadUIControl(){return fileUploadUIPresenter;}

    /**
     * returns commonSessionUIPresenter
     */
    public CommonSessionUIPresenter getCommonSessionUIControl() {return commonSessionUIPresenter;}

    public AllStudentsUIPresenter getAllStudentsUIControl() {return allStudentsUIPresenter;}

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
        this.loginUIPresenter = new LoginUIPresenter(userID, courseManager, userManager, tagManager);
        this.registerUIPresenter = new RegisterUIPresenter(userID, courseManager, userManager, tagManager);
        this.loginManager = new LoginManager(courseManager, userManager, tagManager);
    }

    /**
     * updates user by calling the getUser() method from loginUIPresenter
     */
    public void updateUser(){
        this.selfID = this.loginUIPresenter.selfID;
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
        this.fileUploadUIPresenter = new FileUploadUIPresenter(selfID, courseManager, userManager, tagManager);
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
        this.tagMatchUIPresenter = new TagMatchUIPresenter(selfID, courseManager, userManager, tagManager);
        this.tagSelectUIPresenter = new TagSelectUIPresenter(selfID, courseManager, userManager, tagManager);
        this.labelSelectUIPresenter = new LabelSelectUIPresenter(selfID, courseManager, userManager, tagManager);
        this.profileUIPresenter = new ProfileUIPresenter(selfID, courseManager, userManager, tagManager);
        this.profileDisplayUIPresenter = new ProfileDisplayUIPresenter(selfID, courseManager, userManager, tagManager);
        this.homeUIPresenter = new HomeUIPresenter(selfID, courseManager,userManager, tagManager);
        this.friendListUIPresenter = new FriendListUIPresenter(selfID, courseManager, userManager, tagManager);
        this.matchUIControl = new CourseMatchUIPresenter(selfID, courseManager, userManager, tagManager);
        this.commonSessionUIPresenter = new CommonSessionUIPresenter(selfID, courseManager, userManager, tagManager);
        this.allStudentsUIPresenter = new AllStudentsUIPresenter(selfID, courseManager, userManager, tagManager);
    }

    /**
     * initializes adminUIControl after an admin user logged in
     */
    private void initializeAdminAfterLogin(){
        this.adminUIPresenter = new AdminUIPresenter(selfID, courseManager, userManager, tagManager);
    }
}
