import static org.junit.jupiter.api.Assertions.assertEquals;

import Entry.Task;
import Entry.Todo;
import Oracle.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private final Ui ui = new Ui();

    @Test
    void showFormatException() {
        System.setOut(new PrintStream(outContent));
        ui.showFormatException("EventCommand");
        String res = "Event:    event {description} /{day} {month} {year} {hour}{minute}";
        assertEquals(res, outContent.toString().substring(0, res.length()));
        System.setOut(originalOut);
    }

    @Test
    void showDeleteTask() {
        System.setOut(new PrintStream(outContent));
        int i = 3;
        Task t = new Todo("repair watch");
        ui.showDeleteTask(i, t);
        String res = "Erased: 4. [T][ ] repair watch";
        assertEquals(res, outContent.toString().substring(0, res.length()));
        System.setOut(originalOut);
    }
}

