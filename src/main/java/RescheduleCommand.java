/**
 * Represents a command to reschedule/snooze a certain task in the task list
 * to another specified date
 */
public class RescheduleCommand extends Command {
    /**
     * Constructor that takes in two parameters, <code>fullCommand</code> and <code>typeOfCommand</code>.
     * @param fullCommand the full user input
     * @param typeOfCommand the type of command to be executed, in this case should be "event" or "todo"
     */
    public RescheduleCommand(String fullCommand, String typeOfCommand) {
        super(fullCommand, typeOfCommand);
    }

    /**
     * Reschedules a certain task within the task list
     * @param tasks the task list containing the task to be rescheduled
     * @return a response to be displayed to the user after rescheduling the task in the
     * task list
     * @throws DukeException if user did not specify a task to reschedule and/or the date to
     * reschedule it to
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response;
        String[] splitInputs = this.fullCommand.split(" ");
        if (splitInputs.length == 1) {
            throw new DukeException("Ooh lah lah! Please enter a task to reschedule and the date to "
                    + "reschedule it to!");
        }
        if (splitInputs.length == 2) {
            throw new DukeException("Ooh lah lah! Please enter a date to reschedule the task to!");
        }
        if (splitInputs.length == 3) {
            throw new DukeException("Ooh lah lah! Please enter the timing to reschedule it to!");
        }
        int taskNo = Integer.parseInt(splitInputs[1]) - 1;
        String newDateAndTime = fullCommand.substring(13);
        Task rescheduledTask = tasks.rescheduleTask(tasks, taskNo, newDateAndTime);
        response = "Oui oui! I've rescheduled the following task: \n"
                + rescheduledTask.toString();
        return response;
    }
}
