package seashell.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seashell.SeashellStub;
import seashell.TaskList;
import seashell.exception.SeashellException;
import seashell.task.Deadline;
import seashell.task.Event;
import seashell.task.Todo;


public class ClearCommandTest {

    @Test
    public void execute_clearList_successful() throws SeashellException {
        Todo todo = new Todo("test");
        Deadline deadline = new Deadline("test", "2021-03-01");
        Event event = new Event("test", "2021-03-01");
        TaskList taskList = new TaskList();
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);

        SeashellStub seashellStub = new SeashellStub(taskList);
        String output = seashellStub.getResponse("clear");

        assertEquals("Task list has been cleared!", output);
        assertEquals(new TaskList(), seashellStub.getTaskList());
    }

    @Test
    public void execute_clearList_unsuccessful() {
        SeashellStub seashellStub = new SeashellStub();
        Command command = new ListCommand();
        TaskList emptyTaskList = new TaskList();
        assertThrows(SeashellException.class, () -> command.execute(emptyTaskList));
    }
}
