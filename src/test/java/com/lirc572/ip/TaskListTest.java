package com.lirc572.ip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testToSavedString() {
        TaskList tasks = new TaskList();
        tasks.add(new TodoTask("hello"));
        assertEquals("E | 0 | hello", tasks.toSavedString());
        tasks.add(new TodoTask("hi"));
        assertEquals("E | 0 | hello\nE | 0 | hi", tasks.toSavedString());
    }

}
