import controller.DukeException;
import controller.Parser;
import controller.TaskList;
import controller.Ui;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.ToDo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    @Test
    public void testTodo_emptyDescription_exceptionThrown() {
        try {
            Ui ui = new Ui();
            Parser parser = new Parser(ui);
            TaskList taskList = new TaskList();
            parser.handleTodo("todo", taskList);
        } catch (DukeException e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testTodo_nonEmptyDescription_success() {
        try {
            Ui ui = new Ui();
            Parser parser = new Parser(ui);
            TaskList taskList = new TaskList();
            parser.handleTodo("todo a", taskList);
            String result = taskList.toString();
            String expected = "1. [T][ ] a\n";
            assertEquals(result, expected);
        } catch (DukeException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testDeadLine_noDeadLine_exceptionThrown() {
        try {
            Ui ui = new Ui();
            Parser parser = new Parser(ui);
            TaskList taskList = new TaskList();
            parser.handleTasksWithTime("deadline", "deadline a", taskList);
        } catch (DukeException e) {
            assertEquals(
                    "The timing of the task is not included. Please check your input.",
                    e.getMessage());
        }
    }

    @Test
    public void testDeadLine_wrongDateFormat_exceptionThrown() {
        try {
            Ui ui = new Ui();
            Parser parser = new Parser(ui);
            TaskList taskList = new TaskList();
            parser.handleTasksWithTime("deadline", "deadline a /by jlhsfadjkbhsfdajk", taskList);
        } catch (DukeException e) {
            assertEquals(
                    "Please input a date with correct format (yyyy-mm-dd).",
                    e.getMessage());
        }
    }

    @Test
    public void testDelete_indexOutOfBounds_exceptionThrown() {
        try{
            Ui ui = new Ui();
            Parser parser = new Parser(ui);
            TaskList taskList = new TaskList();
            parser.handleDelete("delete 2", taskList);
        } catch (DukeException e) {
            assertEquals("You have 0 tasks in your list. Please check your input.", e.getMessage());
        }
    }

    @Test
    public void testDelete_correctInput_success() {
        try{
            Ui ui = new Ui();
            Parser parser = new Parser(ui);
            TaskList taskList = new TaskList();
            taskList.addTask(new Event("a", LocalDate.of(2021, 9,1)));
            taskList.addTask(new Deadline("b", LocalDate.of(1999,3,2)));
            taskList.addTask(new ToDo("c"));
            parser.handleDelete("delete 2", taskList);
            assertEquals("a", taskList.getTaskAtIndex(1).getTaskName());
            assertEquals(LocalDate.of(2021, 9, 1),
                    taskList.getTaskAtIndex(1).getTaskTime());
            assertEquals("c", taskList.getTaskAtIndex(2).getTaskName());
        } catch (DukeException e) {
            e.printStackTrace();
            fail();
        }
    }
}
