package helper.command;


import helper.Storage;
import helper.TaskList;
import helper.Ui;

/**
 * Commands for done
 */
public class DoneCommand extends Command {

    private String doneString;

    public DoneCommand(String s) {
        doneString = s;
    }

    /**
     * Set the task as done
     * @param tasks
     * @param ui
     * @param storage
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String stringToReturn = "";
        try {
            int index = Integer.parseInt(doneString) - 1;
            tasks.get(index).setDone(true);
            stringToReturn = ("Good! We finished task: " + doneString + ". " + tasks.get(index));
            storage.saveFile(tasks);
        } catch (Exception e) {
            stringToReturn = ("Please use a good index. Our list starts at 1..."
                    + " and ends at " + (tasks.size() + 1));
        }
        return stringToReturn;
    }

}
