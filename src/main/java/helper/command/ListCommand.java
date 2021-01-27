package helper.command;

import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.Task;

import java.util.List;

public class ListCommand extends Command {


    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.dukePrint(tasks.getTaskList());
        } catch (Exception e) {
            ui.dukePrint("Nothing to list...");
        }
    }
}
