import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void testIsDone() {
        assertFalse(new Task("testSubject1").isDone());
        assertTrue(new Task("testSubject2", true).isDone());
    }

    @Test
    public void testMarkDone() {
        Task sub3 = new Task("testSubject3");
        sub3.markDone();
        assertTrue(sub3.isDone());
    }

    @Test
    public void testGetName() {
        assertEquals("testSubject4", new Task("testSubject4").getName());
    }

    @Test
    public void testGetStatus() {
        assertEquals("[\u2718] testSubject5", new Task("testSubject5").getStatus());
        Task sub6 = new Task("testSubject6");
        sub6.markDone();
        assertEquals("[\u2713] testSubject6", sub6.getStatus());
    }
}
