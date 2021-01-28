import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task = new Task("deadline return book /by Sunday");
    int checker = 0;

    @Test
    void markAsDone() {

        task.markAsDone();
        if (task.isDone = true) {
            checker =1;
        }
        assertEquals(1, checker);
    }
}