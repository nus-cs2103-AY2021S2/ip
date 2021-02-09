package duke.command;

import duke.task.TaskList;

/**
 * Sub-class of Command to represents and execute the find instruction.
 */
public class FindCommand extends Command {

    /**
     * Construct a find command object to execute findTasks operation.
     * @param task user task.
     * @param date date of the user task to be done.
     */
    public FindCommand(String task, String date) {
        super("find", task, date, false, command -> {
            String searchResult = TaskList.findTasks(task);
            System.out.println(searchResult);
            return searchResult;
        });
    }


}
