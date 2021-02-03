package duke.command;

import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;
import duke.util.Ui;

public class DoneCommand extends Command {
    public DoneCommand() {}

    /**
     * Process command done given by user.
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
            currTask = currTask.doTask();
            taskList.set(index - 1, currTask);
            storage.saveTasks(taskList);
            return ui.getDoneSuccess(currTask);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return ui.getDoneFail();
        }
    }
}
