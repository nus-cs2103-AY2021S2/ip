package duke.tasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testIsDoneFunction() {
        Todo test = new Todo("test");
        Assertions.assertFalse(test.isDone);
    }

    @Test
    public void testMarkAsDoneFunction() {
        Todo test = new Todo(("test"));
        test.markAsDone();
        Assertions.assertTrue(test.isDone);
    }

    @Test
    public void testGetStatusIconFunction() {
        Todo test = new Todo("test");
        Assertions.assertEquals("[ ]", test.getStatusIcon());
        test.markAsDone();
        Assertions.assertEquals("[X]", test.getStatusIcon());
    }

    @Test
    public void testGetType() {
        Todo test = new Todo("test");
        Assertions.assertEquals("[T]", test.getType());
    }

    @Test
    public void testGetDescription() {
        Todo test = new Todo("test");
        Assertions.assertEquals("test", test.getDescription());
    }

    @Test
    public void testGetStatus() {
        Todo test = new Todo("test");
        Assertions.assertFalse(test.getStatus());
    }
}
