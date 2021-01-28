package duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testHorizontalLine() {
        Ui ui = new Ui();
        ui.printHorizontalLine();
        assertEquals("\t" + Ui.HORIZONTAL_LINE + "\n", outContent.toString());
    }

    @Test
    public void testIntro() {
        Ui ui = new Ui();
        ui.printIntro();
        assertEquals("Hello from\n" + Ui.LOGO + "\n\t" + Ui.HORIZONTAL_LINE + "\n\tWhat can I do for you?\n\t" + Ui.HORIZONTAL_LINE + '\n', outContent.toString());
    }
}
