package com.lirc572.ip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests TaskList class.
 */
public class TaskListTest {

    /**
     * Tests TaskList.toSavedString() method.
     */
    @Test
    public void testToSavedString() {
        TaskList tasks = new TaskList();
        tasks.add(new TodoTask("hello"));
        assertEquals("E | 0 | hello\n", tasks.toSavedString());
        tasks.add(new TodoTask("hi"));
        assertEquals("E | 0 | hello\nE | 0 | hi\n", tasks.toSavedString());
    }

}
