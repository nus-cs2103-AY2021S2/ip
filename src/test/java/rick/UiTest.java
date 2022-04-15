package rick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final Ui ui = new Ui();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void showInputErrorTest() {
        ui.showInputError();
        assertEquals(Ui.divider + "I'm sorry, but I don't know what that means.\n" + Ui.divider + "\n", outputStreamCaptor.toString());
    }

    @Test
    public void showLoadingErrorTest() {
        ui.showLoadingError();
        assertEquals(Ui.divider + "Failed to load file. Exiting...\n" + Ui.divider + "\n", outputStreamCaptor.toString());
    }

    @Test
    public void showMessage() {
        ui.showMessage("");
        assertEquals(Ui.divider + "\n" + Ui.divider + "\n", outputStreamCaptor.toString());
    }
}
