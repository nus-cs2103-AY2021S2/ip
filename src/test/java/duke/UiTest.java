package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.ui.CliUi;
import duke.ui.Ui;

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
        Ui ui = new CliUi();
        ui.printHorizontalLine();
        assertEquals("\t" + CliUi.HORIZONTAL_LINE + "\n", outContent.toString());
    }

    @Test
    public void testIntro() {
        Ui ui = new CliUi();
        ui.printIntro();
        assertEquals("Hello from\n" + CliUi.LOGO + "\n\t" + CliUi.HORIZONTAL_LINE
                + "\n\tWhat can I do for you?\n\t" + CliUi.HORIZONTAL_LINE + '\n', outContent.toString());
    }
}
