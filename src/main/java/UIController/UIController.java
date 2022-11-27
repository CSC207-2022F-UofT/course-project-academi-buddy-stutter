package UIController;

import Entities.Student;
import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;
import Entities.User;
import GUI.*;
import UseCases.RegisterManager;
import UseCases.TagMatchManager;
import UseCases.TagSelectManager;

import java.io.IOException;


public class UIController{

    //TODO: make UIController as parent class. Create seperate UIControl for each Frame, e.g. LoginUIControl, etc.

    private User self;
    private CourseDataManager courseManager;
    private UserDataManager userManager;
    private RegisterManager registerManager;
    private TagDataManager tagManager;

    private TagMatchManager tagMatchManager;
    private TagSelectManager tagSelectManager;

    protected LoginUIControl loginUIControl;
    protected RegisterUIControl registerUIControl;
    private LabelSelectUIControl labelSelectUIControl;

    private ProfileUIControl profileUIControl;
    private ProfileDisplayUIControl profileDisplayUIControl;

    private HomeUIControl homeUIControl;

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

    protected TagMatchUIControl tagMatchUIControl;
    protected TagSelectUIControl tagSelectUIControl;



    public UIController(User user, CourseDataManager courseManager, UserDataManager userManager, TagDataManager tagManager){
        this.self = user;
        this.courseManager = courseManager;
        this.userManager = userManager;
        this.tagManager = tagManager;
        updateSelf();


        // UseCases


        this.tagMatchManager = new TagMatchManager(courseManager, userManager, tagManager);
        this.tagSelectManager = new TagSelectManager(courseManager, userManager, tagManager);


        this.loginUIControl = new LoginUIControl(courseManager, userManager);
        this.registerUIControl = new RegisterUIControl(courseManager, userManager);
        this.tagMatchUIControl = new TagMatchUIControl(self, courseManager, userManager, tagManager);
        this.tagSelectUIControl = new TagSelectUIControl(self, courseManager, userManager, tagManager);
        this.labelSelectUIControl = new LabelSelectUIControl(self, courseManager, userManager);
        this.profileUIControl = new ProfileUIControl(self, courseManager, userManager);
        this.profileDisplayUIControl = new ProfileDisplayUIControl(courseManager, userManager);
        this.homeUIControl = new HomeUIControl(courseManager,userManager);


    }
    public void updateSelf(){
        try {
            this.self = userManager.getUserByID(self.getUserID());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateUser(){
        this.self = this.loginUIControl.getUser();
    }

    public void unloadUser() {
        this.self = null;
    }


    public void toLogin(){
        LoginFrame loginFrame = new LoginFrame(this);
        this.unloadUser();
    }

    public void toHome(){
        HomeFrame HomeFrame = new HomeFrame(this);
    }

    public void toMatch(){
        MatchFrame matchFrame = new MatchFrame(this);
    }

    public void toRegister(){
        RegisterFrame registerFrame = new RegisterFrame(this);
    }

    public void toTagMatch(){
        TagMatchFrame tagMatchFrame = new TagMatchFrame(this);
    }

    public void toTagSelect(){
        TagSelectFrame tagSelectFrame = new TagSelectFrame(this);
    }

    public void toProfileDisplay(String userID){ProfileDisplayFrame profileDisplayFrame = new ProfileDisplayFrame(this, userID);}

}
