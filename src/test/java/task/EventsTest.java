package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventsTest {

    Events event1 = new Events("event1", null);
    Events event2 = new Events("event2", "NUS");

    @Test
    void getTypeIcon() {
        String actualOutput = event1.getTypeIcon();
        String expectedOutput = "[E]";

        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    void testGetDescription_noVenue() {
        String actualOutput = event1.getDescription();
        String expectedOutput = "event1";

        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    void testGetDescription() {
        String actualOutput = event2.getDescription();
        String expectedOutput = "event2 (at: NUS)";

        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    void testTokenize() {
        String actualOutput = event2.tokenize();
        String expectedOutput = "E|0|event2|NUS";

        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    void testTokenize_noVenue() {
        String actualOutput = event1.tokenize();
        String expectedOutput = "E|0|event1";

        assertEquals(actualOutput, expectedOutput);
    }

}