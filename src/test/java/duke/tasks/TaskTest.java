package duke.tasks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

/**
 * Unit test for Task class.
 */
public class TaskTest {
    @Test
    public void testStringConversion() {
        Task task = new Task("test");
        assertEquals("[ ] test", task.toString());
    }

    @Test
    public void testInfoToStoreConversion() {
        Task task = new Task("test");
        assertEquals("0 | test", task.getTaskInfoToStore());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Task("test");
        assertEquals("[ ] test", task.toString());
        task.markAsDone();
        assertEquals("[X] test", task.toString());
    }

    /**
     * This method is adapted from AddCommandTest.java of addressbook-level2. The link is:
     * github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/commands/AddCommandTest.java
     */
    @Test
    public void testUpdateDate_nonDeadlineObject_throwsException() {
        Task task = new Task("test");
        LocalDate testDate = LocalDate.parse("2021-02-17");
        assertUpdatingDateForNonDeadlineObjectThrowsException(task, testDate);

    }

    /**
     * Asserts that updating the date of an non-deadline object, here the task object,
     * throws a DukeException.
     * This method is adapted from AddCommandTest.java of addressbook-level2. The link is:
     * github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/commands/AddCommandTest.java
     *
     * @param task task to be tested.
     * @param testDate testDate for the update.
     */
    private void assertUpdatingDateForNonDeadlineObjectThrowsException(Task task, LocalDate testDate) {
        try {
            task.updateDate(testDate);
        } catch (DukeException e) {
            return;
        }

        String error = "A non-deadline object (task object) successfully updates its date to 2020-02-17.";
        fail(error);
    }
}
