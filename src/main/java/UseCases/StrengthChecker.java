package UseCases;

import java.util.ArrayList;

public class StrengthChecker {
    public StrengthChecker() {
    }

    public ArrayList<String> checkStrength(String password) {
        ArrayList<String> warnings = new ArrayList<>();

        // Check if password contains empty spaces;
        if (password.matches("(\\s)*")) {
            warnings.add("EMPTY SPACES");
        }
        // Check if password has whitespaces
        else if (password.matches("(.)*(\\s)+(.)*")) {
            warnings.add("Whitespace not allowed");
        }
        // Check if password is greater than or equal to 8 chars
        else if (password.length() < 8) {
            warnings.add("Add more characters");
        }
        // Check if password is lesser than 30 chars
        else if (password.length() > 30) {
            warnings.add("Password too long, max char is 30");
        }
        // Check if password has no numbers
        else if (!password.matches("(.)*(\\d)(.)*")) {
            warnings.add("Add any digit");
        }
        // Check if password has symbols
        else if (!password.matches("(.)*[\\*\\!\\@\\#\\$\\%\\^\\&\\_\\-\\+\\=\\.\\'\\~\\,\\(\\)\\:\\;\\<\\>\\[\\]\\|\\}\\{\\/]+(.)*")) {
            warnings.add("Add a symbol");
        }
        return warnings;

    }
}
