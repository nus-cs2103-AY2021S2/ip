package duke.ui;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;
import duke.tasks.TaskList;


public class TestUi {
    private final TaskList tasks = new TaskList();
    private final Task dummyTask = new Task("task");

    /**
     * Test whether the <code>Ui</code> class is able to print responses without errors.
     */
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
