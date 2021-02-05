import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testSetDone() {
        Task testTask = new Task("");
        testTask.setDone(true);
        assertEquals(true, testTask.isDone);
        testTask.setDone(false);
        assertEquals(false, testTask.isDone);
    }

    @Test
    public void testGetStatus() {
        Task testTask = new Task("");
        testTask.isDone = true;
        assertEquals("[X] ", testTask.getStatus());
        testTask.isDone = false;
        assertEquals("[ ] ", testTask.getStatus());
    }

    @Test
    public void testSaveStatus() {
        Task testTask = new Task("");
        testTask.isDone = true;
        assertEquals(" | 1 | \n", testTask.saveStatus());
        testTask.isDone = false;
        assertEquals(" | 0 | \n", testTask.saveStatus());
    }

    @Test
    public void testDoesDescriptionContain() {
        Task testTask = new Task("");
        assertEquals(true, testTask.doesDescriptionContain(""));
        assertEquals(false, testTask.doesDescriptionContain("123"));
    }
}
