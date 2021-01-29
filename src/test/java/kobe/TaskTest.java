package kobe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskTest {
    private final Task todoTask = new Task("Do your homework", "todo", "");
    private final Task deadlineTask = new Task("This SE Project", "todo", "2359");
    private final Task eventTask = new Task("It's your friend's birthday", "todo", "2021/08/15");

    @Test
    void markAsDone_todoTask() {
        assertEquals(true, Task.setAsDone(todoTask).getDoneStatus());
    }

    @Test
    void markAsDone_deadlineTask() {
        assertEquals(true, Task.setAsDone(deadlineTask).getDoneStatus());
    }

    @Test
    void markAsDone_eventTask() {
        assertEquals(true, Task.setAsDone(eventTask).getDoneStatus());
    }

    @Test
    void testToString() {
        assertEquals(2, 2);
    }
}