package UseCases;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class StrengthCheckerTest extends Tests {

    @Test
    void StrengthCheckerTest() {

        String password = "hello";

        StrengthChecker x = new StrengthChecker();

        List<String> actual = x.checkStrength(password);

        assertEquals(actual, actual);

    }




}
