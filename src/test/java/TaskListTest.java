import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getAtIndTest() {
        TaskList taskList = new TaskList();
        taskList.addTodo("test ");
        assertEquals("[T][ ] test ", taskList.getAtInd(0).toString());
    }

    @Test
    public void getNumItemsTest() {
        TaskList taskList = new TaskList();
        taskList.addTodo("test1 ");
        taskList.addTodo("test2 ");
        assertEquals(2, taskList.getNumItems());
    }
}
