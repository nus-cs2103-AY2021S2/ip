package seashell.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seashell.SeashellStub;
import seashell.TaskList;
import seashell.task.Deadline;
import seashell.task.Event;
import seashell.task.Todo;


public class AddCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null, null));
    }

    @Test
    public void execute_todoTask_addSuccessful() {
        Todo task = new Todo("test");
        TaskList taskList = new TaskList();
        taskList.add(task);

        SeashellStub seashellStub = new SeashellStub();
        String output = seashellStub.getResponse("todo test");

        String sb = "Added " + task + " to the task list!\n"
                + "You now have " + taskList.size() + " items in the task list";
        assertEquals(sb, output);
    }

    @Test
    public void execute_deadlineTask_addSuccessful() {
        Deadline deadline = new Deadline("test", "2021-03-01");
        TaskList taskList = new TaskList();
        taskList.add(deadline);

        SeashellStub seashellStub = new SeashellStub();
        String output = seashellStub.getResponse("deadline test /by 2021-03-01");

        String sb = "Added " + deadline + " to the task list!\n"
                + "You now have " + taskList.size() + " items in the task list";
        assertEquals(sb, output);
    }

    @Test
    public void execute_eventTask_addSuccessful() {
        Event event = new Event("test", "2021-03-01");
        TaskList taskList = new TaskList();
        taskList.add(event);

        SeashellStub seashellStub = new SeashellStub();
        String output = seashellStub.getResponse("event test /at 2021-03-01");

        String sb = "Added " + event + " to the task list!\n"
                + "You now have " + taskList.size() + " items in the task list";
        assertEquals(sb, output);
    }
}
