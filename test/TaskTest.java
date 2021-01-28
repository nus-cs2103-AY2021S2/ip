import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getStatusIcon_taskIsDone_iconIsTick() {
        Task task = new Task(" sdf ", true);
        assertEquals("\u2713", task.getStatusIcon());
    }

    @Test
    void doneTask_taskNotDone_isDoneTrue() {
        Task task = new Task("fs");
        task.setDone();
        assertEquals(true, task.isDone);
    }
}