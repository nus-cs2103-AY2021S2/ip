package duke.commands;

import duke.tasks.TaskList;

/**
 * Command to quit Duke
 */
public class ByeCommand extends Command {
    /**
     * Exit command constructor
     */
    public ByeCommand() {
        super(CommandType.EXIT);
    }

    /**
     * Exits program
     * @param list List of tasks
     */
    @Override
    public String execute(TaskList list) {
        return ui.printGoodbye();
    }
}
