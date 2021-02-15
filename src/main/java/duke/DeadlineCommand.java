package duke;

import java.time.format.DateTimeParseException;

/**
 * Specifies the command for deadline command type.
 */
public class DeadlineCommand extends Command {
    String by;

    /**
     * Initialises DeadlineCommand object.
     * @param description the description of the deadline task.
     * @param by the due date of the deadline task.
     */
    public DeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
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
        try {
            Deadline deadline = new Deadline(description, by);
            taskList.addDeadlineTask(deadline);
            storage.writeToFile(taskList.getList());
            return ui.showTaskAdded(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date entered! Please type in valid yyyy-mm-dd format!");
        }
    }
}
