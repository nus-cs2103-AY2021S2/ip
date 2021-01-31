package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.task.Task;


public class FindCommand extends Command {

    public FindCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    public String execute(TaskManager taskManager, Ui ui, Storage storage) {
        String keyword = parsedCommand[1];
        ArrayList<Task> list = taskManager.retrieveMatchingTasks(keyword);
        return ui.showMatchingTasks(list);
    }
}
