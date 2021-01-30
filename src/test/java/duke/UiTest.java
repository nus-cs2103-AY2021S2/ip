package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Ui ui = new Ui();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void greetingsTest() {
        ui.greetings();
        assertEquals("____________________________________________________________\n" +
                "Hello! I'm your personal assistant Duke\n" +
                "How can I assist you?\n" +
                "____________________________________________________________", outputStreamCaptor.toString().trim());
    }

    @Test
    public void exitTest() {
        ui.exit();
        assertEquals("Bye. Till next time!"+System.lineSeparator()+Ui.horizontalLine, outputStreamCaptor.toString().trim());
    }
}
