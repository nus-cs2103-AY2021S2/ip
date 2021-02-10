package duke.command;

import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand() {}

    /**
     * Process command delete given by user.
     * @param storage The storage to store result
     *        taskList TaskList associated to the current Duke
     *        ui The UI we use to print stuff
     *        command Sentences entered by the user
     */
    public String execute(Storage storage, TaskList taskList, Ui ui, String command) {
        String[] splitCommand = command.split(" ");
        int index = Integer.parseInt(splitCommand[1]);
        try {
            Task currTask = taskList.get(index - 1);
            taskList.remove(index - 1);
            storage.saveTasks(taskList);
            return ui.getDeleteSuccess(currTask, taskList.size());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return ui.getDeleteFail();
        }
    }
}
