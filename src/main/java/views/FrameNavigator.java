package views;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import presenters.*;


import java.io.Serializable;

/**
 * parent class of all UI presenters
 * creates instances of all presenter classes
 * creates instances of all JFrames in the views package
 */
public class FrameNavigator implements Serializable {

    private static final long serialVersionUID = 1L;
    public int STATUS;
    public static final int FROM_REGISTER = 0;
    public static final int FROM_PROFILE = 1;

    private String selfID;
    private final CourseDataAccess courseManager;
    private final UserDataAccess userManager;
    private final TagDataAccess tagManager;

    protected final LoginUIPresenter loginUIPresenter;
    protected final RegisterUIPresenter registerUIPresenter;
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

    /**
     * Constructs a FrameNavigator with 4 parameters
     * @param userID represents a userID
     * @param courseManager instance of courseManager class
     * @param userManager instance of userManager class
     * @param tagManager instance of tagManager class
     */
    public FrameNavigator(String userID, CourseDataAccess courseManager, UserDataAccess userManager, TagDataAccess tagManager) {
        this.selfID = userID;
        this.courseManager = courseManager;
        this.userManager = userManager;
        this.tagManager = tagManager;

        // UseCases
        this.loginUIPresenter = new LoginUIPresenter(courseManager, userManager, tagManager);
        this.registerUIPresenter = new RegisterUIPresenter(courseManager, userManager, tagManager);
    }

    /**
     * returns adminUIControl
     */
    public AdminUIPresenter getAdminUIPresenter() {return adminUIPresenter;}

    /**
     * returns loginUIPresenter
     */
    public LoginUIPresenter getLoginUIPresenter() {
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
    public TagMatchUIPresenter getTagMatchUIPresenter() {
        return tagMatchUIPresenter;
    }

    /**
     * returns tagSelectUIPresenter
     */
    public TagSelectUIPresenter getTagSelectPresenter() {
        return tagSelectUIPresenter;
    }

    /**
     * returns labelSelectUIPresenter
     */
    public LabelSelectUIPresenter getLabelSelectPresenter() {return labelSelectUIPresenter;}

    /**
     * returns profileUIPresenter
     */
    public ProfileUIPresenter getProfileUIPresenter(){return profileUIPresenter;}

    /**
     * returns profileDisplayUIPresenter
     */
    public ProfileDisplayUIPresenter getProfileDisplayUIPresenter(){return profileDisplayUIPresenter;}

    /**
     * returns homeUIPresenter
     */
    public HomeUIPresenter getHomeUIPresenter(){return homeUIPresenter;}

    /**
     * returns friendListUIPresenter
     */
    public FriendListUIPresenter getFriendListUIPresenter(){return friendListUIPresenter;}

    /**
     * returns matchUIControl
     */
    public CourseMatchUIPresenter getMatchUIPresenter(){return matchUIControl;}

    /**
     * returns fileUploadUIPresenter
     */
    public FileUploadUIPresenter getFileUploadUIPresenter(){return fileUploadUIPresenter;}

    /**
     * returns commonSessionUIPresenter
     */
    public CommonSessionUIPresenter getCommonSessionUIPresenter() {return commonSessionUIPresenter;}

    public AllStudentsUIPresenter getAllStudentsUIPresenter() {return allStudentsUIPresenter;}

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
    public void toHome() {
            initializeAfterLogin();
            HomeFrame HomeFrame = new HomeFrame(this);
    }


    /**
     * takes user to Admin Frame
     */
    public void toAdmin() {
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
    public void toProfile() {
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
    public void toTagMatch() {
        TagMatchFrame tagMatchFrame = new TagMatchFrame(this);
    }

    /**
     * takes user to TagSelectFrame
     */
    public void toTagSelect() {
        TagSelectFrame tagSelectFrame = new TagSelectFrame(this);
    }

    /**
     * takes user to LabelSelectFrame
     */
    public void toLabelSelect() {
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
    public void toFriendList() {FriendListFrame friendListFrame = new FriendListFrame(this);}

    /**
     * takes user to commonSessionFrame
     * @param targetUserID shows common sessions with targetUserID
     */
    public void toCommonSession(String targetUserID) {CommonSessionFrame commonSessionFrame = new CommonSessionFrame(this, targetUserID);}

    /**
     * takes user to AllStudentsFrame
     */
    public void toAllStudents() {AllStudentsFrame allStudentsFrame = new AllStudentsFrame(this);}


    /**
     * initializes all controllers after user(student) logged in
     */
    private void initializeAfterLogin(){
        this.tagMatchUIPresenter = new TagMatchUIPresenter(selfID, courseManager, userManager, tagManager);
        this.tagSelectUIPresenter = new TagSelectUIPresenter(selfID, courseManager, userManager, tagManager);
        this.labelSelectUIPresenter = new LabelSelectUIPresenter(selfID, courseManager, userManager, tagManager);
        this.profileUIPresenter = new ProfileUIPresenter(selfID, courseManager, userManager, tagManager);
        this.profileDisplayUIPresenter = new ProfileDisplayUIPresenter(courseManager, userManager, tagManager);
        this.homeUIPresenter = new HomeUIPresenter(selfID, courseManager,userManager, tagManager);
        this.friendListUIPresenter = new FriendListUIPresenter(selfID, courseManager, userManager, tagManager);
        this.matchUIControl = new CourseMatchUIPresenter(selfID, courseManager, userManager, tagManager);
        this.commonSessionUIPresenter = new CommonSessionUIPresenter(selfID, courseManager, userManager, tagManager);
        this.allStudentsUIPresenter = new AllStudentsUIPresenter(courseManager, userManager, tagManager);
    }

    /**
     * initializes adminUIControl after an admin user logged in
     */
    private void initializeAdminAfterLogin(){
        this.adminUIPresenter = new AdminUIPresenter(selfID, courseManager, userManager, tagManager);
    }
}
