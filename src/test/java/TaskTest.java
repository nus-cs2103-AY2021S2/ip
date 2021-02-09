import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.Task;


public class TaskTest {

    @Test
    public void testSetDone() {
        TaskStub testTask = new TaskStub("");
        testTask.setDone(true);
        assertTrue(testTask.getDone());
        testTask.setDone(false);
        assertFalse(testTask.getDone());
    }

    @Test
    public void testGetStatus() {
        TaskStub testTask = new TaskStub("");
        testTask.setDone(true);
        assertEquals("[X] ", testTask.getStatus());
        testTask.setDone(false);
        assertEquals("[ ] ", testTask.getStatus());
    }

    @Test
    public void testSaveStatus() {
        TaskStub testTask = new TaskStub("");
        testTask.setDone(true);
        assertEquals(" | 1 | \n", testTask.saveStatus());
        testTask.setDone(false);
        assertEquals(" | 0 | \n", testTask.saveStatus());
    }

    @Test
    public void testDoesDescriptionContain() {
        Task testTask = new Task("");
        assertTrue(testTask.doesDescriptionContain(""));
        assertFalse(testTask.doesDescriptionContain("123"));
    }
}
