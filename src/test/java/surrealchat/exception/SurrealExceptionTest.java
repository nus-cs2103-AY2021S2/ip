package surrealchat.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import surrealchat.task.TaskManagement;

public class SurrealExceptionTest {
    private String printEmptyList() {
        try {
            return new TaskManagement(new ArrayList<>()).listOutTasks();
        } catch (SurrealException e) {
            return e.getMessage();
        }
    }

    /**
     * Tests whether exception prints correct output upon trying to list out tasks from empty list.
     */
    @Test
    public void testNothingToPrint() {
        assertEquals(printEmptyList(), "I have nothing to print. Not stonks!\n");
    }
}
