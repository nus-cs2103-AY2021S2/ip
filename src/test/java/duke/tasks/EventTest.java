package duke.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testIsDoneFunction() {
        Event test = new Event("test", "test");
        Assertions.assertFalse(test.isDone);
    }

    @Test
    public void testMarkAsDoneFunction() {
        Event test = new Event("test", "test");
        test.markAsDone();
        Assertions.assertTrue(test.isDone);
    }

    @Test
    public void testGetStatusIconFunction() {
        Event test = new Event("test", "test");
        Assertions.assertEquals("[ ]", test.getStatusIcon());
        test.markAsDone();
        Assertions.assertEquals("[X]", test.getStatusIcon());
    }

    @Test
    public void testGetType() {
        Event test = new Event("test", "test");
        Assertions.assertEquals("[E]", test.getType());
    }

    @Test
    public void testGetDescription() {
        Event test = new Event("test", "test");
        Assertions.assertEquals("test", test.getDescription());
    }

    @Test
    public void testGetStatus() {
        Event test = new Event("test", "test");
        Assertions.assertFalse(test.getStatus());
    }

    @Test
    public void testGetEventTime() {
        Event test = new Event("test", "test");
        Assertions.assertEquals("test", test.getEventTime());
    }
}
