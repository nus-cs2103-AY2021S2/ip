package Duke.command;

import Duke.task.Task;
import Duke.exceptions.DukeException;
public class DoneCommand extends Command{
	public DoneCommand(String task, String date) {
		super("done", task, date, command -> {
			return handleDone(task);
		});
	}

	/**
	 * handle done command by marking the task as done.
	 *
	 * @param task name of the user task.
	 */
	private static final Boolean handleDone(String task) {
		if (task.length() > 0) {
			try {
				int num = Integer.parseInt(task);
				Task.done(num);
			} catch (NumberFormatException e) {
				DukeException.NumberFormatException();
			}
		} else {
			DukeException.argumentErrorException();
		}
		return false;
	}


}
