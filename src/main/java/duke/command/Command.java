package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    String arguments;
    boolean isExit;

    public Command(String arguments) {
        this.arguments = arguments;
        this.isExit = false;
    }

    public String getArguments() {
        return this.arguments;
    }

    public abstract void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException;

    public boolean isExit() {
        return isExit;
    }
}
