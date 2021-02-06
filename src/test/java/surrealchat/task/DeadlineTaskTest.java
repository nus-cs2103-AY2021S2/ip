package surrealchat.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    private static final String TEST_DESCRIPTION = "Do CS2103T quiz";
    private static final LocalDateTime TEST_DEADLINE = LocalDateTime.parse("2020-10-21T10:10:10");
    private static final TaskPriority TASK_PRIORITY = TaskPriority.HIGH;
    private static final DeadlineTask STARTING_TASK = DeadlineTask.createNewDeadlineTask(
            TEST_DESCRIPTION, TEST_DEADLINE, TASK_PRIORITY);

    /**
     * Tests the functionality of toString() method in DeadlineTask class.
     */
    @Test
    public void testPrintStringConversion() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.toString(),
                "[D][\u2718] Do CS2103T quiz | Priority: 3 (by: 2020-10-21, 10:10:10)");
    }

    /**
     * Tests the functionality of saveTask() method in DeadlineTask class.
     */
    @Test
    public void testFileStringConversion() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.saveTask(),
                "D/split/0/split/3/split/Do CS2103T quiz /by 2020-10-21T10:10:10");
    }

    /**
     * Tests whether getType() returns "D" for DeadlineTask.
     */
    @Test
    public void testDeadlineTaskType() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.getType(),
                "D");
    }

    /**
     * Tests functionality of getDescription().
     */
    @Test
    public void testGetDescription() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.getDescription(),
                "Do CS2103T quiz");
    }

    /**
     * Tests functionality of editDescription().
     */
    @Test
    public void testEditDescription() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.editDescription("Eat biscuits").getDescription(),
                "Eat biscuits");
    }

    /**
     * Tests functionality of whether task can be marked as done.
     */
    @Test
    public void testMarkDone() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.markAsDone().toString(),
                "[D][\u2713] Do CS2103T quiz | Priority: 3 (by: 2020-10-21, 10:10:10)");
    }

    /**
     * Tests functionality of whether task can be marked as undone after being marked as done.
     */
    @Test
    public void testMarkUndone() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.markAsDone().markAsDone().toString(),
                "[D][\u2718] Do CS2103T quiz | Priority: 3 (by: 2020-10-21, 10:10:10)");
    }
}
