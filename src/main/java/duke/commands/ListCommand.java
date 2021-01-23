package duke.commands;

import duke.tasks.TaskList;

import duke.ui.Ui;

import duke.storage.Storage;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showEmptyList();
        } else {
            ui.showTasks(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
