package duke;

import java.time.format.DateTimeParseException;

/**
 * Specifies the command for event command type.
 */
public class EventCommand extends Command {
    private String at;
    private String description;

    /**
     * Initialises EventCommand object and parses description to description and at.
     *
     * @param description the entire description of the task.
     */
    public EventCommand(String description) throws DukeException {
        String[] descriptionArr = description.split("(?i)/at ");
        try {
            this.description = descriptionArr[0];
            this.at = descriptionArr[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide an 'at' date! E.g. /at 2021-01-01");
        }
    }

    /**
     * Executes the command by adding the event task to the existing taskList,
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
            Event event = new Event(description, at);
            taskList.addEventTask(event);
            storage.writeToFile(taskList.getList());
            return ui.showTaskAdded(event);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date entered! Please type in valid yyyy-mm-dd format!");
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
