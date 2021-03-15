import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.duke.Ui;
import main.duke.tasktype.Task;

public class UiTest {
    @Test
    public void showDoneTest() {
        String result = new Ui().makeDone("hello");
        assertEquals("Following task has been marked done: \nhello", result);
    }

    @Test
    public void byeTest(){
        String result = new Ui().sayGoodbye();
        assertEquals("Goodbye!", result);
    }

    @Test
    public void addTaskTest(){
        Task task = new Task("task");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        String result = new Ui().showTaskAdded("task", taskList);
        assertEquals("Added the following task : \ntask\n" +
                "You now have 1 tasks in your list.\n", result);
    }


}
