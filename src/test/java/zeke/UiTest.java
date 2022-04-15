package zeke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {

    private Ui ui;

    public UiTest() {
        ui = new Ui();
    }

    @Test
    public void greetingsTest() {
        assertEquals("Eren... I have waited to meet you for a long time.\n"
                + "------\n"
                + "How can Zeke assist you?\n"
                + "Type \"help\" for the list of commands!", Ui.greetings());
    }

    @Test
    public void exitTest() {
        assertEquals("Bye Eren. Till next time.", ui.exit());
    }
}
