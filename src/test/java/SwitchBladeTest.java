import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchblade.Parser;
import switchblade.Task;
import switchblade.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwitchBladeTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void createStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Test
    public void uiAddTaskTest() {
        Task t = new Task("read books");
        ArrayList<Task> taskArr = new ArrayList<>();

        taskArr.add(t);

        assertEquals("Added to list:\n" + t + "\nYou now have " + taskArr.size() + " tasks\n"
                , Ui.addedTask(taskArr));
    }

    @Test
    public void parserDeadlineDescTest() {
        assertEquals("read books", Parser.findDescription("deadline read books /by 2021-12-05"));
    }

    @Test
    public void taskTest() {
        Task t = new Task("read books");
        assertEquals("[T] [ ] read books", t.toString());
    }
}
