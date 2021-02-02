import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testShowLine() {
        new Ui().showLine();
        assertEquals("    ____________________________________\n",
                outputStreamCaptor.toString());
    }

    @Test
    public void testShowBye() {
        new Ui().showBye();
        assertEquals("    Bye. Hope to see you again soon!\n",
                outputStreamCaptor.toString());
    }

    @Test
    public void testError() {
        new Ui().showError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        assertEquals("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n",
                outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
