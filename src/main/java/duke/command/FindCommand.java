package duke.command;

import duke.tasks.TaskList;

/**
 * Finds all tasks containing the given description.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor.
     * @param keyword keyword of the task description
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the action of finding required tasks.
     * @return the information of the tasks containing the given keyword
     */
    @Override
    public String[] execute() {
        return TaskList.findTasks(keyword);
    }
}
