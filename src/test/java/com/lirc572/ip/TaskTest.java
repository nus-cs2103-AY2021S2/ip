package com.lirc572.ip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testGetName() {
        assertEquals("lirc572", new Task("lirc572").getName());
        assertEquals("275cril", new Task("275cril").getName());
    }

    @Test
    public void testGetIsDone_setIsDone() {
        Task task = new Task("lirc572");
        assertFalse(task.getIsDone());
        task.setIsDone(true);
        assertTrue(task.getIsDone());
        task.setIsDone(false);
        assertFalse(task.getIsDone());
    }

    @Test
    public void testToString() {
        Task task = new Task("lirc572");
        assertEquals("[ ] lirc572", task.toString());
        task.setIsDone(true);
        assertEquals("[X] lirc572", task.toString());
    }

    @Test
    public void testToSavedString() {
        Task task = new Task("lirc572");
        assertEquals("0 | lirc572", task.toSavedString());
        task.setIsDone(true);
        assertEquals("1 | lirc572", task.toSavedString());
    }
}
