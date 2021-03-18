package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for Ui class.
 */
public class TestUi {

    @Test
    public void TestGreeting() {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        assertEquals(new Ui().printGreeting(), greeting);
    }

    @Test
    public void TestByeMessage() {
        String byeMessage = "Bye. Hope to see you again soon!\n";
        assertEquals(new Ui().printBye(), byeMessage);
    }

    @Test
    public void TestPrintError() {
        String error = "error";
        assertEquals(new Ui().printError(error), error);
    }
}
