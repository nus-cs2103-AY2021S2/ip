package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    void testFileStringConversion() {
        Deadline testDeadline1 = new Deadline("deadlineTest1", "testDate");
        Deadline testDeadline2 = new Deadline("deadlineTest2", "testDate");

        assertEquals("D|0|1|deadlineTest1|testDate", testDeadline1.toFileString());
        assertEquals("D|0|1|deadlineTest2|testDate", testDeadline2.toFileString());

        testDeadline1.setDone();
        assertEquals("D|1|1|deadlineTest1|testDate", testDeadline1.toFileString());

        testDeadline2.setPriority(Priority.valueOf(0));
        assertEquals("D|0|0|deadlineTest2|testDate", testDeadline2.toFileString());
    }

    @Test
    void testStringConversion() {
        Deadline testDeadline1 = new Deadline("deadlineTest1", "testDate");
        Deadline testDeadline2 = new Deadline("deadlineTest2", "testDate");

        assertEquals("[D][ ][!!]deadlineTest1 (by:testDate)", testDeadline1.toString());
        assertEquals("[D][ ][!!]deadlineTest2 (by:testDate)", testDeadline2.toString());

        testDeadline1.setDone();
        assertEquals("[D][X][!!]deadlineTest1 (by:testDate)", testDeadline1.toString());

        testDeadline2.setPriority(Priority.valueOf(0));
        assertEquals("[D][ ][!]deadlineTest2 (by:testDate)", testDeadline2.toString());
    }
}
