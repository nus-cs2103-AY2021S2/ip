package duke.tasklist;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private static TaskList taskList = new TaskList();

    @Test
    public void addingTaskTest(){
        taskList.addToDo("testing description");
        taskList.addDeadline("test deadline", LocalDate.of(2021, 1,25),
                LocalTime.NOON);
        taskList.addEvent("test event", LocalDate.of(2021, 1,26),
                LocalTime.NOON, LocalTime.MIDNIGHT);

        assertEquals("[T][ ] testing description",  taskList.getTaskListArray().get(1).toString());
        assertEquals("[D][ ] test deadline (by: 2021-01-25 12:00)",  taskList.getTaskListArray().get(2).toString());
        assertEquals("[E][ ] test event(at: 2021-01-26 12:00 00:00)",  taskList.getTaskListArray().get(3).toString());
    }

    @Test
    public void markAsDoneTest() throws DukeException {
        taskList.addToDo("do ip");
        taskList.markAsDone(0);
        assertEquals("[T][✘] do ip", taskList.getTaskListArray().get(0).toString());
    }

    @Test
    public void deleteTaskTest() throws DukeException {
        taskList.deleteTask(0);
        assertEquals("[T][ ] testing description", taskList.getTaskListArray().get(0).toString());
    }
}
