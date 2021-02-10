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

        ui.getWelcomeResponse();
        ui.getByeResponse();
        ui.getListResponse(this.tasks);
        ui.getDoneResponse(this.dummyTask, 2);
        ui.getDeleteResponse(this.dummyTask, 2);
        ui.getFindResponse(this.tasks, "dummy keyword");
        ui.getAddTaskResponse(this.tasks, this.dummyTask);
    }
}
