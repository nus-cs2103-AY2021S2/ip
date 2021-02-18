import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void getTest() {
        TaskList list = new TaskList();
        Task task = new Todo("return books");
        list.add(task);
        assertEquals(list.getList().get(0), task);

    }

    @Test
    public void addTodoTest() {
        TaskList list = new TaskList();
        list.addTodo("testcase2");
        assertEquals(list.getList().get(0).getTask() , "testcase2");
    }
}
