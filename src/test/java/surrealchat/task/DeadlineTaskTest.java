package surrealchat.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    private static final String TEST_DESCRIPTION = "Do CS2103T quiz";
    private static final LocalDateTime TEST_DEADLINE = LocalDateTime.parse("2020-10-21T10:10:10");
    private static final LocalDateTime TEST_EDIT_DEADLINE = LocalDateTime.parse("2020-11-11T20:20:20");
    private static final TaskPriority TASK_PRIORITY = TaskPriority.HIGH;
    private static final DeadlineTask STARTING_TASK = DeadlineTask.createNewDeadlineTask(
            TEST_DESCRIPTION, TASK_PRIORITY, TEST_DEADLINE);

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
     * Tests functionality of editPriority().
     */
    @Test
    public void testEditPriority() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.editPriority(TaskPriority.MEDIUM).toString(),
                "[D][\u2718] Do CS2103T quiz | Priority: 2 (by: 2020-10-21, 10:10:10)");
    }

    /**
     * Tests functionality of editDeadline().
     */
    @Test
    public void testEditDeadline() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.editDeadline(DeadlineTaskTest.TEST_EDIT_DEADLINE).toString(),
                "[D][\u2718] Do CS2103T quiz | Priority: 3 (by: 2020-11-11, 20:20:20)");
    }

    /**
     * Tests functionality of editTask().
     */
    @Test
    public void testEditTask() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.editTask(
                "Eat biscuits", TaskPriority.LOW, DeadlineTaskTest.TEST_EDIT_DEADLINE).toString(),
                "[D][\u2718] Eat biscuits | Priority: 1 (by: 2020-11-11, 20:20:20)");
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
