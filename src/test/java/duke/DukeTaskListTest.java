package duke;

import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTaskListTest {
    @Test
    public void sizeTest() {
        ArrayList<Task> taskList = new ArrayList<>();
        Task dummyTask = new Todo("test");
        taskList.add(dummyTask);
        taskList.add(dummyTask);
        taskList.add(dummyTask);
        taskList.add(dummyTask);
        taskList.add(dummyTask);
        DukeTaskList dummyTaskList = new DukeTaskList(taskList);
        assertEquals(dummyTaskList.size(), 5);
    }

    @Test
    public void getTest() {
        ArrayList<Task> taskList = new ArrayList<>();
        Todo task = new Todo("test");
        taskList.add(task);
        DukeTaskList dummyTaskList = new DukeTaskList(taskList);
        assertEquals(dummyTaskList.get(0), task);
    }

    @Test
    public void getTaskListTest() {
        ArrayList<Task> taskList = new ArrayList<>();
        DukeTaskList dummyTaskList = new DukeTaskList(taskList);
        assertEquals(dummyTaskList.getTaskList(), taskList);
    }
}
