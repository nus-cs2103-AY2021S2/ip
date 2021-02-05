import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void addTask() {
        Task dummyTask = new Task("homework");

        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        taskArrayList.add(dummyTask);

        TaskList tl = new TaskList();
        tl.addTask(dummyTask);
        assertEquals(taskArrayList.get(0), tl.getSingleTask(0));
    }

    @Test
    void deleteTask() {
        Task dummyTask = new Task("homework");
        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        taskArrayList.add(dummyTask);
        taskArrayList.remove(0);

        TaskList tl = new TaskList();
        tl.addTask(dummyTask);
        tl.deleteTask(0);
        assertEquals(taskArrayList.size(), tl.getSize());
    }

    //  To be tested
    @Test
    void getTasks() {
    }

    @Test
    void getSingleTask() {
    }

    @Test
    void getSize() {
    }
}