package yoda.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventTest {
    @Test
    void testToString() {
        Event event = new Event("attend this", "2021-09-05 1500");
        assertEquals("[E][ ] attend this ---> at Sep 5 2021 1500", event.toString());
    }
}
