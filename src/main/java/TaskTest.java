import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TaskTest class is a test class for Task
 *
 * @version 28 Jan 2021
 * @author Zhang Peng
 */
class TaskTest {

    Task task = new Task("deadline return book /by Sunday");
    int checker = 0;

    /**
     * This is the the test method for markAsDone method.
     * @return Nothing.
     */
    @Test
    void markAsDone() {

        task.markAsDone();
        if (task.isDone = true) {
            checker =1;
        }
        assertEquals(1, checker);
    }
}