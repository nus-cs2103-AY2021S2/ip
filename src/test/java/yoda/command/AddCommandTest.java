package yoda.command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import yoda.task.Deadline;
import yoda.task.Event;

class AddCommandTest {

    @Test
    void testMakeTask() {
        String[] eventArgs = {"EVENT", "attend meeting /at 2021-09-09 0900"};
        AddCommand makeEvent = new AddCommand(eventArgs);
        Event event = new Event("attend meeting", "2021-09-09 0900");
        String[] deadlineArgs = {"DEADLINE", "finish quiz /by 2021-09-09 0900"};
        AddCommand makeDeadline = new AddCommand(deadlineArgs);
        Deadline deadline = new Deadline("finish quiz", "2021-09-09 0900");
        assertAll("make deadline and event", () -> assertEquals(makeEvent.makeTask().toString(),
                event.toString()), () -> assertEquals(makeDeadline.makeTask().toString(),
                deadline.toString())
        );
    }
}
