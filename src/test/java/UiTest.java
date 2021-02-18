import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ui.Ui;

public class UiTest {
    private final Ui ui = new Ui();

    @Test
    public void showWelcomeTest() {
        assertEquals("Hello! I'm Duke, your personal assistant!\n" +
                "What can I do for you today?", Ui.showWelcome());
    }

    @Test
    public void showErrorTest() {
        assertEquals("OOPS! Invalid input", ui.showError("Invalid input"));
    }
}
