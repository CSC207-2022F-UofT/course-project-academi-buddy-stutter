package UseCases;

import java.util.ArrayList;
import java.util.List;

public class StrengthChecker {
    public StrengthChecker() {
    }

    public List<String> checkStrength(String password) {
        List<String> warnings = new ArrayList<String>();

        // Check if password contains empty spaces;
        if (password.matches("(\\s)*")) {
            warnings.add("EMPTY SPACES");
        }
        // Check if password has whitespaces
        if (password.matches("(.)*(\\s)+(.)*")) {
            warnings.add("Whitespace not allowed");
        }
        // Check if password is greater than or equal to 8 chars
        if (password.length() < 8) {
            warnings.add("Add more characters");
        }
        // Check if password is lesser than 30 chars
        if (password.length() > 30) {
            warnings.add("Password too long, max char is 30");
        }
        // Check if password has no numbers
        if (!password.matches("(.)*(\\d)(.)*")) {
            warnings.add("Add any digit");
        }
        // Check if password has symbols
        if (!password.matches("(.)*[\\*\\!\\@\\#\\$\\%\\^\\&\\_\\-\\+\\=\\.\\'\\~\\,\\(\\)\\:\\;\\<\\>\\[\\]\\|\\}\\{\\/]+(.)*")) {
            warnings.add("Add a symbol");
        }
        return warnings;

    }
}