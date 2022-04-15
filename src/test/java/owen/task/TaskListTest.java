import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import owen.OwenException;
import owen.task.TaskList;

public class TaskListTest {
    @Test
    public void addTask_correctFormat_success() throws OwenException {
        TaskList taskList = new TaskList();
        taskList = taskList.addTask("todo test");
        taskList = taskList.addTask("event test /at 1/1/2021 1400 - 01/01/2021 1600");
        taskList = taskList.addTask("deadline test /by 1/1/2021 1400");

        String expected = "1. [T][ ] test\n"
                + "2. [E][ ] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)\n"
                + "3. [D][ ] test (by: January 1 2021 2:00 PM)";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void addTask_incorrectFormat_exception() {
        try {
            new TaskList().addTask("deadline test");
            fail();
        } catch (OwenException exception) {
            assertEquals(
                    "Ooooo noo...\nDeadline task must have a description and due date/time...",
                    exception.getMessage());
        }
    }

    @Test
    public void addTask_unknownTaskType_exception() {
        try {
            new TaskList().addTask("test");
            fail();
        } catch (OwenException exception) {
            assertEquals(
                    "Ooooo noo...\nUnknown task type...",
                    exception.getMessage());
        }
    }

    @Test
    public void markAsDone_taskExists_success() throws OwenException {
        TaskList taskList = new TaskList();
        taskList = taskList.addTask("todo test");
        taskList = taskList.addTask("event test /at 1/1/2021 1400 - 01/01/2021 1600");
        taskList = taskList.addTask("deadline test /by 1/1/2021 1400");
        taskList = taskList.markAsDone(3);

        String expected = "1. [T][ ] test\n"
                + "2. [E][ ] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)\n"
                + "3. [D][X] test (by: January 1 2021 2:00 PM)";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void markAsDone_taskNotExist_exception() {
        try {
            new TaskList().markAsDone(1);
        } catch (OwenException exception) {
            assertEquals("Ooooo noo...\nTask 1 does not exist...", exception.getMessage());
        }
    }

    @Test
    public void deleteTask_taskExists_success() throws OwenException {
        TaskList taskList = new TaskList();
        taskList = taskList.addTask("todo test");
        taskList = taskList.addTask("event test /at 1/1/2021 1400 - 01/01/2021 1600");
        taskList = taskList.addTask("deadline test /by 1/1/2021 1400");
        taskList = taskList.deleteTask(2);

        String expected = "1. [T][ ] test\n"
                + "2. [D][ ] test (by: January 1 2021 2:00 PM)";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void deleteTask_taskNotExist_exception() {
        try {
            new TaskList().deleteTask(1);
        } catch (OwenException exception) {
            assertEquals("Ooooo noo...\nTask 1 does not exist...", exception.getMessage());
        }
    }

    @Test
    public void serialize_serialize_success() throws OwenException {
        TaskList taskList = new TaskList();
        taskList = taskList.addTask("todo test");
        taskList = taskList.addTask("event test /at 1/1/2021 1400 - 01/01/2021 1600");
        taskList = taskList.addTask("deadline test /by 1/1/2021 1400");

        String expected = "TODO | false | test\n"
                + "EVENT | false | test | 1/1/2021 1400 | 1/1/2021 1600\n"
                + "DEADLINE | false | test | 1/1/2021 1400";
        assertEquals(expected, taskList.serialize());
    }

    @Test
    public void deserializeTask_correctFormat_success() throws OwenException {
        TaskList taskList = new TaskList();
        taskList = taskList.deserializeTask("TODO | false | test");
        taskList = taskList.deserializeTask("EVENT | false | test | 1/1/2021 1400 | 1/1/2021 1600");
        taskList = taskList.deserializeTask("DEADLINE | false | test | 1/1/2021 1400");

        String expected = "1. [T][ ] test\n"
                + "2. [E][ ] test (at: January 1 2021 2:00 PM - January 1 2021 4:00 PM)\n"
                + "3. [D][ ] test (by: January 1 2021 2:00 PM)";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void findTask_taskExists_success() throws OwenException {
        TaskList taskList = new TaskList();
        taskList = taskList.addTask("todo apple");
        taskList = taskList.addTask("event orange /at 1/1/2021 1400 - 01/01/2021 1600");
        taskList = taskList.addTask("deadline app /by 1/1/2021 1400");

        String expected = "1. [T][ ] apple\n"
                + "3. [D][ ] app (by: January 1 2021 2:00 PM)";
        assertEquals(expected, taskList.findTask("app"));
    }
}
