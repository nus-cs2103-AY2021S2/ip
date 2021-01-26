package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    public static final String testDesc1 = "return books";
    public static final String testDate1 = "2019-11-01";
    public static final String testDesc2 = "pay phone bill";
    public static final String testDate2 = "2021-01-26";

    @Test
    public void toStorageStringTest() {
        Deadline newDeadline1 = new Deadline(testDesc1, testDate1);
        assertEquals("D | 0 | return books | 2019-11-01", newDeadline1.toStorageString());

        Deadline newDeadline2 = new Deadline(1, testDesc2, testDate2);
        assertEquals("D | 1 | pay phone bill | 2021-01-26", newDeadline2.toStorageString());
    }

    @Test
    public void toStringTest() {
        Deadline newDeadline1 = new Deadline(testDesc1, testDate1);
        assertEquals("[D][ ] return books (by: Nov 01 2019)", newDeadline1.toString());

        Deadline newDeadline2 = new Deadline(1, testDesc2, testDate2);
        assertEquals("[D][✘] pay phone bill (by: Jan 26 2021)", newDeadline2.toString());
    }
}
