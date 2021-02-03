package com.lirc572.ip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests Task class.
 */
public class TaskTest {

    /**
     * Tests Task.getName() method.
     */
    @Test
    public void testGetName() {
        assertEquals("lirc572", new Task("lirc572").getName());
        assertEquals("275cril", new Task("275cril").getName());
    }

    /**
     * Tests Task.getIsDone() and Task.setIsDone() methods.
     */
    @Test
    public void testGetIsDone_setIsDone() {
        Task task = new Task("lirc572");
        assertFalse(task.getIsDone());
        task.setIsDone(true);
        assertTrue(task.getIsDone());
        task.setIsDone(false);
        assertFalse(task.getIsDone());
    }

    /**
     * Tests Task.toString() method.
     */
    @Test
    public void testToString() {
        Task task = new Task("lirc572");
        assertEquals("[ ] lirc572", task.toString());
        task.setIsDone(true);
        assertEquals("[X] lirc572", task.toString());
    }

    /**
     * Tests Task.toSavedString() method.
     */
    @Test
    public void testToSavedString() {
        Task task = new Task("lirc572");
        assertEquals("0 | lirc572", task.getSavedString());
        task.setIsDone(true);
        assertEquals("1 | lirc572", task.getSavedString());
    }
}
