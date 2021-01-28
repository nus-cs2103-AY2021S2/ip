package surrealchat.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    public static final String TEST_DESCRIPTION = "Do CS2103T quiz";
    public static final ToDoTask STARTING_TASK = ToDoTask.createNewToDoTask(TEST_DESCRIPTION);

    @Test
    public void testPrintStringConversion(){
        assertEquals(ToDoTaskTest.STARTING_TASK.toString(),
                "[T][\u2718] Do CS2103T quiz");
    }

    @Test
    public void testFileStringConversion(){
        assertEquals(ToDoTaskTest.STARTING_TASK.saveTask(),
                "T/split/0/split/Do CS2103T quiz");
    }

    @Test
    public void testToDoTaskType(){
        assertEquals(ToDoTaskTest.STARTING_TASK.getType(),
                "T");
    }

    @Test
    public void testGetDescription() {
        assertEquals(ToDoTaskTest.STARTING_TASK.getDescription(),
                "Do CS2103T quiz");
    }

    @Test
    public void testEditDescription() {
        assertEquals(ToDoTaskTest.STARTING_TASK.editDescription("Eat biscuits").getDescription(),
                "Eat biscuits");
    }

    @Test
    public void testMarkDone() {
        assertEquals(ToDoTaskTest.STARTING_TASK.markAsDone().toString(),
                "[T][\u2713] Do CS2103T quiz");
    }

    @Test
    public void testMarkUndone() {
        assertEquals(ToDoTaskTest.STARTING_TASK.markAsDone().markAsDone().markAsDone().toString(),
                "[T][\u2718] Do CS2103T quiz");
    }
}
