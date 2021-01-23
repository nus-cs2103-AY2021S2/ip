package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final String BORDER = "___________________________________________________________";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void printMsg_test() {
        String msg = "Testing Message 1234567890!";
        String expected = BORDER + "\r\n" + msg + "\r\n" + BORDER;

        Ui ui = new Ui();
        ui.printMsg(msg);
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void printTaskList_emptyList() {
        String expected = BORDER + "\r\n" + "Meow, here are the tasks in your list:\r\n" + BORDER;

        List<Task> emptyList = new ArrayList<>();

        Ui ui = new Ui();
        ui.printTaskList(emptyList);
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void printTaskList_3Items() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        LocalDateTime dateTime = LocalDateTime.now();
        String expected = BORDER + "\r\nMeow, here are the tasks in your list:\r\n" +
                "1.[T][ ] DESCRIPTION 1\n" + "2.[D][ ] DESCRIPTION 2 (by: " + dateTime.format(formatter) + ")\n" +
                "3.[E][ ] DESCRIPTION 3 (Start: " + dateTime.format(formatter) + " | End: " + dateTime.format(formatter)
                + ")\n" + BORDER;

        List<Task> list = new ArrayList<>();
        list.add(new ToDo("DESCRIPTION 1"));
        list.add(new Deadline("DESCRIPTION 2", dateTime));
        list.add(new Event("DESCRIPTION 3", dateTime, dateTime));

        Ui ui = new Ui();
        ui.printTaskList(list);
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void printAddMsg_taskTypesAll_tasksSize3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        LocalDateTime dateTime = LocalDateTime.now();

        String expectedToDo = BORDER + "\r\nGot it meow. I've added this task:\r\n  [T][ ] DESCRIPTION 1\nNow you " +
                "have 99 tasks in the list.\n" + BORDER;
        String expectedDeadline = BORDER + "\r\nGot it meow. I've added this task:\r\n  [D][ ] DESCRIPTION 2 (by: " + dateTime.format(formatter) + ")\nNow " +
                "you have 99 tasks in the list.\n" + BORDER;
        String expectedEvent = BORDER + "\r\nGot it meow. I've added this task:\r\n  [E][ ] DESCRIPTION 3 (Start: " + dateTime.format(formatter) + " | End: " + dateTime.format(formatter)
                + ")\nNow you have 99 tasks in the list.\n" + BORDER;

        ToDo toDo = new ToDo("DESCRIPTION 1");
        Deadline deadline = new Deadline("DESCRIPTION 2", dateTime);
        Event event = new Event("DESCRIPTION 3", dateTime, dateTime);

        Ui ui = new Ui();
        ui.printAddMsg(toDo, 99);
        assertEquals(expectedToDo, outputStream.toString().trim());

        outputStream.reset();
        ui.printAddMsg(deadline, 99);
        assertEquals(expectedDeadline, outputStream.toString().trim());

        outputStream.reset();
        ui.printAddMsg(event, 99);
        assertEquals(expectedEvent, outputStream.toString().trim());
    }

    @Test
    public void printDoneMsg_taskTypesAll_tasksSize3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        LocalDateTime dateTime = LocalDateTime.now();

        String expectedToDo = BORDER + "\r\nGood job meow, I've marked this task as done:\r\n100.[T][ ] DESCRIPTION" +
                " 1\n" + BORDER;
        String expectedDeadline = BORDER + "\r\nGood job meow, I've marked this task as done:\r\n100.[D][ ] " +
                "DESCRIPTION 2 (by: " + dateTime.format(formatter) + ")\n" + BORDER;
        String expectedEvent = BORDER + "\r\nGood job meow, I've marked this task as done:\r\n100.[E][ ] " +
                "DESCRIPTION 3 (Start: " + dateTime.format(formatter) + " | End: " + dateTime.format(formatter) +
                ")\n" + BORDER;

        ToDo toDo = new ToDo("DESCRIPTION 1");
        Deadline deadline = new Deadline("DESCRIPTION 2", dateTime);
        Event event = new Event("DESCRIPTION 3", dateTime, dateTime);

        Ui ui = new Ui();
        ui.printDoneMsg(99, toDo);
        assertEquals(expectedToDo, outputStream.toString().trim());

        outputStream.reset();
        ui.printDoneMsg(99, deadline);
        assertEquals(expectedDeadline, outputStream.toString().trim());

        outputStream.reset();
        ui.printDoneMsg(99, event);
        assertEquals(expectedEvent, outputStream.toString().trim());
    }

    @Test
    public void printDeleteMsg_taskTypesAll_tasksSize3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        LocalDateTime dateTime = LocalDateTime.now();

        String expectedToDo = BORDER + "\r\nNoted meow. I've removed this task:\r\n  [T][ ] DESCRIPTION" +
                " 1\nNow you have 99 tasks in the list.\n" + BORDER;
        String expectedDeadline = BORDER + "\r\nNoted meow. I've removed this task:\r\n  [D][ ] " +
                "DESCRIPTION 2 (by: " + dateTime.format(formatter) + ")\nNow you have 99 tasks in the list.\n" + BORDER;
        String expectedEvent = BORDER + "\r\nNoted meow. I've removed this task:\r\n  [E][ ] " +
                "DESCRIPTION 3 (Start: " + dateTime.format(formatter) + " | End: " + dateTime.format(formatter) +
                ")\nNow you have 99 tasks in the list.\n" + BORDER;

        ToDo toDo = new ToDo("DESCRIPTION 1");
        Deadline deadline = new Deadline("DESCRIPTION 2", dateTime);
        Event event = new Event("DESCRIPTION 3", dateTime, dateTime);

        Ui ui = new Ui();
        ui.printDeleteMsg(toDo, 99);
        assertEquals(expectedToDo, outputStream.toString().trim());

        outputStream.reset();
        ui.printDeleteMsg(deadline, 99);
        assertEquals(expectedDeadline, outputStream.toString().trim());

        outputStream.reset();
        ui.printDeleteMsg(event, 99);
        assertEquals(expectedEvent, outputStream.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
