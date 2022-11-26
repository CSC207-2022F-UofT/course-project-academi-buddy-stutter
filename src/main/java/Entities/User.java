package Entities;

public class User {
    String user_id;


    String full_name;
    private String user_password;
    String user_info;

    //init
    public User(){
        this.user_id = null;
        this.user_password = null;
        this.full_name = null;
        this.user_info = null;
    }
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
    public String GetUserID(){
        return this.user_id;
    }

    /*
    GetUserInfo: returns User info
    param: none
    return: String
     */
    public String GetUserInfo(){
        return this.user_info;
    }

    /*
   GetUserPassword: Get user password
   param: none
   return: String
    */
    public String GetUserPassword(){
        return this.user_password;
    }

    /*
    SetUserInfo: sets new user info
    param: String
    return: none
     */
    public void SetUserInfo(String info){
        this.user_info = info;
    }

    /*
    SetSUserID: sets new user ID
    param: String
    return: none
     */
    public void SetUserID(String ID){
        this.user_id = ID;
    }

    /*
    SetUserPassword: sets new user password
    param: String
    return: none
     */
    private void SetUserPassword(String pass){
        this.user_password = pass;
    }

    public String getUser_id() {return user_id;}

    public String getFull_name() {return full_name;}

    public String getUser_info() {return user_info;}


}
