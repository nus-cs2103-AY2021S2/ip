package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void testPrintWelcomeGreeting(){
        new Ui().printWelcomeGreeting();
        assertEquals(HORIZONTAL_RULE + "\nHello! I am Duke\n" + "What can I do for you?\n" + HORIZONTAL_RULE,
                outputStreamCaptor.toString().trim());
    }
    @Test
    public void testPrintExitMessage(){
        new Ui().printExitMessage();
        assertEquals("GoodBye. Hope to see you again soon!\n" + HORIZONTAL_RULE, outputStreamCaptor.toString().trim());
    }
}
