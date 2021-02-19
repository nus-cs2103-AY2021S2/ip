package duke.commands;

import duke.tasks.TaskList;

/**
 * Command for finding tasks with name containing given text
 */
public class FindCommand extends Command {
    private String text;

    /**
     * Find command constructor
     *
     * @param text Search text
     */
    public FindCommand(String text) {
        super(CommandType.FIND);
        this.text = text;
    }

    /**
     * Finds tasks with name containing search text
     *
     * @param list List of tasks
     */
    @Override
    public String execute(TaskList list) {
        return ui.printSearchResults(list.find(this.text));
    }
}
