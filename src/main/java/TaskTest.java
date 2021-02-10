import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;




/**
 * TaskTest class is a test class for Task
 * @author Zhang Peng
 * @version 28 Jan 2021
 */
class TaskTest {

    private final Task task = new Task("deadline return book /by Sunday");
    private int checker = 0;

    /**
     * This is the the test method for markAsDone method.
     * @return Nothing.
     */
    @Test
    void markAsDone() {

        task.markAsDone();
        if (task.isDone = true) {
            checker = 1;
        }
        assertEquals(1, checker);
    }
}
