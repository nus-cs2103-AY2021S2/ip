package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyListException;
import duke.exceptions.ExcessListKeywordException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

/**
 * A command class that gives the list of all the tasks in the taskList.
 */
public class ListCommand extends Command {
    public ListCommand(String description) {
        super(description);
    }

    /**
     * Returns the tasks in taskList
     *
     * @param taskList the list of tasks
     * @param ui the UI object
     * @param storage the storage object
     * @return String which is the list of tasks in taskList
     * @throws DukeException
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputList = description.trim().split(" ", 2);

        if (inputList.length > 1) {
            throw new ExcessListKeywordException();
        } else if (taskList.getSize() == 0) {
            throw new EmptyListException();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here is a list of your tasks, Master:");

        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append("\n" + String.valueOf(i + 1) + ". " + taskList.getTask(i).toString());
        }

        ui.formatAndPrintType(sb.toString());
        return sb.toString();
    }

    @Override
    public boolean isEndOfProgram() {
        return false;
    }
}
