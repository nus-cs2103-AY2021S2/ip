package duke.command;

import duke.exception.NoMeaningException;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.List;

public class FindCommand extends Command{
    public FindCommand() {}

    /**
     * Process command find given by user.
     * @param storage The storage to store result
     *        taskList TaskList associated to the current Duke
     *        ui The UI we use to print stuff
     *        command Sentences entered by the user
     */
    public String execute(Storage storage, TaskList taskList, Ui ui, String command) {
        try {
            String toSearch = command.substring(5);
            List<Task> searchedTaskList = taskList.find(toSearch);
            return ui.getFindSuccess(searchedTaskList);
        } catch (StringIndexOutOfBoundsException e) {
            return ui.getFindFail(new NoMeaningException(
                    "☹ OOPS!!! The description of a find cannot be empty."));
        }
    }
}
