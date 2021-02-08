package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestTaskList {
    private final TaskList tasks;
    private final ToDo toDo;
    private final Deadline deadline;
    private final Event event;

    public TestTaskList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2021-02-06 23:30", formatter);

        this.tasks = new TaskList();
        this.toDo = new ToDo("CS2103 Quiz 1");
        this.deadline = new Deadline("CS2103 Quiz 2", dateTime);
        this.event = new Event("CS2103 Quiz 3", dateTime);

        this.tasks.addTask(this.toDo);
        this.tasks.addTask(this.deadline);
        this.tasks.addTask(this.event);
    }

    @Test
    public void testGetListOfTasks() {
        ArrayList<Task> listOfTasks = this.tasks.getListOfTasks();
        assertEquals(3, listOfTasks.size());
        assertEquals(this.toDo, listOfTasks.get(0));
        assertEquals(this.deadline, listOfTasks.get(1));
        assertEquals(this.event, listOfTasks.get(2));
    }

    @Test
    public void testGetTask() {
        assertNull(this.tasks.getTaskByIndex(-1));
        assertNull(this.tasks.getTaskByIndex(0));
        assertNull(this.tasks.getTaskByIndex(4));

        assertEquals(this.toDo, this.tasks.getTaskByIndex(1));
        assertEquals(this.deadline, this.tasks.getTaskByIndex(2));
        assertEquals(this.event, this.tasks.getTaskByIndex(3));
    }

    @Test
    public void testPopTask() {
        assertNull(this.tasks.popTaskByIndex(-1));
        assertNull(this.tasks.popTaskByIndex(0));
        assertNull(this.tasks.popTaskByIndex(4));

        assertEquals(this.event, this.tasks.popTaskByIndex(3));
        assertEquals(this.deadline, this.tasks.popTaskByIndex(2));
        assertEquals(this.toDo, this.tasks.popTaskByIndex(1));

        this.tasks.addTask(this.toDo);
        this.tasks.addTask(this.deadline);
        this.tasks.addTask(this.event);
    }

    @Test
    public void testPrintTasks() {
        this.tasks.printTasks();
    }

    @Test
    public void testGetSize() {
        assertEquals(3, this.tasks.getSize());
    }
}
