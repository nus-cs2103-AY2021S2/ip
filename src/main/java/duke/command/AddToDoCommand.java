package duke.command;

import duke.exception.NameDuplicateException;
import duke.util.*;
import duke.exception.NoMeaningException;

public class AddToDoCommand extends Command {
    public AddToDoCommand() {}

    /**
     * Process command todo given by user.
     * @param storage The storage to store result
     *        taskList TaskList associated to the current Duke
     *        ui The UI we use to print stuff
     *        command Sentences entered by the user
     */
    public String execute(Storage storage, TaskList taskList, Ui ui, String command) {
        try {
            String commandContent = command.substring(5);
            if (taskList.findExact(commandContent).size() == 0) {
                ToDo todo = new ToDo(commandContent);
                taskList.add(todo);
                storage.saveTasks(taskList);
                return ui.getTaskFinally(todo, taskList.size());
            } else {
                storage.saveTasks(taskList);
                throw new NameDuplicateException("Daddy, looks like you have already add this task");
            }

        } catch (StringIndexOutOfBoundsException e) {
            return ui.getTaskFail(new NoMeaningException(
                    "☹ OOPS!!! The description of a todo cannot be empty.")
            );
        } catch (NameDuplicateException e) {
            return ui.getTaskFail(e);
        }
    }
}
