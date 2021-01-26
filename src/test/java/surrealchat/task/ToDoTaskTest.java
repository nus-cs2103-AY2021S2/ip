package surrealchat.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void testPrintStringConversion(){
        assertEquals(new ToDoTask("Do CS2103T quiz", false).toString(),
                "[T][\u2718] Do CS2103T quiz");
    }

    @Test
    public void testFileStringConversion(){
        assertEquals(new ToDoTask("Do CS2103T quiz", false).saveTask(),
                "T/split/0/split/Do CS2103T quiz");
    }

    @Test
    public void testToDoTaskType(){
        assertEquals(new ToDoTask("Do CS2103T quiz", false).getType(),
                "T");
    }

    @Test
    public void testDescription() {
        assertEquals(new ToDoTask("Do CS2103T quiz", false).getDescription(),
                "Do CS2103T quiz");
    }
}
