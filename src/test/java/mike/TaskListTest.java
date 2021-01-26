package mike;

import org.junit.Test;
import task.DeadlineTask;
import task.EventTask;
import task.TodoTask;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class TaskListTest {
    @Test
    public void TaskListToStringTest() {
        String expectedResponse =
                "1. [T] [ ] task1\n" +
                "2. [T] [ ] task2\n" +
                "3. [E] [ ] Event1 (at: 09 Oct 2000, 10:00 PM)\n" +
                "4. [D] [ ] Deadline1 (by: 09 Oct 2000, 10:00 PM)\n";

        TaskList taskList = new TaskList();
        taskList.addTaskToList(new TodoTask("task1"));
        taskList.addTaskToList(new TodoTask("task2"));
        taskList.addTaskToList(new EventTask("Event1", LocalDateTime.of(2000,10,9,22,0)));
        taskList.addTaskToList(new DeadlineTask("Deadline1", LocalDateTime.of(2000,10,9,22,0)));

        assertEquals(expectedResponse, taskList.toString());
    }
}
