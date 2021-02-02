package duke.commands;

import duke.Ui;
import duke.tasks.TaskList;

/**
 * Abstract class for all commands
 */
public abstract class Command {
    protected CommandType type;
    protected Ui ui;

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
