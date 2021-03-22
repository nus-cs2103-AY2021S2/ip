package seashell.command;

import org.junit.jupiter.api.Test;
import seashell.SeashellTest;
import seashell.TaskList;
import seashell.exception.SeashellException;
import seashell.task.Deadline;
import seashell.task.Event;
import seashell.task.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null, null));
    }

    @Test
    public void execute_todoTask_addSuccessful() throws SeashellException {
        Todo task = new Todo("test");
        TaskList taskList = new TaskList();
        taskList.add(task);

        SeashellTest seashellTest = new SeashellTest();
        String output = seashellTest.getResponse("todo test");

        String sb = "Added " + task + " to the task list!\n" +
                "You now have " + taskList.size() + " items in the task list";
        assertEquals(sb, output);
    }

    @Test
    public void execute_deadlineTask_addSuccessful() throws SeashellException {
        Deadline deadline = new Deadline("test", "2021-03-01");
        TaskList taskList = new TaskList();
        taskList.add(deadline);

        SeashellTest seashellTest = new SeashellTest();
        String output = seashellTest.getResponse("deadline test /by 2021-03-01");

        String sb = "Added " + deadline + " to the task list!\n" +
                "You now have " + taskList.size() + " items in the task list";
        assertEquals(sb, output);
    }

    @Test
    public void execute_eventTask_addSuccessful() throws SeashellException {
        Event event = new Event("test", "2021-03-01");
        TaskList taskList = new TaskList();
        taskList.add(event);

        SeashellTest seashellTest = new SeashellTest();
        String output = seashellTest.getResponse("event test /at 2021-03-01");

        String sb = "Added " + event + " to the task list!\n" +
                "You now have " + taskList.size() + " items in the task list";
        assertEquals(sb, output);
    }
}