package GUI;

import Users.User;

public class UIOperator {

    //TODO: implement this class. UIOperator/Navigator construct and passes information between frames. UIOperator.home(), UIOperator.courseMatch().

    public void toLogin(UIController uiController){
        LoginFrame loginFrame = new LoginFrame(uiController);
        uiController.unloadUser();
    }

    public void toHome(UIController uiController){
        HomeFrame HomeFrame = new HomeFrame(uiController);
    }

    public void toRegister(UIController uiController){
        RegisterFrame registerFrame = new RegisterFrame(uiController);
    }

}
