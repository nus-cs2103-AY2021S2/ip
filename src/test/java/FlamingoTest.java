import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class FlamingoTest {

    @Test
    public void taskListTest() {
        TaskList t = new TaskList(new ArrayList<>());
        t.addTask(new Todo("read book"));
        t.addTask(new Deadline("return book", LocalDate.parse("2021-02-02")));
        t.deleteTask(1);
        t.markAsDone(1);
        assertEquals(1, t.numTasks);
    }

    @Test
    public void getTaskStatusTest() {
        Task t = new Task("read book");
        assertEquals("0", t.getStatusAsNumber());
        t.isDone = true;
        assertEquals("1", t.getStatusAsNumber());
    }

    @Test
    public void openFileTest() throws Exception {
        Storage s = new Storage();
        s.loadData();
        assertEquals(false, s.pathExists);
    }
}
