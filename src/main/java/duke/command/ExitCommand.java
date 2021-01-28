package duke.command;

import duke.exceptions.DukeException;
import duke.ui.Ui;

public class ExitCommand extends Command {
	public ExitCommand(String task, String date) {
		super("bye", task, date, command -> {
			try {
				return handleBye(task, date);
			} catch (DukeException e) {
				DukeException.commandErrorException();
			}
			return false;
		});
	}

	/**
	 * handle bye command by returning true or false.
	 *
	 * @param task name of the user task.
	 * @param date date of the task that user key in.
	 * @return a boolean of whether to exit the program.
	 */
	private static final boolean handleBye(String task, String date) throws DukeException {
		if (!task.equals("") || !task.equals("")) {
			throw new DukeException(Ui.COMMANDERROR);
		} else {
			return true;
		}
	}

}
