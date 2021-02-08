package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import org.junit.jupiter.api.Test;

public class TestUi {
    private final TaskList tasks = new TaskList();
    private final Task dummyTask = new Task("task");

    @Test
    public void testPrinter() {
        Ui ui = new Ui();

        ui.showWelcome();
        ui.handleBye();
        ui.handleList(this.tasks);
        ui.handleDone(this.dummyTask);
        ui.handleDelete(this.dummyTask);
        ui.handleFind(this.tasks, "dummy keyword");
        ui.handleAddTask(this.tasks, this.dummyTask);
    }
}