package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindTasksCommand extends Command {

    private static final Boolean toExit = false;

    private String keyword;

    /**
     * Initialises find tasks command by specifying
     * filter keyword.
     *
     * @param keyword Keyword used to filter the task list.
     */
    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Filters the task list to find tasks that
     * matches a certain keyword.
     *
     * @param tasks Core TaskList object
     * @param ui Core Ui object
     * @param storage Core Storage object
     * @return Command execution response.
     * @throws DukeException If tasks cannot be found.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> x = tasks.findTasks(this.keyword);
        String msg = ui.getTaskList(x);
        return new CommandResponse(msg, FindTasksCommand.toExit);
    }
}
