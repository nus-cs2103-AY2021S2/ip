package duke.command;

import duke.task.TaskList;

/**
 * Sub-class of Command to represents and executes the find instruction.
 */
public class FindCommand extends Command {
    private static final String NO_DATE = "";

    /**
     * Constructs a find command object that finds all relevant tasks with the given task.
     * @param task task user want to search for.
     */
    public FindCommand(String task) {
        super("find", task, NO_DATE, false, command -> {
            String searchResult = TaskList.findTasks(task);
            System.out.println(searchResult);
            return searchResult;
        });
    }


}
