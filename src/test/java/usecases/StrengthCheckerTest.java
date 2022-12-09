package usecases;

import database.local.LocalTempDataBuilder;
import model.usecases.StrengthChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class StrengthCheckerTest extends LocalTempDataBuilder {


    @Test
    void StrengthCheckTestCase1() {

        String password = " ";

        StrengthChecker x = new StrengthChecker();

        List<String> actual = x.checkStrength(password);

        List<String> expected = new ArrayList<>();
        expected.add("Empty space not allowed");
        expected.add("Whitespace not allowed");
        expected.add("Password too short");
        expected.add("Add a number");
        expected.add("Add a symbol");

        Assertions.assertEquals(expected, actual);

    }
    @Test
    void StrengthCheckTestCase2() {

        String password = "* 1111111";

        StrengthChecker x = new StrengthChecker();

        List<String> actual = x.checkStrength(password);

        List<String> expected = new ArrayList<>();
        expected.add("Whitespace not allowed");

        Assertions.assertEquals(expected , actual);

    }
    @Test
    void StrengthCheckTestCase3() {

        String password = "1*";

        StrengthChecker x = new StrengthChecker();

        List<String> actual = x.checkStrength(password);

        List<String> expected = new ArrayList<>();
        expected.add("Password too short");


        Assertions.assertEquals(expected , actual);

    }

    @Test
    void StrengthCheckTestCase4() {

        String password = "hello1aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa*";

        StrengthChecker x = new StrengthChecker();

        List<String> actual = x.checkStrength(password);

        List<String> expected = new ArrayList<>();
        expected.add("Password too long");

        Assertions.assertEquals(expected , actual);

    }
    @Test
    void StrengthCheckTestCase5() {

        String password = "hell*oooooooo";

        StrengthChecker x = new StrengthChecker();

        List<String> actual = x.checkStrength(password);

        List<String> expected = new ArrayList<>();
        expected.add("Add a number");



        Assertions.assertEquals(expected , actual);

    }
    @Test
    void StrengthCheckTestCase6() {

        String password = "hello1ooooooo";

        StrengthChecker x = new StrengthChecker();

        List<String> actual = x.checkStrength(password);

        List<String> expected = new ArrayList<>();
        expected.add("Add a symbol");


        Assertions.assertEquals(expected , actual);

    }

}
