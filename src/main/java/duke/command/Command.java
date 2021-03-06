package duke.command;

import duke.exception.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {
    private String fullCommand;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
