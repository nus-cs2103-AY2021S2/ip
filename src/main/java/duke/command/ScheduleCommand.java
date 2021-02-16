package duke.command;

import java.util.ArrayList;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;
import duke.model.Task;

/**
 * The ScheduleCommand class denotes a schedule command to the RoboBot chat bot.
 */
public class ScheduleCommand extends Command {
    /**
     * Construct a ScheduleCommand.
     * @param taskDescription      The description of the task.
     */
    public ScheduleCommand(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Executing the command
     * @param taskList The list of recorded tasks.
     * @param ui       The user interface.
     * @param storage  The list of recorded user inputs data.
     * @return         The message replied by RoboBot chat bot.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ArrayList<Task> scheduledTasks = taskList.filterSchedule(taskDescription);
            return ui.scheduleCommandInteraction(scheduledTasks);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
