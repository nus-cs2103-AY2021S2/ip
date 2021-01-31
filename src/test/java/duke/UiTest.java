package duke;

import duke.bot.Ui;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests to be run on the message output process of the chat bot */
public class UiTest {
    /** A reference to the default output stream */
    private final PrintStream standardOut = System.out;
    /** A mock output stream to capture outputs from the Ui */
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    /** Borderlines to contain a display message*/
    private final String BORDER = "___________________________________________________________";

    /** Replace the system's output stream with a custom stream to capture the output */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void printMsg_test() {
        String msg = "Testing Message 1234567890!";
        String expected = BORDER + "\r\n" + msg + "\r\n" + BORDER;

        Ui ui = new Ui();
        ui.constructMsg(msg);
        assertEquals(expected, outputStream.toString().trim());
    }

    /** Tests printing of task list when the list is empty */
    @Test
    public void printTaskList_emptyList() {
        String expected = BORDER + "\r\n" + "Meow, here are the tasks in your list:\r\n" + BORDER;

        List<Task> tasks = new ArrayList<>();

        Ui ui = new Ui();
        ui.constructTaskList(tasks);
        assertEquals(expected, outputStream.toString().trim());
    }

    /** Tests printing of task list when the list has 3 items */
    @Test
    public void printTaskList_3Items() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        LocalDateTime dateTime = LocalDateTime.now();
        String expected = BORDER + "\r\nMeow, here are the tasks in your list:\r\n" +
                "1.[T][ ] DESCRIPTION 1\n" + "2.[D][ ] DESCRIPTION 2 (By: " + dateTime.format(formatter) + ")\n" +
                "3.[E][ ] DESCRIPTION 3 (Start: " + dateTime.format(formatter) + " | End: " + dateTime.format(formatter)
                + ")\n" + BORDER;

        List<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("DESCRIPTION 1"));
        tasks.add(new Deadline("DESCRIPTION 2", dateTime));
        tasks.add(new Event("DESCRIPTION 3", dateTime, dateTime));

        Ui ui = new Ui();
        ui.constructTaskList(tasks);
        assertEquals(expected, outputStream.toString().trim());
    }

    /** Tests printing of add message when the list has 3 items and each is an unique type */
    @Test
    public void printAddMsg_taskTypesAll_tasksSize3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        LocalDateTime dateTime = LocalDateTime.now();

        String expectedToDo = BORDER + "\r\nGot it meow. I've added this task:\r\n  [T][ ] DESCRIPTION 1\nNow you " +
                "have 99 tasks in the list.\n" + BORDER;
        String expectedDeadline = BORDER + "\r\nGot it meow. I've added this task:\r\n  [D][ ] DESCRIPTION 2 (By: " +
                dateTime.format(formatter) + ")\nNow you have 99 tasks in the list.\n" + BORDER;
        String expectedEvent = BORDER + "\r\nGot it meow. I've added this task:\r\n  [E][ ] DESCRIPTION 3 (Start: " +
                dateTime.format(formatter) + " | End: " + dateTime.format(formatter) + ")\n" +
                "Now you have 99 tasks in the list.\n" + BORDER;

        ToDo toDo = new ToDo("DESCRIPTION 1");
        Deadline deadline = new Deadline("DESCRIPTION 2", dateTime);
        Event event = new Event("DESCRIPTION 3", dateTime, dateTime);

        Ui ui = new Ui();
        ui.constructAddMsg(toDo, 99);
        assertEquals(expectedToDo, outputStream.toString().trim());

        outputStream.reset();
        ui.constructAddMsg(deadline, 99);
        assertEquals(expectedDeadline, outputStream.toString().trim());

        outputStream.reset();
        ui.constructAddMsg(event, 99);
        assertEquals(expectedEvent, outputStream.toString().trim());
    }

    /** Tests printing of done message when the list has 3 items and each is an unique type */
    @Test
    public void printDoneMsg_taskTypesAll_tasksSize3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        LocalDateTime dateTime = LocalDateTime.now();

        String expectedToDo = BORDER + "\r\nGood job meow, I've marked this task as done:\r\n100.[T][ ] DESCRIPTION" +
                " 1\n" + BORDER;
        String expectedDeadline = BORDER + "\r\nGood job meow, I've marked this task as done:\r\n100.[D][ ] " +
                "DESCRIPTION 2 (By: " + dateTime.format(formatter) + ")\n" + BORDER;
        String expectedEvent = BORDER + "\r\nGood job meow, I've marked this task as done:\r\n100.[E][ ] " +
                "DESCRIPTION 3 (Start: " + dateTime.format(formatter) + " | End: " + dateTime.format(formatter) +
                ")\n" + BORDER;

        ToDo toDo = new ToDo("DESCRIPTION 1");
        Deadline deadline = new Deadline("DESCRIPTION 2", dateTime);
        Event event = new Event("DESCRIPTION 3", dateTime, dateTime);

        Ui ui = new Ui();
        ui.constructDoneMsg(99, toDo);
        assertEquals(expectedToDo, outputStream.toString().trim());

        outputStream.reset();
        ui.constructDoneMsg(99, deadline);
        assertEquals(expectedDeadline, outputStream.toString().trim());

        outputStream.reset();
        ui.constructDoneMsg(99, event);
        assertEquals(expectedEvent, outputStream.toString().trim());
    }

    /** Tests printing of delete message when the list has 3 items and each is an unique type */
    @Test
    public void printDeleteMsg_taskTypesAll_tasksSize3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        LocalDateTime dateTime = LocalDateTime.now();

        String expectedToDo = BORDER + "\r\nNoted meow. I've removed this task:\r\n  [T][ ] DESCRIPTION" +
                " 1\nNow you have 99 tasks in the list.\n" + BORDER;
        String expectedDeadline = BORDER + "\r\nNoted meow. I've removed this task:\r\n  [D][ ] " +
                "DESCRIPTION 2 (By: " + dateTime.format(formatter) + ")\nNow you have 99 tasks in the list.\n" + BORDER;
        String expectedEvent = BORDER + "\r\nNoted meow. I've removed this task:\r\n  [E][ ] " +
                "DESCRIPTION 3 (Start: " + dateTime.format(formatter) + " | End: " + dateTime.format(formatter) +
                ")\nNow you have 99 tasks in the list.\n" + BORDER;

        ToDo toDo = new ToDo("DESCRIPTION 1");
        Deadline deadline = new Deadline("DESCRIPTION 2", dateTime);
        Event event = new Event("DESCRIPTION 3", dateTime, dateTime);

        Ui ui = new Ui();
        ui.constructDeleteMsg(toDo, 99);
        assertEquals(expectedToDo, outputStream.toString().trim());

        outputStream.reset();
        ui.constructDeleteMsg(deadline, 99);
        assertEquals(expectedDeadline, outputStream.toString().trim());

        outputStream.reset();
        ui.constructDeleteMsg(event, 99);
        assertEquals(expectedEvent, outputStream.toString().trim());
    }

    /** Tests printing of search results when there are 3 tasks of different types */
    @Test
    public void printFoundMsg_taskTypesAll_tasksSize3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        LocalDateTime dateTime = LocalDateTime.now();

        String expected = BORDER + "\r\nMeow, here are the matching tasks in your list:\r\n" +
                "1.[T][ ] DESCRIPTION 1\n" + "2.[D][ ] DESCRIPTION 2 (By: " + dateTime.format(formatter) + ")\n" +
                "3.[E][ ] DESCRIPTION 3 (Start: " + dateTime.format(formatter) + " | End: " + dateTime.format(formatter)
                + ")\n" + BORDER;

        List<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("DESCRIPTION 1"));
        tasks.add(new Deadline("DESCRIPTION 2", dateTime));
        tasks.add(new Event("DESCRIPTION 3", dateTime, dateTime));

        Ui ui = new Ui();
        ui.constructFoundMsg(tasks);
        assertEquals(expected, outputStream.toString().trim());
    }

    /** Reset the system's output stream with system.out */
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
