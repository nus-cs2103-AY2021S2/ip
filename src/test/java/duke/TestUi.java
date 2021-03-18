package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for Ui class.
 */
public class TestUi {

    /**
     * Tests greeting.
     */
    @Test
    public void testGreeting() {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        assertEquals(new Ui().printGreeting(), greeting);
    }

    /**
     * Tests exit message.
     */
    @Test
    public void testByeMessage() {
        String byeMessage = "Bye. Hope to see you again soon!\n";
        assertEquals(new Ui().printBye(), byeMessage);
    }

    /**
     * Tests error message.
     */
    @Test
    public void testPrintError() {
        String error = "error";
        assertEquals(new Ui().printError(error), error);
    }
}
