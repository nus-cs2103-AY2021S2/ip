package commands;

import tasks.TaskList;

import ui.Ui;

import storage.Storage;

public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
