import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void parseInputAddToList() {
        int tasksStateLength = Duke.tasks.size();

        assertEquals("added: help people", Duke.parseInput("help people"));

        assertEquals(tasksStateLength + 1, Duke.tasks.size());

        assertEquals(Duke.tasks.get(tasksStateLength).getTaskInfo(), "help people");
        assertEquals(Duke.tasks.get(tasksStateLength).getCompletionState(), false);
        assertEquals(Duke.tasks.get(tasksStateLength).toString(), "[✗] help people");
    }

    @Test
    public void parseInputAddToListMaintainsOrder() {
        int tasksStateLength = Duke.tasks.size();

        assertEquals("added: help people", Duke.parseInput("help people"));
        assertEquals("added: blah", Duke.parseInput("blah"));
        assertEquals("added: read book", Duke.parseInput("read book"));

        assertEquals(tasksStateLength + 3, Duke.tasks.size());

        assertEquals(Duke.tasks.get(tasksStateLength).getTaskInfo(), "help people");
        assertEquals(Duke.tasks.get(tasksStateLength + 1).getTaskInfo(), "blah");
        assertEquals(Duke.tasks.get(tasksStateLength + 2).getTaskInfo(), "read book");
    }

    @Test
    public void parseInputListOutputTasksState() {
        // Setup
        Duke.tasks = new ArrayList<>(100);
        assertEquals("added: help people", Duke.parseInput("help people"));
        assertEquals("added: blah", Duke.parseInput("blah"));
        assertEquals("added: read book", Duke.parseInput("read book"));

        final String expectedString = "1.[✗] help people\n2.[✗] blah\n3.[✗] read book\n";

        // Test
        assertEquals(expectedString, Duke.parseInput("list"));
    }

    @Test
    public void parseInputByeOutputFarewell() {
        assertEquals("Bye. Hope to see you again soon!", Duke.parseInput("bye"));
    }

    @Test
    public void chatInputByeExitsLoop() throws IOException {
        String testInput = "bye\n" + "hello\n" + "bye\n";

        InputStream ins = new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Duke.chatLoop(ins, out);

        assertEquals("Bye. Hope to see you again soon!\n", new String(out.toByteArray()));
    }

    @Test
    public void chatLoopWritesToOutput() throws IOException {
        String testInput = "echoThis\n" + "echoThisAsWell\n" + "echoThisFinally\n";

        InputStream ins = new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Duke.chatLoop(ins, out);

        assertEquals(null, in.readLine());
        assertNotEquals("", new String(out.toByteArray()));
    }
}