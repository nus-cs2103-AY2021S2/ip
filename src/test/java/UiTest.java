import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void respondToList_taskSizeZero_success() {
        Ui ui = new Ui();
        String response = ui.respondToList(0);
        assertEquals("Your list is currently empty! Let's start adding tasks!", response);
    }

}
