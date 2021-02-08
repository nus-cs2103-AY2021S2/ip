package duke.command;

import java.util.ArrayList;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;
import duke.model.Task;

public class ScheduleCommand extends Command {
    public ScheduleCommand(String taskDescription) {
        super(taskDescription);
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ArrayList<Task> scheduledTasks = taskList.filterSchedule(taskDescription);
            return ui.scheduleCommandInteraction(scheduledTasks);
        } catch (Exception ex) {
            return "     " + ex.getMessage();
        }
    }
}
