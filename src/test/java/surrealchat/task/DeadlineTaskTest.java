package surrealchat.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    public static final String TEST_DESCRIPTION = "Do CS2103T quiz";
    public static final LocalDate TEST_DEADLINE = LocalDate.parse("2020-10-21");
    public static final DeadlineTask STARTING_TASK = DeadlineTask.createNewDeadlineTask(
            DeadlineTaskTest.TEST_DESCRIPTION, DeadlineTaskTest.TEST_DEADLINE);

    @Test
    public void testPrintStringConversion(){
        assertEquals(DeadlineTaskTest.STARTING_TASK.toString(),
                "[D][\u2718] Do CS2103T quiz (by: 2020-10-21)");
    }

    @Test
    public void testFileStringConversion(){
        assertEquals(DeadlineTaskTest.STARTING_TASK.saveTask(),
                "D/split/0/split/Do CS2103T quiz /by 2020-10-21");
    }

    @Test
    public void testDeadlineTaskType(){
        assertEquals(DeadlineTaskTest.STARTING_TASK.getType(),
                "D");
    }

    @Test
    public void testEditDescription() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.editDescription("Eat biscuits").getDescription(),
                "Eat biscuits");
    }

    @Test
    public void testMarkDone() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.markAsDone().toString(),
                "[D][\u2713] Do CS2103T quiz (by: 2020-10-21)");
    }

    @Test
    public void testMarkUndone() {
        assertEquals(DeadlineTaskTest.STARTING_TASK.markAsDone().markAsDone().toString(),
                "[D][\u2718] Do CS2103T quiz (by: 2020-10-21)");
    }
}
