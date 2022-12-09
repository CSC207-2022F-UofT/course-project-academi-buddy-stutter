package model.usecases;

import java.util.ArrayList;
import java.util.List;

/**
 * The strength checker for the password component in register.
 */
public class StrengthChecker {

    public StrengthChecker() {
    }

    /**
     * To check the strength of the password.
     * @param password The password that is being checked.
     * @return The List of Strings of warnings if any.
     */
    public List<String> checkStrength(String password) {
        List<String> warnings = new ArrayList<>();

        // Check if password contains empty spaces;
        if (password.matches("(\\s)*")) {
            warnings.add("Empty space not allowed");
        }
        // Check if password has whitespaces
        if (password.matches("(.)*(\\s)+(.)*")) {
            warnings.add("Whitespace not allowed");
        }
        // Check if password is greater than or equal to 8 chars
        if (password.length() < 8) {
            warnings.add("Password too short");
        }
        // Check if password is lesser than 30 chars
        if (password.length() > 30) {
            warnings.add("Password too long");
        }
        // Check if password has no numbers
        if (!password.matches("(.)*(\\d)(.)*")) {
            warnings.add("Add a number");
        }
        // Check if password has symbols
        if (!password.matches("(.)*[\\*\\!\\@\\#\\$\\%\\^\\&\\_\\-\\+\\=\\.\\'\\~\\,\\(\\)\\:\\;\\<\\>\\[\\]\\|\\}\\{\\/]+(.)*")) {
            warnings.add("Add a symbol");
        }
        return warnings;

    }
}