package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.Deadlines;
import tasks.Events;
import tasks.Todo;


public class CommandTest {

    @Test
    public void exitTest() {
        assertEquals(true, new ByeCommand().isExit());
        assertEquals(false, new ListCommand().isExit());
        assertEquals(false, new DoneCommand(0).isExit());
        assertEquals(false, new DeleteCommand(0).isExit());
        assertEquals(false, new AddCommand(new Todo("test")).isExit());
        assertEquals(false, new AddCommand(new Deadlines("test", "2020-06-02")).isExit());
        assertEquals(false, new AddCommand(new Events("test", "Monday",
                "10:00", "12:00")).isExit());
    }
}
