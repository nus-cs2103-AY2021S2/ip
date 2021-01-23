package surrealchat.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {
    @Test
    public void testPrintStringConversion(){
        assertEquals(new EventTask("Do CS2103T quiz", LocalDate.parse("2020-10-21"), false).toString(),
                "[E][\u2718] Do CS2103T quiz (at: 2020-10-21)");
    }

    @Test
    public void testFileStringConversion(){
        assertEquals(new EventTask("Do CS2103T quiz", LocalDate.parse("2020-10-21"), false).saveTask(),
                "E/split/0/split/Do CS2103T quiz /at 2020-10-21");
    }
}
