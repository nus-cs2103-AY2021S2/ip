package duke.commands;

import duke.tasks.TaskList;

import duke.Ui;

public abstract class Command {
    CommandType type;
    Ui ui;

    public Command(CommandType type) {
        this.type = type;
        this.ui = new Ui();
    }

    public CommandType getType() {
        return type;
    }

    public abstract void excecute(TaskList list);

}
