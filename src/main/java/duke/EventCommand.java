package duke;

import java.time.format.DateTimeParseException;

/**
 * Specifies the command for event command type.
 */
public class EventCommand extends Command {
    private String at;
    private String description;

    /**
     * Initialises EventCommand object.
     *
     * @param description the description of the task.
     * @param at the date of the task, in the format YYYY-MM-DD
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
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
        try {
            Event event = new Event(description, at);
            taskList.addEventTask(event);
            storage.writeToFile(taskList.getList());
            return ui.showTaskAdded(event);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date entered! Please type in valid yyyy-mm-dd format!");
        }
    }
}
