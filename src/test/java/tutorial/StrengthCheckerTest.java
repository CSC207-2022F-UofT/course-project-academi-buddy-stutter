//package tutorial;
//
//import org.junit.jupiter.api.Test;
//
//import UseCases.StrengthChecker;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class StrengthCheckerTest {
//    @Test
//    public void StrengthCheckerNoDigit() {
//        String password = "password";
//        StrengthChecker strengthChecker = new StrengthChecker();
//        List<String> warnings = strengthChecker.checkStrength(password);
//        List<String> result = new ArrayList<String>();
//        result.add("Add any digit");
//
//        Assertions.assertEquals(result, warnings);
//    }
//
//    @Test
//    public void StrengthCheckerWhiteSpace() {
//        String password = "1 ";
//        StrengthChecker strengthChecker = new StrengthChecker();
//        List<String> warnings = strengthChecker.checkStrength(password);
//        List<String> result = new ArrayList<String>();
//        result.add("Whitespace not allowed");
//
//        Assertions.assertEquals(result, warnings);
//    }
//
//    @Test
//    public void StrengthCheckerEmpty() {
//        String password = "";
//        StrengthChecker strengthChecker = new StrengthChecker();
//        List<String> warnings = strengthChecker.checkStrength(password);
//        List<String> result = new ArrayList<String>();
//        result.add("EMPTY SPACES");
//
//        Assertions.assertEquals(result, warnings);
//    }
//
//    @Test
//    public void StrengthCheckerMoreCharacters() {
//        String password = "1234";
//        StrengthChecker strengthChecker = new StrengthChecker();
//        List<String> warnings = strengthChecker.checkStrength(password);
//        List<String> result = new ArrayList<String>();
//        result.add("Add more characters");
//
//        Assertions.assertEquals(result, warnings);
//    }
//
//    @Test
//    public void StrengthCheckerMaxCharacters() {
//        String password = "asdfjkl;asdjfklas;jdflskjsldkfjslkfjalskjdflskdjflskdjflskdjflskdjflskdjf";
//        StrengthChecker strengthChecker = new StrengthChecker();
//        List<String> warnings = strengthChecker.checkStrength(password);
//        List<String> result = new ArrayList<String>();
//        result.add("Password too long, max char is 30");
//
//        Assertions.assertEquals(result, warnings);
//    }
//
//    @Test
//    public void StrengthCheckerNoSymbol() {
//        String password = "abcdef1234";
//        StrengthChecker strengthChecker = new StrengthChecker();
//        List<String> warnings = strengthChecker.checkStrength(password);
//        List<String> result = new ArrayList<String>();
//        result.add("Add a symbol");
//
//        Assertions.assertEquals(result, warnings);
//    }
//}
