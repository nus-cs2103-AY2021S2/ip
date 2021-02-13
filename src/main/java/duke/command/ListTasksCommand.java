package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class ListTasksCommand extends Command {

    private static final Boolean toExit = false;

    /**
     * Lists all the tasks currently being handled
     * by the Duke chat box.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @return Command execution response.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> x = tasks.getList();
        String msg = ui.getTaskList(x);
        return new CommandResponse(msg, ListTasksCommand.toExit);
    }
}
