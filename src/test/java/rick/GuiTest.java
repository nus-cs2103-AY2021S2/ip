package rick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuiTest {
    private final Gui gui = new Gui();

    @Test
    public void getLoadingErrorTest() {
        assertEquals("Rick failed to load file. Rick failure. Exiting...", gui.getLoadingErrorString());
    }

    @Test
    public void getSavingErrorTest() {
        assertEquals("Rick failed to save file. Rick failure. Exiting...", gui.getSavingErrorString());
    }

    @Test
    public void getErrorMessageStringTest() {
        assertEquals("Error, Morty! Error! ", gui.getErrorMessageString(""));
    }
}
