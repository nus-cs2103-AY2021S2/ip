package helper.command;

import helper.Storage;
import helper.TaskList;
import helper.Ui;

/**
 * Commands for deletes
 */
public class DeleteCommand extends Command {

    private String deleteString;

    public DeleteCommand(String s) {
        deleteString = s;
    }

    /**
     * Delete the task from the tasklist
     * @param tasks
     * @param ui
     * @param storage
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String stringToReturn = "";
        try {
            int index = Integer.parseInt(deleteString) - 1;
            stringToReturn = ("OK! We removed task: " + deleteString + ". " + tasks.get(index));
            tasks.remove(index);
            storage.saveFile(tasks);

        } catch (Exception e) {
            stringToReturn = ("Please use a good index. Our list starts at 1..."
                    + " and ends at " + (tasks.size() + 1));
        }
        return stringToReturn;
    }


}
