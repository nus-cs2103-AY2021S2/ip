/**
 * Represents a command to add a <code>Task</code> into
 * the existing task list.
 */

public class AddCommand extends Command {
    /**
     * Constructor that takes in two parameters, <code>fullCommand</code> and <code>typeOfCommand</code>.
     * @param fullCommand the full user input
     * @param typeOfCommand the type of command to be executed, in this case, the type
     *                      of task to be added
     */
    public AddCommand(String fullCommand, String typeOfCommand) {
        super(fullCommand, typeOfCommand);
    }

    /**
     * Adds a task into the given <code>TaskList</code>.
     * @param tasks the task list that the task in question is to be added into
     * @return a response to be displayed to the user after adding the task into the
     * task list
     * @throws DukeException if the user does not enter any input after the command,
     * or if the user enters an unknown command
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String[] splitInputs = this.fullCommand.split(" ");
        if (typeOfCommand.equals("todo")) {
            if (splitInputs.length == 1) {
                throw new DukeException("Ooh la la! The description of a todo cannot be empty.");
            }
            return executeToDo(tasks);
        } else if (typeOfCommand.equals("deadline")) {
            if (splitInputs.length == 1) {
                throw new DukeException("Ooh la la! The description of a deadline cannot be empty.");
            }
            return executeDeadline(tasks);
        } else if (typeOfCommand.equals("event")) {
            if (splitInputs.length == 1) {
                throw new DukeException("Ooh la la! The description of an event cannot be empty.");
            }
            return executeEvent(tasks);
        } else {
            throw new DukeException("Ooh la la! Pardon, I have no idea what that means :(");
        }
    }

    /**
     * Returns a String that is to be displayed to the user after adding a <code>ToDo</code> task
     * into the task list
     * @param tasks the task list that the task in question is to be added into
     * @return a String that is to be displayed to the user
     */

    public String executeToDo(TaskList tasks) {
        String response;
        String toPrint = tasks.addByCommand(fullCommand, "todo");
        response = "Oui oui! I've added this todo: \n" + toPrint + "\n" + "Now you have a whopping "
                + tasks.size() + " task(s) in the list.";
        return response;
    }

    /**
     * Returns a String that is to be displayed to the user after adding a <code>Deadline</code> task
     * into the task list
     * @param tasks the task list that the task in question is to be added into
     * @return a String that is to be displayed to the user
     */

    public String executeDeadline(TaskList tasks) {
        String response;
        String toPrint = tasks.addByCommand(fullCommand, "deadline");
        response = "Oui oui! I've added this deadline: \n" + toPrint + "\n" + "Now you have a whopping "
                + tasks.size() + " task(s) in the list.";
        return response;
    }

    /**
     * Returns a String that is to be displayed to the user after adding an <code>Event</code> task
     * into the task list
     * @param tasks the task list that the task in question is to be added into
     * @return a String that is to be displayed to the user
     */
    public String executeEvent(TaskList tasks) {
        String response;
        String toPrint = tasks.addByCommand(fullCommand, "event");
        response = "Oui oui! I've added this event: \n" + toPrint + "\n" + "Now you have a whopping "
                + tasks.size() + " task(s) in the list.";
        return response;
    }
}
