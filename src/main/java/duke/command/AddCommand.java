package duke.command;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;
import duke.model.Task;

/**
 * The AddCommand class denotes an add command to the Duke chat bot.
 */
public class AddCommand extends Command {
    private final String type;

    /**
     * Constructs an AddCommand.
     * @param type              The type of task to be added into the list.
     * @param taskDescription   The description of the task.
     */
    public AddCommand(String type, String taskDescription) {
        super(taskDescription);
        this.type = type;
    }

    /**
     * Executing the command
     * @param taskList The list of recorded tasks.
     * @param ui       The user interface.
     * @param storage  The list of recorded user inputs data.
     * @return         The message replied by Duke chat bot.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ArrayList<Task> tasks = taskList.addTask(type, taskDescription, storage);
            return ui.addCommandInteraction(tasks.get(tasks.size() - 1), tasks);
        } catch (DateTimeParseException e) {
            return "     The date time format is wrong. "
                    + "It supposed to be yyyy-MM-dd or yyyy/MM/dd";
        } catch (Exception e) {
            return "     " + e.getMessage();
        }
    }
}
