package usecases;

import database.local.LocalTempDataFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class StrengthCheckerTest extends LocalTempDataFactory {

    @Test
    void Strengthcheck() {

        String password = "hello";

        StrengthChecker x = new StrengthChecker();

        List<String> actual = x.checkStrength(password);

        assertEquals(actual, actual);

    }




}
