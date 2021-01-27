package Duke.command;

import Duke.exceptions.DukeException;
public class ExitCommand extends Command {
	public ExitCommand(String task, String date) {
		super("bye", task, date, command -> {
			return handleBye(task, date);
		});
	}

	/**
	 * handle bye command by returning true or false.
	 *
	 * @param task name of the user task.
	 * @param date date of the task that user key in.
	 * @return a boolean of whether to exit the program.
	 */
	private static final boolean handleBye(String task, String date) {
		if (!task.equals("") || !task.equals("")) {
			DukeException.commandErrorException();
			return false;
		} else {
			return true;
		}
	}

}
