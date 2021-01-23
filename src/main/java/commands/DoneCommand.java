package commands;

import storage.Storage;

import ui.Ui;

import exceptions.*;

import tasks.TaskList;

import java.io.IOException;

public class DoneCommand extends Command {
    private String[] checkCommands;
    public DoneCommand(String[] checkCommands) {
        this.checkCommands = checkCommands;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (checkCommands.length == 1 || !isNumber(checkCommands[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(checkCommands[1]);
        if (num > 0 && num <= tasks.size()) {
            tasks.get(num - 1).markAsDone();
            ui.showDoneTask(tasks, num);

        } else {
            throw new TaskNotFoundException();
        }
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private boolean isNumber(String checkString) {
        try {
            Integer.parseInt(checkString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
