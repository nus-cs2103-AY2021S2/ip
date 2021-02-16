import java.io.IOException;

/**
 * Command to create an Event.
 */
public class SortCommand extends Command {

    /**
     * Executes the sort command and shows the list after it is sorted.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If missing description.
     * @throws DukeWrongInputException If user input is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException, DukeIoException {
        try {
            TaskList sortedList = taskList.sort();
            storage.save(sortedList.getTaskList());
            return ui.showTaskListSorted(sortedList);
        } catch (IOException e) {
            throw new DukeIoException("File error: Could not save.");
        }
    }
}
