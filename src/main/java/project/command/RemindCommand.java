package project.command;

import project.common.PrintedText;
import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

public class RemindCommand extends Command {
    public RemindCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        if (taskList.hasTasks()) {
            TaskList upcoming = taskList.getUpcomingTasks();
            return ui.showReminder(upcoming);
        } else {
            return PrintedText.EMPTY_TASKLIST_ERROR.toString();
        }
    }
}
