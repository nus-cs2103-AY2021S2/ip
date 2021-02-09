package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    public static final String TEST_DESC_1 = "return books";
    public static final String TEST_DATE_1 = "2019-11-01T18:00";
    public static final String TEST_DESC_2 = "pay phone bill";
    public static final String TEST_DATE_2 = "2021-01-26T19:00";

    @Test
    public void toStorageStringTest() {
        Deadline newDeadline1 = new Deadline(TEST_DESC_1, TEST_DATE_1);
        assertEquals("D | 0 | return books | 2019-11-01T18:00", newDeadline1.toStorageString());

        Deadline newDeadline2 = new Deadline(1, TEST_DESC_2, TEST_DATE_2);
        assertEquals("D | 1 | pay phone bill | 2021-01-26T19:00", newDeadline2.toStorageString());
    }

    @Test
    public void toStringTest() {
        Deadline newDeadline1 = new Deadline(TEST_DESC_1, TEST_DATE_1);
        assertEquals("[D][ ] return books (by: Nov 01 2019 06:00PM)", newDeadline1.toString());

        Deadline newDeadline2 = new Deadline(1, TEST_DESC_2, TEST_DATE_2);
        assertEquals("[D][X] pay phone bill (by: Jan 26 2021 07:00PM)", newDeadline2.toString());
    }
}
