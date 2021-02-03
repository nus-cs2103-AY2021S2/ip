package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class ParserTest {

    // code taken from StackOverFlow at
    // https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void out() {
        setUpStreams();
        System.out.print("hello");
        assertEquals("hello", outContent.toString());
        restoreStreams();
    }

    @Test
    public void parserToDoTest() {
        setUpStreams();

        duke.Parser p = new duke.Parser();
        String actual = Ui.welcome();
        actual += p.parser("clear");
        actual += p.parser("todo read a book");

        String check = "    ____________________________________________________________\n"
                + "\n"
                + "      Hello! I'm Duke\n"
                + "      What can I do for you?\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      The list has been cleared\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [T][ ]  read a book\n"
                + "      now you have 1 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n";

        assertEquals(check, actual);
        restoreStreams();
    }

    @Test
    public void parserDeadlineTest() {
        setUpStreams();

        duke.Parser p = new duke.Parser();
        String actual = Ui.welcome();
        actual += p.parser("clear");
        actual += p.parser("deadline read a book /by 2021-01-31");

        String check = "    ____________________________________________________________\n"
                + "\n"
                + "      Hello! I'm Duke\n"
                + "      What can I do for you?\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      The list has been cleared\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      now you have 1 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n";

        assertEquals(check, actual);
        restoreStreams();
    }

    @Test
    public void parserEventTest() {
        setUpStreams();

        duke.Parser p = new duke.Parser();
        String actual = Ui.welcome();
        actual += p.parser("clear");
        actual += p.parser("event read a book /at 2021-01-31");

        String check = "    ____________________________________________________________\n"
                + "\n"
                + "      Hello! I'm Duke\n"
                + "      What can I do for you?\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      The list has been cleared\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [E][ ]  read a book (at: 31 Jan 2021)\n"
                + "      now you have 1 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n";

        assertEquals(check, actual);
        restoreStreams();
    }

    @Test
    public void parserListTest() {
        setUpStreams();

        duke.Parser p = new duke.Parser();
        String actual = Ui.welcome();
        actual += p.parser("clear");
        actual += p.parser("todo read a book");
        actual += p.parser("deadline read a book /by 2021-01-31");
        actual += p.parser("event read a book /at 2021-01-31");
        actual += p.parser("list");

        String check = "    ____________________________________________________________\n"
                + "\n"
                + "      Hello! I'm Duke\n"
                + "      What can I do for you?\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      The list has been cleared\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [T][ ]  read a book\n"
                + "      now you have 1 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      now you have 2 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [E][ ]  read a book (at: 31 Jan 2021)\n"
                + "      now you have 3 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Here are the tasks in your list:\n"
                + "      1.  [T][ ]  read a book\n"
                + "      2.  [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      3.  [E][ ]  read a book (at: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n";

        assertEquals(check, actual);
        restoreStreams();
    }

    @Test
    public void parserDoneTest() {
        setUpStreams();

        duke.Parser p = new duke.Parser();
        String actual = Ui.welcome();
        actual += p.parser("clear");
        actual += p.parser("todo read a book");
        actual += p.parser("deadline read a book /by 2021-01-31");
        actual += p.parser("event read a book /at 2021-01-31");
        actual += p.parser("list");
        actual += p.parser("done 1");
        actual += p.parser("done 2");
        actual += p.parser("done 3");
        actual += p.parser("list");

        String check = "    ____________________________________________________________\n"
                + "\n"
                + "      Hello! I'm Duke\n"
                + "      What can I do for you?\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      The list has been cleared\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [T][ ]  read a book\n"
                + "      now you have 1 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      now you have 2 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [E][ ]  read a book (at: 31 Jan 2021)\n"
                + "      now you have 3 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Here are the tasks in your list:\n"
                + "      1.  [T][ ]  read a book\n"
                + "      2.  [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      3.  [E][ ]  read a book (at: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Nice! I've marked this task as done:\n"
                + "      [T][X]  read a book\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Nice! I've marked this task as done:\n"
                + "      [D][X]  read a book (by: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Nice! I've marked this task as done:\n"
                + "      [E][X]  read a book (at: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Here are the tasks in your list:\n"
                + "      1.  [T][X]  read a book\n"
                + "      2.  [D][X]  read a book (by: 31 Jan 2021)\n"
                + "      3.  [E][X]  read a book (at: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n";


        assertEquals(check, actual);
        restoreStreams();
    }

    @Test
    public void parserDeleteTest() {
        setUpStreams();

        duke.Parser p = new duke.Parser();
        String actual = Ui.welcome();
        actual += p.parser("clear");
        actual += p.parser("todo read a book");
        actual += p.parser("deadline read a book /by 2021-01-31");
        actual += p.parser("event read a book /at 2021-01-31");
        actual += p.parser("list");
        actual += p.parser("delete 2");
        actual += p.parser("list");

        String check = "    ____________________________________________________________\n"
                + "\n"
                + "      Hello! I'm Duke\n"
                + "      What can I do for you?\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      The list has been cleared\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [T][ ]  read a book\n"
                + "      now you have 1 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      now you have 2 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [E][ ]  read a book (at: 31 Jan 2021)\n"
                + "      now you have 3 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Here are the tasks in your list:\n"
                + "      1.  [T][ ]  read a book\n"
                + "      2.  [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      3.  [E][ ]  read a book (at: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Noted. I've removed this task:\n"
                + "      [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      now you have 2 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Here are the tasks in your list:\n"
                + "      1.  [T][ ]  read a book\n"
                + "      2.  [E][ ]  read a book (at: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n";

        assertEquals(check, actual);
        restoreStreams();
    }

    @Test
    public void parserTasksOnDayTest() {
        setUpStreams();

        duke.Parser p = new duke.Parser();
        String actual = Ui.welcome();
        actual += p.parser("clear");
        actual += p.parser("todo read a book");
        actual += p.parser("deadline read a book /by 2021-01-31");
        actual += p.parser("event read a book /at 2021-01-31");
        actual += p.parser("todo study");
        actual += p.parser("deadline study /by 2021-01-31");
        actual += p.parser("event study /at 2021-01-31");
        actual += p.parser("done 1");
        actual += p.parser("done 3");
        actual += p.parser("done 5");
        actual += p.parser("list");
        actual += p.parser("taskson 2021-01-30");
        actual += p.parser("taskson 2021-01-31");

        String check = "    ____________________________________________________________\n"
                + "\n"
                + "      Hello! I'm Duke\n"
                + "      What can I do for you?\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      The list has been cleared\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [T][ ]  read a book\n"
                + "      now you have 1 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      now you have 2 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [E][ ]  read a book (at: 31 Jan 2021)\n"
                + "      now you have 3 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [T][ ]  study\n"
                + "      now you have 4 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [D][ ]  study (by: 31 Jan 2021)\n"
                + "      now you have 5 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Got it. I've added this task: \n"
                + "      [E][ ]  study (at: 31 Jan 2021)\n"
                + "      now you have 6 tasks in the list.\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Nice! I've marked this task as done:\n"
                + "      [T][X]  read a book\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Nice! I've marked this task as done:\n"
                + "      [E][X]  read a book (at: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Nice! I've marked this task as done:\n"
                + "      [D][X]  study (by: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Here are the tasks in your list:\n"
                + "      1.  [T][X]  read a book\n"
                + "      2.  [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      3.  [E][X]  read a book (at: 31 Jan 2021)\n"
                + "      4.  [T][ ]  study\n"
                + "      5.  [D][X]  study (by: 31 Jan 2021)\n"
                + "      6.  [E][ ]  study (at: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Here are the tasks on 2021-01-30:\n"
                + "      1.  [T][ ]  study\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "    ____________________________________________________________\n"
                + "\n"
                + "      Here are the tasks on 2021-01-31:\n"
                + "      1.  [D][ ]  read a book (by: 31 Jan 2021)\n"
                + "      2.  [T][ ]  study\n"
                + "      3.  [E][ ]  study (at: 31 Jan 2021)\n"
                + "    ____________________________________________________________\n"
                + "\n";

        assertEquals(check, actual);
        restoreStreams();
    }
}
