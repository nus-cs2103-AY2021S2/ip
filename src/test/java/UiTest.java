import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.Ui;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Ui ui = new Ui();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void showWelcomeTest() {
        ui.showWelcome();
        assertEquals("____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________", outputStreamCaptor.toString().trim());
    }

    @Test
    public void showLineTest() {
        ui.showLine();
        assertEquals("____________________________________________________________",
                    outputStreamCaptor.toString().trim());
    }

    @Test
    public void showErrorTest() {
        ui.showError("Invalid Input");
        assertEquals("☹️ OOPS!!! Invalid Input", outputStreamCaptor.toString().trim());
    }
}
