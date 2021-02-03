package duke;

import duke.exception.DukeStorageException;
import duke.util.TaskList;
import duke.command.AddCommand;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.TaskStorage;
import duke.util.MessageFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    @Test
    void testAddCommand() {
        try {
            Task todo = new ToDo("hello world!");
            AddCommand add = new AddCommand(todo);
            TaskList tasks = new TaskList();
            add.execute(tasks, new MessageFormatter(), new TaskStorage("data/cmdTest.txt"));
            assertEquals(tasks.getTask(0).toString(), todo.toString());
        } catch (DukeStorageException e) {
            assertEquals(e.getMessage(), "");
        }
    }
}