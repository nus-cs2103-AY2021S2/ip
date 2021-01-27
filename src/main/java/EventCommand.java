/**
 * Command to create an Event.
 */
public class EventCommand extends Command {

    private String command;

    /**
     * Constructor method.
     * @param command user command for creating event.
     */
    public EventCommand(String command) {
        this.command = command;
    }

    /**
     * Execute command for event command.
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If event command is missing description.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException {
        String description = "";
        String time = "";
        boolean foundAt = false;
        String [] commandArr = command.trim().split(" ");

        if (command.equals("event")) {
            throw new DukeMissingInputException("OOPS!!! The description of an event cannot be empty.");
        } else {
            for (int i = 1; i < commandArr.length; i++) {
                if (commandArr[i].equals("/at")){
                    foundAt = true;
                    continue;
                }
                if (foundAt) {
                    time += (commandArr[i] + " ");
                } else {
                    description += (commandArr[i] + " ");
                }
            }
        }
        time = time.trim();
        Event newEvent = new Event(description, time);
        taskList.add(newEvent);
        ui.showTaskAdded(newEvent);
    }

    /**
     * Indicates whether command is an exit command.
     * @return boolean value for whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}