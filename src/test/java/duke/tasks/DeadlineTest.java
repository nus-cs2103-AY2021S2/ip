package duke.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testIsDone() {
        Deadline test = new Deadline("test", "test");
        Assertions.assertFalse(test.isDone);
    }

    @Test
    public void testMarkAsDone() {
        Deadline test = new Deadline("test", "test");
        test.markAsDone();
        Assertions.assertTrue(test.isDone);
    }

    @Test
    public void testGetStatusIcon() {
        Deadline test = new Deadline("test", "test");
        Assertions.assertEquals("[ ]", test.getStatusIcon());
        test.markAsDone();
        Assertions.assertEquals("[X]", test.getStatusIcon());
    }

    @Test
    public void testGetType() {
        Deadline test = new Deadline("test", "test");
        Assertions.assertEquals("[D]", test.getType());
    }

    @Test
    public void testGetDescription() {
        Deadline test = new Deadline("test", "test");
        Assertions.assertEquals("test", test.getDescription());
    }

    @Test
    public void testGetStatus() {
        Deadline test = new Deadline("test", "test");
        Assertions.assertFalse(test.getStatus());
    }

    @Test
    public void testGetDeadline() {
        Deadline test = new Deadline("test", "test");
        Assertions.assertEquals("test", test.getDeadline());
    }
}
