import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testInitialization() {
        TaskList tasks = new TaskList();
        ArrayList<Task> expected = new ArrayList<>();
        Assertions.assertEquals(expected, tasks.getTasks());
    }

    @Test
    public void testAddAndDelete() {
        TaskList tasks = new TaskList();
        ArrayList<Task> expected = new ArrayList<>();

        // test case 1: test add
        tasks.addTask(new ToDo("description"));
        expected.add(new ToDo("description"));
        tasks.addTask(new ToDo("description 2"));
        expected.add(new ToDo("description 2"));
        Assertions.assertEquals(expected.toString(), tasks.getTasks().toString());

        // test case 2: test delete
        tasks.deleteTask(1);
        expected.remove(0);
        Assertions.assertEquals(expected.toString(), tasks.getTasks().toString());
    }

    @Test
    public void testDone() {
        TaskList tasks = new TaskList();

        tasks.addTask(new ToDo("description"));
        tasks.doneTask(1);

        Assertions.assertEquals("[[T][X] description]", tasks.getTasks().toString());
    }
}
