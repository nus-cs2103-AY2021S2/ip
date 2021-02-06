package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void markAsDone_validInput_success() {
        Task task = new DeadlineTask("homework", "2021-01-01 19:00");
        task.markAsDone();
        assertEquals(task.isDone, true);
    }

    @Test
    void getStatusIcon_validInput_success() {
        Task task = new DeadlineTask("homework", "2021-01-01 19:00");
        assertEquals(task.getStatusIcon(), " ");
    }

}