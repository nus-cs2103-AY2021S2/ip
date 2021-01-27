package surrealchat.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {
    public static final String TEST_DESCRIPTION = "Do CS2103T quiz";
    public static final LocalDate TEST_EVENT = LocalDate.parse("2020-10-21");
    public static final boolean TEST_DONE = false;
    public static final EventTask STARTING_TASK = new EventTask(
            EventTaskTest.TEST_DESCRIPTION, EventTaskTest.TEST_EVENT, EventTaskTest.TEST_DONE);

    @Test
    public void testPrintStringConversion(){
        assertEquals(EventTaskTest.STARTING_TASK.toString(),
                "[E][\u2718] Do CS2103T quiz (at: 2020-10-21)");
    }

    @Test
    public void testFileStringConversion(){
        assertEquals(EventTaskTest.STARTING_TASK.saveTask(),
                "E/split/0/split/Do CS2103T quiz /at 2020-10-21");
    }

    @Test
    public void testEventTaskType(){
        assertEquals(EventTaskTest.STARTING_TASK.getType(),
                "E");
    }

    @Test
    public void testEditDescription() {
        assertEquals(EventTaskTest.STARTING_TASK.editDescription("Eat biscuits").getDescription(),
                "Eat biscuits");
    }

    @Test
    public void testMarkDone() {
        assertEquals(EventTaskTest.STARTING_TASK.markAsDone().toString(),
                "[E][\u2713] Do CS2103T quiz (at: 2020-10-21)");
    }

    @Test
    public void testMarkUndone() {
        assertEquals(EventTaskTest.STARTING_TASK.markAsDone().markAsUndone().toString(),
                "[E][\u2718] Do CS2103T quiz (at: 2020-10-21)");
    }
}
