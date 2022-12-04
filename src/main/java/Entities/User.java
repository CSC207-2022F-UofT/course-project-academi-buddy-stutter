package Entities;

/**
 * Implements User as a parent class to Admin class and Student Class
 */
public class User {
    String user_id;
    String full_name;
    private String user_password;
    String user_info;

    /**
     * Initiates a user
     */
    public User(){
        this.user_id = null;
        this.user_password = null;
        this.full_name = null;
        this.user_info = null;
    }

    /**
     * Constructs a user
     * @param UID user id
     * @param UPass user password
     * @param full_name user's full name
     * @param info user's info
     */
    public User(String UID, String UPass, String full_name, String info){
        this.user_id = UID;
        this.user_password = UPass;
        this.full_name = full_name;
        this.user_info = info;
    }

    /*
    GetUserID: returns user id
    param: none
    return: String
     */
    public String getUserID(){
        return this.user_id;
    }

    /**
     * @return user's full name
     */
    public String getFullName() {return full_name;}

    /*
    getUserInfo: returns User info
    param: none
    return: String
     */
    public String getUserInfo(){
        return this.user_info;
    }

    /*
   getUserPassword: Get user password
   param: none
   return: String
    */
    public String getUserPassword(){
        return this.user_password;
    }

    /*
    SetUserInfo: sets new user info
    param: String
    return: none
     */
    public void setUserInfo(String info){
        this.user_info = info;
    }

    /**
     SetSUserID: sets new user ID
     param: String
     return: none
     */
    public void setUserID(String ID){
        this.user_id = ID;
    }

    /*
    SetUserPassword: sets new user password
    param: String
    return: none
     */
    private void setUserPassword(String pass){
        this.user_password = pass;
    }
}
