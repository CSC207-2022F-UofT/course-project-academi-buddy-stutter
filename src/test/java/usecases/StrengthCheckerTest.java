package usecases;

import database.local.LocalTempDataFactory;
import model.usecases.StrengthChecker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class StrengthCheckerTest extends LocalTempDataFactory {

    @Test
    void StrengthCheckTest() {

        String password = "hello";

        StrengthChecker x = new StrengthChecker();

        List<String> actual = x.checkStrength(password);

        List<String> expected = new ArrayList<>();
        expected.add("Password too short");
        expected.add("Add a number");
        expected.add("Add a symbol");


        assertEquals(expected , actual);

    }




}
