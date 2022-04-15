import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void testShowLine() {
        assertEquals("    ____________________________________", new Ui().showLine());
    }

    @Test
    public void testShowBye() {
        new Ui().showBye();
        assertEquals("Bye. Hope to see you again soon!",
                new Ui().showBye());
    }
}
