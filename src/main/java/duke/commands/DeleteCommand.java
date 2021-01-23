package duke.commands;

import duke.exceptions.*;

import duke.tasks.TaskList;
import duke.tasks.Task;

import duke.ui.Ui;

import duke.storage.Storage;

import java.io.IOException;

public class DeleteCommand extends Command {
    private String[] checkCommands;
    public DeleteCommand(String[] checkCommands) {
        this.checkCommands = checkCommands;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws
            DukeException, IOException {
        if (checkCommands.length == 1 || !isNumber(checkCommands[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(checkCommands[1]);
        if (num > 0 && num <= tasks.size()) {
            Task deleted = tasks.get(num - 1);
            tasks.remove(num - 1);
            ui.showDeleteTask(tasks, deleted);
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
