package duke.command;

import duke.*;

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

    public abstract void execute(Storage storage, Ui ui, TaskList taskList);

    public boolean isExit() {
        return isExit;
    }
}
