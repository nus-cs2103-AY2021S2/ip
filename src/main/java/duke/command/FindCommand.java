package duke.command;

import duke.exception.DescriptionMissingException;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    protected String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String keyword = fullCommand.substring(4).strip();
        if (keyword.equals("")) {
            throw new DescriptionMissingException("Argument missing!");
        }
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task thisTask = tasks.get(i);
            String thisName = thisTask.getName();
            if (thisName.contains(keyword)) {
                matchingTasks.add(thisTask);
            }
        }
        ui.listMatchingTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
