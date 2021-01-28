package duke.command;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand extends Command{
	public DoneCommand(String task, String date) {
		super("done", task, date, command -> {
			try {
				return handleDone(task);
			} catch (DukeException e) {
				DukeException.argumentErrorException();
			}
			return false;
		});
	}

	/**
	 * handle done command by marking the task as done.
	 *
	 * @param task name of the user task.
	 */
	private static final Boolean handleDone(String task) throws DukeException {
		if (task.length() > 0) {
			try {
				int num = Integer.parseInt(task);
				Task.done(num);
			} catch (NumberFormatException e) {
				DukeException.NumberFormatException();
			}
		} else {
			throw new DukeException(Ui.TOOMANYARGUMENTS);
		}
		return false;
	}


}
