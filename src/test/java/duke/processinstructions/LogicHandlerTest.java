package duke.processinstructions;

import duke.processinstructions.LogicHandler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.List;
import java.util.ArrayList;

import duke.tasks.Task;

import static org.junit.jupiter.api.Assertions.*;

class LogicHandlerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void out() {
        System.out.print("hello");
        assertEquals("hello", outContent.toString());
    }

    @Test
    public void testTodoOutput() {
        LogicHandler lh = new LogicHandler();
        List<Task> tempList = new ArrayList<>();

        assertEquals("added: [T][✘] homework\nNow you have 1 tasks in the list."
                , lh.todo("todo homework", tempList));
    }

    @Test
    public void testDeadlineOutput() {
        LogicHandler lh = new LogicHandler();
        List<Task> tempList = new ArrayList<>();

        assertEquals("added: [D][✘] homework (by: Jan 31 2021)\nNow you have 1 tasks in the list."
                , lh.deadline("deadline homework /by 2021-01-31", tempList));
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
