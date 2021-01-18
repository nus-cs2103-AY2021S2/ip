import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void parseInputNewDeadlineTask() {
        // Setup
        Duke.tasks = new ArrayList<>(100);

        // Test
        final String expectedOutput = "Got it. I've added this task:\n  [D][✗] return book (by: Sunday)\nNow you have 1 tasks in the list.";
        assertEquals(expectedOutput, Duke.parseInput("deadline return book /by Sunday"));

        assertEquals("return book", Duke.tasks.get(0).getTaskInfo());
        assertEquals(false, Duke.tasks.get(0).getCompletionState());
        assertEquals("[D][✗] return book (by: Sunday)", Duke.tasks.get(0).toString());
        assertTrue(Duke.tasks.get(0) instanceof DeadlineTask);
    }

    @Test
    public void parseInputNewEventTask() {
        // Setup
        Duke.tasks = new ArrayList<>(100);

        // Test
        final String expectedOutput = "Got it. I've added this task:\n  [E][✗] project meeting (at: Mon 2-4pm)\nNow you have 1 tasks in the list.";
        assertEquals(expectedOutput, Duke.parseInput("event project meeting /at Mon 2-4pm"));

        assertEquals("project meeting", Duke.tasks.get(0).getTaskInfo());
        assertEquals(false, Duke.tasks.get(0).getCompletionState());
        assertEquals("[E][✗] project meeting (at: Mon 2-4pm)", Duke.tasks.get(0).toString());
        assertTrue(Duke.tasks.get(0) instanceof EventTask);
    }

    @Test
    public void parseInputNewTodoTask() {
        // Setup
        Duke.tasks = new ArrayList<>(100);

        // Test
        final String expectedOutput = "Got it. I've added this task:\n  [T][✗] borrow book\nNow you have 1 tasks in the list.";
        assertEquals(expectedOutput, Duke.parseInput("todo borrow book"));

        assertEquals("borrow book", Duke.tasks.get(0).getTaskInfo());
        assertEquals(false, Duke.tasks.get(0).getCompletionState());
        assertEquals("[T][✗] borrow book", Duke.tasks.get(0).toString());
        assertTrue(Duke.tasks.get(0) instanceof TodoTask);
    }

    @Test
    public void chatLoopParseEventTaskInteractionOutput() throws IOException {
        // Setup
        Duke.tasks = new ArrayList<>(100);

        // Test
        String testInput = "event project meeting /at Mon 2-4pm\nlist\ndone 1\nlist\n";

        InputStream in = makeInputStreamFromString(testInput);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Duke.chatLoop(in, out);

        final String expectedOutput = "Got it. I've added this task:\n  [E][✗] project meeting (at: Mon 2-4pm)\nNow you have 1 tasks in the list.\n"
                + "1.[E][✗] project meeting (at: Mon 2-4pm)\n"
                + "Nice! I've marked this task as done:\n  [E][✓] project meeting (at: Mon 2-4pm)\n"
                + "1.[E][✓] project meeting (at: Mon 2-4pm)\n";
        assertEquals(expectedOutput, new String(out.toByteArray()));
    }

    @Test
    public void chatLoopParseDeadlineTaskInteractionOutput() throws IOException {
        // Setup
        Duke.tasks = new ArrayList<>(100);

        // Test
        String testInput = "deadline return book /by Sunday\nlist\ndone 1\nlist\n";

        InputStream in = makeInputStreamFromString(testInput);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Duke.chatLoop(in, out);

        final String expectedOutput = "Got it. I've added this task:\n  [D][✗] return book (by: Sunday)\nNow you have 1 tasks in the list.\n"
                + "1.[D][✗] return book (by: Sunday)\n"
                + "Nice! I've marked this task as done:\n  [D][✓] return book (by: Sunday)\n"
                + "1.[D][✓] return book (by: Sunday)\n";
        assertEquals(expectedOutput, new String(out.toByteArray()));
    }

    @Test
    public void chatLoopParseTodoTaskInteractionOutput() throws IOException {
        // Setup
        Duke.tasks = new ArrayList<>(100);

        // Test
        String testInput = "todo borrow book\nlist\ndone 1\nlist\n";

        InputStream in = makeInputStreamFromString(testInput);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Duke.chatLoop(in, out);

        final String expectedOutput = "Got it. I've added this task:\n  [T][✗] borrow book\nNow you have 1 tasks in the list.\n"
                + "1.[T][✗] borrow book\n" + "Nice! I've marked this task as done:\n  [T][✓] borrow book\n"
                + "1.[T][✓] borrow book\n";
        assertEquals(expectedOutput, new String(out.toByteArray()));
    }

    @Test
    public void chatLoopParseDoneSetsTaskAsDone() throws IOException {
        // Setup
        Duke.tasks = new ArrayList<>(100);

        // Test
        String testInput = "todo help people\n" + "todo help myself\n" + "done 1\n" + "list\n";

        InputStream in = makeInputStreamFromString(testInput);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Duke.chatLoop(in, out);

        final String expectedOutput = "Got it. I've added this task:\n  [T][✗] help people\nNow you have 1 tasks in the list.\n"
                + "Got it. I've added this task:\n  [T][✗] help myself\nNow you have 2 tasks in the list.\n"
                + "Nice! I've marked this task as done:\n  [T][✓] help people\n" + "1.[T][✓] help people\n"
                + "2.[T][✗] help myself\n";
        assertEquals(expectedOutput, new String(out.toByteArray()));
    }

    @Test
    public void parseInputDoneSetsTaskAsDone() {
        // Setup
        Duke.tasks = new ArrayList<>(100);
        Duke.parseInput("help people");

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

        Duke.parseInput("help people");

        assertEquals(tasksStateLength + 1, Duke.tasks.size());

        assertEquals("help people", Duke.tasks.get(tasksStateLength).getTaskInfo());
        assertEquals(false, Duke.tasks.get(tasksStateLength).getCompletionState());
        assertEquals("[✗] help people", Duke.tasks.get(tasksStateLength).toString());
    }

    @Test
    public void parseInputAddToListMaintainsOrder() {
        int tasksStateLength = Duke.tasks.size();

        Duke.parseInput("help people");
        Duke.parseInput("blah");
        Duke.parseInput("read book");

        assertEquals(tasksStateLength + 3, Duke.tasks.size());

        assertEquals("help people", Duke.tasks.get(tasksStateLength).getTaskInfo());
        assertEquals("blah", Duke.tasks.get(tasksStateLength + 1).getTaskInfo());
        assertEquals("read book", Duke.tasks.get(tasksStateLength + 2).getTaskInfo());
    }

    @Test
    public void parseInputListOutputTasksState() {
        // Setup
        Duke.tasks = new ArrayList<>(100);
        Duke.parseInput("help people");
        Duke.parseInput("blah");
        Duke.parseInput("read book");

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