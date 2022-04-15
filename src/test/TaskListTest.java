import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private static List<Task> testList;

    @BeforeAll
    static void setUpList() {
        testList = new ArrayList<>();
    }


    @Test
    void addTask_newToDo_success() {
        TaskList taskList = new TaskList(testList);
        taskList.addTask("todo borrow book");
        assertEquals(1, taskList.getNewStorage().size());
    }

    /*
    @Test
    void listTask_noInputs_success() {
        TaskList taskList = new TaskList(testList);
        taskList.addTask("todo borrow book");
        String expectedOutput = "Here are the tasks in your list:" + "\n" +
                "1. [T][ ] borrow book";
        assertEquals(expectedOutput, taskList.listTask(taskList.getNewStorage()));

    }
    */


    @Test
    void setTaskAsDone() {
        TaskList taskList = new TaskList(testList);
        taskList.setTaskAsDone(0);
        assertTrue(taskList.getNewStorage().get(0).getDone());
    }

    @Test
    void deleteTask() {
        TaskList taskList = new TaskList(testList);
        taskList.deleteTask("delete 1");
        assertEquals(0, taskList.getNewStorage().size());
    }


}