import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void chatLoopParseDoneSetsTaskAsDone() throws IOException {
        // Setup
        Duke.tasks = new ArrayList<>(100);

        // Test
        String testInput = "help people\n" + "help myself\n" + "done 1\n" + "list\n";

        InputStream in = makeInputStreamFromString(testInput);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Duke.chatLoop(in, out);

        final String expectedOutput = "added: help people\n" + "added: help myself\n"
                + "Nice! I've marked this task as done:\n  [✓] help people\n" + "1.[✓] help people\n"
                + "2.[✗] help myself\n";
        assertEquals(expectedOutput, new String(out.toByteArray()));
    }

    @Test
    public void parseInputDoneSetsTaskAsDone() {
        // Setup
        Duke.tasks = new ArrayList<>(100);
        assertEquals("added: help people", Duke.parseInput("help people"));

        // Test
        assertEquals("help people", Duke.tasks.get(0).getTaskInfo());
        assertEquals(false, Duke.tasks.get(0).getCompletionState());
        assertEquals("[✗] help people", Duke.tasks.get(0).toString());

        final String expectedOutput = "Nice! I've marked this task as done:\n  [✓] help people";
        assertEquals(expectedOutput, Duke.parseInput("done 1"));

        assertEquals("help people", Duke.tasks.get(0).getTaskInfo());
        assertEquals(true, Duke.tasks.get(0).getCompletionState());
        assertEquals("[✓] help people", Duke.tasks.get(0).toString());
    }

    @Test
    public void parseInputAddToList() {
        int tasksStateLength = Duke.tasks.size();

        assertEquals("added: help people", Duke.parseInput("help people"));

        assertEquals(tasksStateLength + 1, Duke.tasks.size());

        assertEquals("help people", Duke.tasks.get(tasksStateLength).getTaskInfo());
        assertEquals(false, Duke.tasks.get(tasksStateLength).getCompletionState());
        assertEquals("[✗] help people", Duke.tasks.get(tasksStateLength).toString());
    }

    @Test
    public void parseInputAddToListMaintainsOrder() {
        int tasksStateLength = Duke.tasks.size();

        assertEquals("added: help people", Duke.parseInput("help people"));
        assertEquals("added: blah", Duke.parseInput("blah"));
        assertEquals("added: read book", Duke.parseInput("read book"));

        assertEquals(tasksStateLength + 3, Duke.tasks.size());

        assertEquals("help people", Duke.tasks.get(tasksStateLength).getTaskInfo());
        assertEquals("blah", Duke.tasks.get(tasksStateLength + 1).getTaskInfo());
        assertEquals("read book", Duke.tasks.get(tasksStateLength + 2).getTaskInfo());
    }

    @Test
    public void parseInputListOutputTasksState() {
        // Setup
        Duke.tasks = new ArrayList<>(100);
        assertEquals("added: help people", Duke.parseInput("help people"));
        assertEquals("added: blah", Duke.parseInput("blah"));
        assertEquals("added: read book", Duke.parseInput("read book"));

        final String expectedString = "1.[✗] help people\n2.[✗] blah\n3.[✗] read book";

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

        InputStream in = makeInputStreamFromString(testInput);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Duke.chatLoop(in, out);

        assertEquals("Bye. Hope to see you again soon!\n", new String(out.toByteArray()));
    }

    @Test
    public void chatLoopWritesToOutput() throws IOException {
        String testInput = "echoThis\n" + "echoThisAsWell\n" + "echoThisFinally\n";

        InputStream in = makeInputStreamFromString(testInput);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Duke.chatLoop(in, out);

        assertNotEquals("", new String(out.toByteArray()));
    }

    private ByteArrayInputStream makeInputStreamFromString(String testInput) {
        return new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8));
    }
}