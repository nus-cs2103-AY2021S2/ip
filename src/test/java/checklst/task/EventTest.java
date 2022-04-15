package checklst.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import checklst.exception.ChecklstException;

public class EventTest {

    @Test
    public void createEventTest() {
        assertThrows(ChecklstException.class, () -> Event.makeEvent(""));
        assertThrows(ChecklstException.class, () -> Event.makeEvent("test"));
        assertThrows(ChecklstException.class, () -> Event.makeEvent("test /at"));
        assertThrows(ChecklstException.class, () -> Event.makeEvent("test /at "));
        assertThrows(ChecklstException.class, () -> Event.makeEvent("test /at 23rd Feb"));
        assertThrows(ChecklstException.class, () -> Event.makeEvent("test/at2020-11-20"));
    }

}
