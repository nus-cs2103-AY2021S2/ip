import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {

    @Test
    public void testSetDone() {
        Deadline testDeadline = new Deadline("", LocalDate.now());
        testDeadline.setDone(true);
        assertEquals(true, testDeadline.getDone());
        testDeadline.setDone(false);
        assertEquals(false, testDeadline.getDone());
    }

    @Test
    public void testGetStatus() {
        LocalDate nowDate = LocalDate.now();
        Deadline testDeadline = new Deadline("", nowDate);
        testDeadline.setDone(true);

        String testDate = "[D][X] (by: " + nowDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(testDate, testDeadline.getStatus());
        testDeadline.setDone(false);
        testDate = "[D][ ] (by: " + nowDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(testDate, testDeadline.getStatus());
    }

    @Test
    public void testSaveStatus() {
        Deadline testDeadline = new Deadline("", LocalDate.now());
        testDeadline.setDone(true);
        assertEquals("D | 1 | \n", testDeadline.currentStatus());
        testDeadline.setDone(false);
        assertEquals("D | 0 | \n", testDeadline.currentStatus());
    }

    @Test
    public void testDoesDescriptionContain() {
        Deadline testDeadline = new Deadline("", LocalDate.now());
        assertEquals(true, testDeadline.doesDescriptionContain(""));
        assertEquals(false, testDeadline.doesDescriptionContain("123"));
    }

    @Test
    public void testChangeEventTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDate nowDate = LocalDate.now();
        Deadline testDeadline = new Deadline("", nowDate);
        String testDate = "[D][ ] (by: " + nowDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(testDate , testDeadline.getStatus());

        LocalDate newDate = LocalDate.parse("04/08/1997 1200", formatter);
        testDeadline.changeEventTime(newDate);
        testDate = "[D][ ] (by: " + newDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(testDate , testDeadline.getStatus());
    }
}
