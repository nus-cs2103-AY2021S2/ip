import duke.Task;
import duke.TaskList;
import duke.ToDo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void completeTaskTest() throws IOException {
        ToDo newToDo = new ToDo("read book");
        TaskList.taskList.add(newToDo);
        TaskList.completeTask(newToDo);
        assertEquals("[X]", TaskList.taskList.get(0).checkBox);
    }

    @Test
    public void findTest() throws IOException {
        List<Task> tasks = new ArrayList<>();
        ToDo newToDo1 = new ToDo("read book");
        TaskList.completeTask(newToDo1);
        ToDo newToDo2 = new ToDo("return book");
        ToDo newToDo3 = new ToDo("borrow new book");
        tasks.add(newToDo1);
        tasks.add(newToDo2);
        tasks.add(newToDo3);
        TaskList.taskList.add(newToDo2);
        TaskList.taskList.add(newToDo3);
        assertEquals(tasks, TaskList.find("book"));
    }
}
