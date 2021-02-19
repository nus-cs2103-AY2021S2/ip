package duke;

import java.time.format.DateTimeParseException;

/**
 * Specifies the command for deadline command type.
 */
public class DeadlineCommand extends Command {
    private String by;
    private String description;

    /**
     * Initialises DeadlineCommand object and parses description to description and by.
     *
     * @param description the entire description of the deadline task.
     */
    public DeadlineCommand(String description) throws DukeException {
        String[] descriptionArr = description.split("(?i)/by ");
        try {
            this.description = descriptionArr[0];
            this.by = descriptionArr[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a 'by' date! E.g. /by 2021-01-01");
        }
    }

    /**
     * Executes the command by adding the deadline task to the existing taskList,
     * writing the updated taskList into storage and responding with relevant message.
     *
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     * @return Ui of response to user request.
     * @throws DukeException if the date entered is invalid or in the wrong format.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        try {
            Deadline deadline = new Deadline(description, by);
            taskList.addDeadlineTask(deadline);
            storage.writeToFile(taskList.getList());
            return ui.showTaskAdded(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date entered! Please type in valid yyyy-mm-dd format!");
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
