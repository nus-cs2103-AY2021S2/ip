package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeCorruptFileException;
import duke.exception.DukeException;
import duke.exception.DukeIndexRangeException;
import duke.exception.DukeTaskAlreadyDoneException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;



public class TaskManagerTest {

    /*
     * Task Manager Operators
     */
    @Test
    public void testInitialisation() {
        TaskManager tm = new TaskManager();
        assertEquals(tm.getSize(), 0);
        assertEquals(tm.getTasks().size(), 0);
    }

    @Test
    public void testReadFile() throws DukeException {
        TaskManager tm = new TaskManager();
        //to simulate input from Storage
        ArrayList<String> input = new ArrayList<>();
        input.add("T|0|test1");
        input.add("E|0|test2|12.Dec.9090");
        input.add("D|1|test3|12.Dec.1999");

        tm.loadArray(input);
        assertEquals(tm.getSize(), 3);

        ArrayList<Task> tasks = tm.getTasks();
        assertTrue(tasks.get(0) instanceof ToDoTask);
        assertTrue(tasks.get(1) instanceof EventTask);
        assertTrue(tasks.get(2) instanceof DeadlineTask);

        assertEquals(tasks.get(0).getName(), "test1");
        assertEquals(tasks.get(1).getName(), "test2");
        assertEquals(tasks.get(2).getName(), "test3");
    }

    @Test
    public void testGetSize() throws DukeException {
        TaskManager tm = new TaskManager();
        //to simulate input from Storage
        ArrayList<String> input = new ArrayList<>();
        input.add("T|0|test1");
        input.add("E|0|test2|12.Dec.9090");
        input.add("D|1|test3|12.Dec.1999");

        assertEquals(tm.getSize(), 0);
        tm.loadArray(input);
        assertEquals(tm.getSize(), 3);
    }

    @Test
    public void testDelete() throws DukeException {
        TaskManager tm = new TaskManager();
        //to simulate input from Storage
        ArrayList<String> input = new ArrayList<>();
        input.add("T|0|test1");
        input.add("E|0|test2|12.Dec.9090");
        input.add("D|1|test3|12.Dec.1999");

        tm.loadArray(input);
        assertEquals(tm.getSize(), 3);
        tm.deleteTask(2);
        assertEquals(tm.getSize(), 2);
        assertEquals(tm.getTasks().get(1).getClass(),
                DeadlineTask.class);
    }

    @Test
    public void testClear() throws DukeException {
        TaskManager tm = new TaskManager();
        //to simulate input from Storage
        ArrayList<String> input = new ArrayList<>();
        input.add("T|0|test1");
        input.add("E|0|test2|12.Dec.9090");
        input.add("D|1|test3|12.Dec.1999");

        assertEquals(tm.getSize(), 0);
        tm.loadArray(input);
        assertEquals(tm.getSize(), 3);
        tm.clear();
        assertEquals(tm.getSize(), 0);
    }

    @Test
    public void testAddToDoTask() {
        TaskManager tm = new TaskManager();
        assertEquals(tm.getSize(), 0);
        tm.addToDoTask("test");
        assertEquals(tm.getSize(), 1);
        assertEquals(tm.getTasks().get(0).getClass(),
                ToDoTask.class);

    }

    @Test
    public void testAddDeadineTask() {
        TaskManager tm = new TaskManager();
        assertEquals(tm.getSize(), 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        LocalDate d = LocalDate.parse("12.Dec.1999", formatter);

        tm.addDeadlineTask("test", d);
        assertEquals(tm.getSize(), 1);
        assertEquals(tm.getTasks().get(0).getClass(),
                DeadlineTask.class);
    }

    @Test
    public void testAddEventTask() {
        TaskManager tm = new TaskManager();
        assertEquals(tm.getSize(), 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        LocalDate d = LocalDate.parse("12.Dec.1999", formatter);

        tm.addEventTask("test", d);
        assertEquals(tm.getSize(), 1);
        assertEquals(tm.getTasks().get(0).getClass(),
                EventTask.class);
    }

    /*
     * Invalid Operation Tests
     */
    @Test
    public void testCorruptFileTaskType() {
        TaskManager tm = new TaskManager();
        //to simulate input from Storage
        ArrayList<String> input = new ArrayList<>();
        input.add("X|1|test1");
        assertThrows(DukeCorruptFileException.class, () -> tm.loadArray(input));
    }

    @Test
    public void testCorruptFileMissingDate() {
        TaskManager tm = new TaskManager();
        //to simulate input from Storage
        ArrayList<String> input = new ArrayList<>();
        input.add("D|1|test1");
        assertThrows(DukeCorruptFileException.class, () -> tm.loadArray(input));
    }

    @Test
    public void testCorruptFileCompletion() {
        TaskManager tm = new TaskManager();
        //to simulate input from Storage
        ArrayList<String> input = new ArrayList<>();
        input.add("D|V|test1");
        assertThrows(DukeCorruptFileException.class, () -> tm.loadArray(input));
    }

    @Test
    public void testCorruptFileDate() {
        TaskManager tm = new TaskManager();
        //to simulate input from Storage
        ArrayList<String> input = new ArrayList<>();
        input.add("D|1|test1|123");
        assertThrows(DukeCorruptFileException.class, () -> tm.loadArray(input));
    }

    @Test
    public void testMarkAlreadyDone() throws DukeException {
        TaskManager tm = new TaskManager();
        //to simulate input from Storage
        ArrayList<String> input = new ArrayList<>();
        input.add("T|1|test1");
        tm.loadArray(input);
        assertThrows(DukeTaskAlreadyDoneException.class, () -> tm.markTaskAsDone(1));
    }

    @Test
    public void testMarkTaskOutOfRange() {
        TaskManager tm = new TaskManager();
        assertThrows(DukeIndexRangeException.class, () -> tm.markTaskAsDone(1));
    }

    @Test
    public void testDeleteOutOfRange() {
        TaskManager tm = new TaskManager();
        assertThrows(DukeIndexRangeException.class, () -> tm.deleteTask(1));
    }
}
