package seashell.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seashell.SeashellTest;
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

        SeashellTest seashellTest = new SeashellTest(taskList);
        String output = seashellTest.getResponse("clear");

        assertEquals("Task list has been cleared!", output);
        assertEquals(new TaskList(), seashellTest.getTaskList());
    }

    @Test
    public void execute_clearList_unsuccessful() {
        SeashellTest seashellTest = new SeashellTest();
        String seashellExceptionMessage = seashellTest.getResponse("clear");
        String expected = "OOPS!!! Task list is already empty!";
        assertEquals(expected, seashellExceptionMessage);
    }
}
