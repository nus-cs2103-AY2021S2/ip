package surrealchat.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void testPrintStringConversion(){
        assertEquals(new DeadlineTask("Do CS2103T quiz", LocalDate.parse("2020-10-21"), false).toString(),
                "[D][\u2718] Do CS2103T quiz (by: 2020-10-21)");
    }

    @Test
    public void testFileStringConversion(){
        assertEquals(new DeadlineTask("Do CS2103T quiz", LocalDate.parse("2020-10-21"), false).saveTask(),
                "D/split/0/split/Do CS2103T quiz /by 2020-10-21");
    }
}
