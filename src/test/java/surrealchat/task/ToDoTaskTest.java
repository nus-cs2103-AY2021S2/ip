package surrealchat.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {
    private static final String TEST_DESCRIPTION = "Do CS2103T quiz";
    private static final TaskPriority TASK_PRIORITY = TaskPriority.LOW;
    private static final ToDoTask STARTING_TASK = ToDoTask.createNewToDoTask(TEST_DESCRIPTION, TASK_PRIORITY);

    /**
     * Tests the functionality of toString() method in ToDoTask class.
     */
    @Test
    public void testPrintStringConversion() {
        assertEquals(ToDoTaskTest.STARTING_TASK.toString(),
                "[T][\u2718] Do CS2103T quiz | Priority: 1");
    }

    /**
     * Tests the functionality of saveTask() method in ToDoTask class.
     */
    @Test
    public void testFileStringConversion() {
        assertEquals(ToDoTaskTest.STARTING_TASK.saveTask(),
                "T/split/0/split/1/split/Do CS2103T quiz");
    }

    /**
     * Tests whether getType() returns "D" for ToDoTask.
     */
    @Test
    public void testToDoTaskType() {
        assertEquals(ToDoTaskTest.STARTING_TASK.getType(),
                "T");
    }

    /**
     * Tests functionality of getDescription().
     */
    @Test
    public void testGetDescription() {
        assertEquals(ToDoTaskTest.STARTING_TASK.getDescription(),
                "Do CS2103T quiz");
    }

    /**
     * Tests functionality of editDescription().
     */
    @Test
    public void testEditDescription() {
        assertEquals(ToDoTaskTest.STARTING_TASK.editDescription("Eat biscuits").getDescription(),
                "Eat biscuits");
    }

    /**
     * Tests functionality of editPriority().
     */
    @Test
    public void testEditPriority() {
        assertEquals(ToDoTaskTest.STARTING_TASK.editPriority(TaskPriority.MEDIUM).toString(),
                "[T][\u2718] Do CS2103T quiz | Priority: 2");
    }

    /**
     * Tests functionality of editTask().
     */
    @Test
    public void testEditTask() {
        assertEquals(ToDoTaskTest.STARTING_TASK.editTask("Eat biscuits", TaskPriority.HIGH).toString(),
                "[T][\u2718] Eat biscuits | Priority: 3");
    }

    /**
     * Tests functionality of whether task can be marked as done.
     */
    @Test
    public void testMarkDone() {
        assertEquals(ToDoTaskTest.STARTING_TASK.markAsDone().toString(),
                "[T][\u2713] Do CS2103T quiz | Priority: 1");
    }

    /**
     * Tests functionality of whether task can be marked as undone after being marked as done.
     */
    @Test
    public void testMarkUndone() {
        assertEquals(ToDoTaskTest.STARTING_TASK.markAsDone().markAsDone().toString(),
                "[T][\u2718] Do CS2103T quiz | Priority: 1");
    }
}
