package soonkeatneo.duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        Ui ui = new Ui();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void greetUser_correctOutputPrinted() {
        Ui.greetUser();
        String expected = "Henlooooo~! My name is " + Duke.BOT_NAME + "\n" +
                "    What can I do for you today? :)";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void showTestMessage_correctOutputPrinted() {
        Ui.printMessage("Test");
        String expected = "    Test\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void showSeparators_correctOutputPrinted() {
        Ui.printSeparators();
        String expected = "~~~~~~~~~~~~~~~~~~~~~~\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }
}