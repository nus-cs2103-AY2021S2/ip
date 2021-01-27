import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTest() {
        TaskList list = new TaskList();
        Task task = new Task("make this right");
        list.add(task);
        assertEquals(list.list.get(0), task);

    }

    @Test
    public void addTodoTest(){
        TaskList list = new TaskList();
        list.addTodo("testcase2");
        assertEquals(list.list.get(0).task, "testcase2");
    }
}
