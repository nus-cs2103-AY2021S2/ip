/**
 * Represents a command to add a task(ToDo/Deadline/Event) to
 * the existing task list.
 */

public class AddCommand extends Command {
    /**
     * Constructor that takes in two parameters, <code>fullCommand</code> and <code>action</code>,
     * where <code>fullCommand</code> is the full input entered by the user, and
     * <code>action</code> is the type of task (<code>ToDo</code>/<code>Deadline</code>/<code>Event</code>)
     * that is to be added
     * @param fullCommand the full user input
     * @param action the type of task to be added
     */
    public AddCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    /**
     * Adds a task (<code>ToDo</code>/<code>Deadline</code>/<code>Event</code>)
     * into the given task list.
     * @param tasks a task list that the task in question is to be added into
     * @throws DukeException If the given command does not correspond to adding
     * either a <code>Todo</code>, <code>Deadline</code> or <code>Event</code>
     */

    @Override
    public void execute(TaskList tasks) throws DukeException {
        Ui.printLine();
        String[] inputs = this.fullCommand.split(" ");
        String action = inputs[0];
        if (action.equals("todo")) {
            if (inputs.length == 1) {
                throw new DukeException("OOPS! The description of a todo cannot be empty.");
            }
            Ui.print(Aligner.align("Sure! I've added this todo:"));
            String toPrint = tasks.addByCommand(fullCommand, "todo");
            Ui.print(Aligner.align(toPrint));
            Ui.print(Aligner.align("Now you have a whopping " + tasks.size() + " task(s) in the list."));
        } else if (action.equals("deadline")) { //input format yyyy-mm-dd tttt
            if (inputs.length == 1) {
                throw new DukeException("OOPS! The description of a deadline cannot be empty.");
            }
            Ui.print(Aligner.align("Sure! I've added this deadline:"));
            String toPrint = tasks.addByCommand(fullCommand, "deadline");
            Ui.print(Aligner.align(toPrint));
            Ui.print(Aligner.align("Now you have a whopping " + tasks.size() + " task(s) in the list."));
        } else if (action.equals("event")) { //input format: yyyy-mm-dd tttt-tttt
            if (inputs.length == 1) {
                throw new DukeException("OOPS! The description of an event cannot be empty.");
            }
            Ui.print(Aligner.align("Sure! I've added this event:"));
            String toPrint = tasks.addByCommand(fullCommand, "event");
            Ui.print(Aligner.align(toPrint));
            Ui.print(Aligner.align("Now you have a whopping " + tasks.size() + " task(s) in the list."));
        } else {
            throw new DukeException("OOPS! Sorry, I have no idea what that means :(");
        }
        Ui.printLine();
    }
}
