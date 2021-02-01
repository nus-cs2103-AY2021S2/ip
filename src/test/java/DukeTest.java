import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.interaction.Parser;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.TodoTask;


public class DukeTest {
    @Test
    public void deadlineSerialisationTest() {
        DeadlineTask task = new DeadlineTask("test", LocalDateTime.parse("2020-11-01T10:15:30"));
        DeadlineTask newTask = DeadlineTask.deserialise(task.serialise());
        assertEquals(task.toString(), newTask.toString());
        task.markDone();
        assertNotEquals(task.toString(), newTask.toString());
    }

    @Test
    public void eventSerialisationTest() {
        EventTask task = new EventTask(
                "test",
                LocalDateTime.parse("2020-11-01T10:15:30"),
                LocalDateTime.parse("2020-11-01T10:16:30")
        );
        EventTask newTask = EventTask.deserialise(task.serialise());
        assertEquals(task.toString(), newTask.toString());
        task.markDone();
        assertNotEquals(task.toString(), newTask.toString());
    }

    @Test
    public void parserTest() {
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        parser.parseInput("todo a").execute(taskList);
        assertEquals(new TodoTask("a").serialise() + "\n", taskList.serialise());

        parser.parseInput("delete 1").execute(taskList);
        assertEquals("", taskList.serialise());
    }

}
