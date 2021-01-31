package duke;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;
import duke.command.AddCommand;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    @Test
    void testAddCommand() {
        Task todo = new ToDo("hello world!");
        AddCommand add = new AddCommand(todo);
        TaskList tasks = new TaskList();
        add.execute(tasks, new Ui(), new TaskStorage("data/cmdTest.txt"));
        assertEquals(tasks.getTask(0).toString(), todo.toString());
    }
}