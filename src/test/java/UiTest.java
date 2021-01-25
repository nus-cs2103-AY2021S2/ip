
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.ui.Ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

// Very helpful: https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
// and this : https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println/1119559#1119559
public class UiTest {

    @BeforeEach
    void init() {}

    @Test
    void getUserInput_normalInput_noException() {
        // ByteArrayOutputStream out = JUnitUtility.prepareStdout(); ... out.toString();
        JUnitUtility.prepareStdin("random input\n");
        Ui ui = new Ui();
        try {
            String result = ui.getUserInput();
            assertEquals("random input", result);
        } catch (DukeExceptionIllegalArgument e) {
            fail("Should not throw exception.");
        }
    }

    @AfterAll
    static void teardown() {
        JUnitUtility.resetStdin();
    }
}