import java.io.IOException;

/**
 * Command to create an Event.
 */
public class EventCommand extends Command {

    private String command;

    /**
     * Constructs an event command.
     *
     * @param command user command for creating event.
     */
    public EventCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the event command by creating a new event.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If event command is missing description.
     * @throws DukeWrongInputException If user input is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException, DukeIoException {
        try {
            String[] descriptionDeadlinePair = descriptionBuilder(command);
            String description = descriptionDeadlinePair[0];
            String eventTime = descriptionDeadlinePair[1];

            if (command.equals("event")) {
                throw new DukeMissingInputException("OOPS! The description of an event cannot be empty.");
            }
            Event newEvent = new Event(description, eventTime);
            taskList.add(newEvent);
            storage.save(taskList.getTaskList());
            return ui.showTaskAdded(newEvent);
        } catch (IOException e) {
            throw new DukeIoException("File error: Could not save.");
        }
    }

    /**
     * Builds the description and event time from user's event command.
     *
     * @param command user command.
     * @return a String array with task description at pos 0 and event time at pos 1.
     */
    public String[] descriptionBuilder(String command) {
        String[] commandArr = command.split(" ");
        String description = "";
        String eventTime = "";
        boolean hasFoundAt = false;
        for (int i = 1; i < commandArr.length; i++) {
            if (commandArr[i].equals("/at")) {
                hasFoundAt = true;
                continue;
            }
            if (hasFoundAt) {
                eventTime += (commandArr[i] + " ");
            } else {
                description += (commandArr[i] + " ");
            }
        }
        description = description.trim();
        eventTime = eventTime.trim();
        return new String[] {description, eventTime};
    }
}
