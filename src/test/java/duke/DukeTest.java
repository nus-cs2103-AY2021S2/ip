package duke;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    // code taken from StackOverFlow at
    // https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    // code taken from StackOverFlow at
    // https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
    private ByteArrayInputStream in;
    private final InputStream sysInBackup = System.in;

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
    public void dummyTest(){
        assertEquals(2, 2);
    }
/*
    @Test
    public void dukeRetainStateTest() {
        setUpStreams();

        Duke.main(new String[] {""});
        String cmd = "clear"
                //+ System.lineSeparator()
                + "todo read a book"
                //+ System.lineSeparator()
                + "list"
                //+ System.lineSeparator()
                + "bye";
                //+ System.lineSeparator();

        this.in = new ByteArrayInputStream(("clear"
                + System.lineSeparator()
                + "todo read a book"
                + System.lineSeparator()
                + "list"
                + System.lineSeparator()
                + "bye"
                + System.lineSeparator()).getBytes());
        System.setIn(this.in);

        Duke.main(null);
        cmd = "list\n" + "bye\n";

        this.in = new ByteArrayInputStream("My string".getBytes());
        System.setIn(this.in);

        assertEquals(2, 2);

        restoreStreams();
        System.setIn(sysInBackup);
    }

 */
}
