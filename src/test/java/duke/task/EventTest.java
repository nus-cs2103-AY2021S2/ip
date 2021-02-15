package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void testFileStringConversion() {
        Event testEvent1 = new Event("eventTest1", "testDate");
        Event testEvent2 = new Event("eventTest2", "testDate");

        assertEquals("E|0|1|eventTest1|testDate", testEvent1.toFileString());
        assertEquals("E|0|1|eventTest2|testDate", testEvent2.toFileString());

        testEvent1.setDone();
        assertEquals("E|1|1|eventTest1|testDate", testEvent1.toFileString());

        testEvent2.setPriority(Priority.valueOf(0));
        assertEquals("E|0|0|eventTest2|testDate", testEvent2.toFileString());
    }

    @Test
    void testStringConversion() {
        Event testEvent1 = new Event("eventTest1", "testDate");
        Event testEvent2 = new Event("eventTest2", "testDate");

        assertEquals("[E][ ][!!]eventTest1 (at:testDate)", testEvent1.toString());
        assertEquals("[E][ ][!!]eventTest2 (at:testDate)", testEvent2.toString());

        testEvent1.setDone();
        assertEquals("[E][X][!!]eventTest1 (at:testDate)", testEvent1.toString());

        testEvent2.setPriority(Priority.valueOf(0));
        assertEquals("[E][ ][!]eventTest2 (at:testDate)", testEvent2.toString());
    }
}
