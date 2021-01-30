package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    // Ui testing adapted from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Reassign the standard output stream to a new PrintStream with a ByteArrayOutputStream
        // Any output will now be directed to this output stream
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        // Restore to original state by assigning output stream back to System.out
        System.setOut(stdout);
    }

    @Test
    public void printDivider_whenInvoke_thenOutputCaptorSuccess() {
        new Ui().printDivider();
        assertEquals("------------------------------------------------------------",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void printGreeting_whenInvoke_thenOutputCaptorSuccess() {
        new Ui().printGreeting();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        assertEquals("Hello! I'm\n" + logo + "\nWhat can I do for you?",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void printExitMessage_whenInvoke_thenOutputCaptorSuccess() {
        new Ui().printExitMessage();
        assertEquals("Bye. Hope to see you again soon!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void print_whenInvoke_thenOutputCaptorSuccess() {
        new Ui().print("Testing print method of Ui class.");
        assertEquals("Testing print method of Ui class.", outputStreamCaptor.toString().trim());
    }
}
