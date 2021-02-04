package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    public void saveStringTest() {
        Event event = new Event("birthday", "today");
        assertEquals(event.saveString(), "E --- 0 --- birthday --- today");
    }

}