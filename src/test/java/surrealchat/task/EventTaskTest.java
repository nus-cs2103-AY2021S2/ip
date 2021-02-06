package surrealchat.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    private static final String TEST_DESCRIPTION = "Do CS2103T quiz";
    private static final LocalDateTime TEST_EVENT = LocalDateTime.parse("2020-10-21T10:10:10");
    private static final TaskPriority TASK_PRIORITY = TaskPriority.MEDIUM;
    private static final EventTask STARTING_TASK = EventTask.createNewEventTask(
            EventTaskTest.TEST_DESCRIPTION, TEST_EVENT, TASK_PRIORITY);

    /**
     * Tests the functionality of toString() method in EventTask class.
     */
    @Test
    public void testPrintStringConversion() {
        assertEquals(EventTaskTest.STARTING_TASK.toString(),
                "[E][\u2718] Do CS2103T quiz | Priority: 2 (at: 2020-10-21, 10:10:10)");
    }

    /**
     * Tests the functionality of saveTask() method in EventTask class.
     */
    @Test
    public void testFileStringConversion() {
        assertEquals(EventTaskTest.STARTING_TASK.saveTask(),
                "E/split/0/split/2/split/Do CS2103T quiz /at 2020-10-21T10:10:10");
    }

    /**
     * Tests whether getType() returns "E" for EventTask.
     */
    @Test
    public void testEventTaskType() {
        assertEquals(EventTaskTest.STARTING_TASK.getType(),
                "E");
    }

    /**
     * Tests functionality of getDescription().
     */
    @Test
    public void testGetDescription() {
        assertEquals(EventTaskTest.STARTING_TASK.getDescription(),
                "Do CS2103T quiz");
    }

    /**
     * Tests functionality of editDescription().
     */
    @Test
    public void testEditDescription() {
        assertEquals(EventTaskTest.STARTING_TASK.editDescription("Eat biscuits").getDescription(),
                "Eat biscuits");
    }

    /**
     * Tests functionality of whether task can be marked as done.
     */
    @Test
    public void testMarkDone() {
        assertEquals(EventTaskTest.STARTING_TASK.markAsDone().toString(),
                "[E][\u2713] Do CS2103T quiz | Priority: 2 (at: 2020-10-21, 10:10:10)");
    }

    /**
     * Tests functionality of whether task can be marked as undone after being marked as done.
     */
    @Test
    public void testMarkUndone() {
        assertEquals(EventTaskTest.STARTING_TASK.markAsDone().markAsDone().toString(),
                "[E][\u2718] Do CS2103T quiz | Priority: 2 (at: 2020-10-21, 10:10:10)");
    }
}
