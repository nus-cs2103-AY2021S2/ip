import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void borderPrintTest() {
        Ui ui = new Ui();

        String expectedPrint = "____________________________________________________________\r\n"
                + "Hello world!\r\n"
                + "____________________________________________________________";

        ui.borderPrint("Hello world!");

        assertEquals(expectedPrint, outputStreamCaptor.toString().trim());
    }

    @Test
    void showErrorTest() {
        Ui ui = new Ui();

        try {
            throw new DukeException("Hello world!");
        } catch (DukeException e) {
            ui.showError(e);
        }

        String expectedPrint = "____________________________________________________________\r\n"
                + "Hello world!\r\n"
                + "____________________________________________________________";

        assertEquals(expectedPrint, outputStreamCaptor.toString().trim());
    }
}