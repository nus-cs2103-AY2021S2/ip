package duke.commands;

import duke.tasks.TaskList;

/**
 * Command for listing task out
 */
public class ListCommand extends Command {
    /**
     * List command constructor
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Prints all tasks on list
     * @param list List of tasks
     */
    @Override
    public String execute(TaskList list) {
        return ui.printList(list);
    }
}
