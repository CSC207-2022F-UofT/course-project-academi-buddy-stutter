package entities;

/**
 * Implement an administrative user from parent class User
 */
public class Admin extends User{
    /**
     * Constructs an admin user
     * @param UID user id
     * @param UPass user password
     * @param full_name user's full name
     * @param info user info
     */
    public Admin(String UID, String UPass, String full_name, String info){
        super(UID, UPass, full_name, info);
    }
}
