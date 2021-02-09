import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ui.Ui;

public class UiTest {
    private Ui ui = new Ui();

    @Test
    public void showWelcomeTest() {
        assertEquals("Hello! I'm Duke\nWhat can I do for you?", ui.showWelcome());
    }

    @Test
    public void showErrorTest() {
        assertEquals(":( OOPS!!! Invalid Input", ui.showError("Invalid Input"));
    }
}
