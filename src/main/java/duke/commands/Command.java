package duke.commands;

import duke.tasks.TaskList;

import duke.Ui;

/**
 * Abstract class for all commands
 */
public abstract class Command {
    CommandType type;
    Ui ui;

    /**
     * Command constructor
     * @param type Type of command
     */
    public Command(CommandType type) {
        this.type = type;
        this.ui = new Ui();
    }

    /**
     * Gets type of command
     * @return Command type
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Abstract method for executing a command
     * @param list List of tasks
     */
    public abstract void execute(TaskList list);

}
