import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.Task;


public class TaskTest {

    //    @Test
    //    public void testSetDone() {
    //        Task testTask = new Task("");
    //        testTask.setDone(true);
    //        assertTrue(testTask.isDone);
    //        testTask.setDone(false);
    //        assertFalse(testTask.isDone);
    //    }
    //
    //    @Test
    //    public void testGetStatus() {
    //        Task testTask = new Task("");
    //        testTask.isDone = true;
    //        assertEquals("[X] ", testTask.getStatus());
    //        testTask.isDone = false;
    //        assertEquals("[ ] ", testTask.getStatus());
    //    }
    //
    //    @Test
    //    public void testSaveStatus() {
    //        Task testTask = new Task("");
    //        testTask.isDone = true;
    //        assertEquals(" | 1 | \n", testTask.saveStatus());
    //        testTask.isDone = false;
    //        assertEquals(" | 0 | \n", testTask.saveStatus());
    //    }

    @Test
    public void testDoesDescriptionContain() {
        Task testTask = new Task("");
        assertTrue(testTask.doesDescriptionContain(""));
        assertFalse(testTask.doesDescriptionContain("123"));
    }
}
