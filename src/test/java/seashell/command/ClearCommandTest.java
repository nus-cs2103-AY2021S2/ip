package seashell.command;

import org.junit.jupiter.api.Test;
import seashell.SeashellTest;
import seashell.TaskList;
import seashell.exception.SeashellException;
import seashell.task.Deadline;
import seashell.task.Event;
import seashell.task.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class ClearCommandTest {

    @Test
    public void execute_clearList_Successful() throws SeashellException {
        Todo todo = new Todo("test");
        Deadline deadline = new Deadline("test", "2021-03-01");
        Event event = new Event("test", "2021-03-01");
        TaskList taskList = new TaskList();
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);

        SeashellTest seashellTest = new SeashellTest(taskList);
        String output = seashellTest.getResponse("clear");

        assertEquals("Task list has been cleared!", output);
        assertEquals(new TaskList(), seashellTest.taskListObj);
    }

    @Test
    public void list_empty_throwsSeashellException() {
        SeashellTest seashellTest = new SeashellTest();
        assertThrows(SeashellException.class, () -> seashellTest.getResponse("clear"));
    }
}