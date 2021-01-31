package helper.command;

import helper.Storage;
import helper.TaskList;
import helper.Ui;

public class DeleteCommand extends Command {

    private String deleteString;

    public DeleteCommand(String s) {
        deleteString = s;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(deleteString) - 1;
            ui.dukePrint("OK! We removed task: " + deleteString + ". " + tasks.get(index));
            tasks.remove(index);
            storage.saveFile(tasks);
        } catch (Exception e) {
            ui.dukePrint("Please use a good index. Our list starts at 1..." +
                    " and ends at " + (tasks.size() + 1));
        }
    }


}