import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ip.src.main.java.Task;


public class TaskTest {
    @Test

    /*
      Tests if the markDone() method in the Task class behaves as expected.
     */
    public void taskDoneTest() {
        Task task = new Task("Test");
        task.markDone();
        assertEquals(true , task.done);

    }
}
