package duke.command;



import duke.task.TaskList;

/**
 * Sub-class of Command that represents and execute the "list" instruction of user.
 */
public class ListCommand extends Command {
    /**
     * Create a ListCommand object to carry out listing of tasks.
     */
    public ListCommand() {
        super("", "", "", false, command -> {
           String result = TaskList.listTasks();
           return result;
        });
    }
}
