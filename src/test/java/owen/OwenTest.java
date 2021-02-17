import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import owen.Owen;
import owen.OwenException;

public class OwenTest {
    @Test
    public void todoCommand_correctFormat_success() throws OwenException {
        Owen owen = new Owen();

        owen = owen.parseCommand("todo test");
        String expected = "Hoot hoot. I've added this task:\n"
                + "    [T][ ] test\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(expected, owen.getResponse());

        owen.parseCommand("delete 1");
    }

    @Test
    public void eventCommand_correctFormat_success() throws OwenException {
        Owen owen = new Owen();

        owen = owen.parseCommand("event test /at 1/1/2021 1400 - 01/01/2021 1600");
        String expected = "Hoot hoot. I've added this task:\n"
                + "    [E][ ] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(expected, owen.getResponse());

        owen.parseCommand("delete 1");
    }

    @Test
    public void deadlineCommand_correctFormat_success() throws OwenException {
        Owen owen = new Owen();

        owen = owen.parseCommand("deadline test /by 1/1/2021 1400");
        String expected = "Hoot hoot. I've added this task:\n"
                + "    [D][ ] test (by: January 1 2021 2:00 PM)\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(expected, owen.getResponse());

        owen.parseCommand("delete 1");
    }

    @Test
    public void listCommand_correctFormat_success() throws OwenException {
        Owen owen = new Owen();

        owen = owen.parseCommand("todo test");
        owen = owen.parseCommand("event test /at 1/1/2021 1400 - 01/01/2021 1600");
        owen = owen.parseCommand("deadline test /by 1/1/2021 1400");

        owen = owen.parseCommand("list");
        String expected = "1. [T][ ] test\n"
                + "2. [E][ ] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)\n"
                + "3. [D][ ] test (by: January 1 2021 2:00 PM)";
        assertEquals(expected, owen.getResponse());

        owen = owen.parseCommand("delete 1");
        owen = owen.parseCommand("delete 1");
        owen = owen.parseCommand("delete 1");
    }

    @Test
    public void doneCommand_correctFormat_success() throws OwenException {
        Owen owen = new Owen();

        owen = owen.parseCommand("todo test");
        owen = owen.parseCommand("event test /at 1/1/2021 1400 - 01/01/2021 1600");
        owen = owen.parseCommand("deadline test /by 1/1/2021 1400");

        owen = owen.parseCommand("done 2");
        String expected = "Hoot! I've marked this task as done:\n"
                + "    [E][X] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)";
        assertEquals(expected, owen.getResponse());

        owen = owen.parseCommand("delete 1");
        owen = owen.parseCommand("delete 1");
        owen = owen.parseCommand("delete 1");
    }

    @Test
    public void deleteCommand_correctFormat_success() throws OwenException {
        Owen owen = new Owen();

        owen = owen.parseCommand("todo test");
        owen = owen.parseCommand("event test /at 1/1/2021 1400 - 01/01/2021 1600");
        owen = owen.parseCommand("deadline test /by 1/1/2021 1400");

        owen = owen.parseCommand("delete 2");
        String expected = "Hoot. I've removed this task:\n"
                + "    [E][ ] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)\n"
                + "Now you have 2 tasks in the list.";
        assertEquals(expected, owen.getResponse());

        owen = owen.parseCommand("delete 1");
        owen = owen.parseCommand("delete 1");
    }

    @Test
    public void findCommand_correctFormat_success() throws OwenException {
        Owen owen = new Owen();

        owen = owen.parseCommand("todo apple");
        owen = owen.parseCommand("event orange /at 1/1/2021 1400 - 01/01/2021 1600");
        owen = owen.parseCommand("deadline app /by 1/1/2021 1400");

        owen = owen.parseCommand("find app");
        String expected = "Hoot hoot here are the matching tasks in your list:\n"
                + "1. [T][ ] apple\n"
                + "3. [D][ ] app (by: January 1 2021 2:00 PM)";
        assertEquals(expected, owen.getResponse());

        owen = owen.parseCommand("delete 1");
        owen = owen.parseCommand("delete 1");
        owen = owen.parseCommand("delete 1");
    }
}
