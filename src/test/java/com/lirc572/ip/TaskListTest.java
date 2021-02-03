package com.lirc572.ip;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
        assertEquals("T | 0 | hello\n", tasks.toSavedString());
        tasks.add(new TodoTask("hi"));
        assertEquals("T | 0 | hello\nT | 0 | hi\n", tasks.toSavedString());
    }

}
