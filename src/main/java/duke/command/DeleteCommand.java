package duke.command;


import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * Sub-class of command that only represents and executes a delete instruction of a user.
 */
public class DeleteCommand extends Command{
	public DeleteCommand(String task, String date) {
		super("delete", task, date, command -> {
			return handleDelete(task, date);

		});
	}


	/**
	 * handle delete command key in by user by removing the task from the list if there is any.
	 * @param task user task in String.
	 * @param date date of the task.
	 */
	private static final Boolean handleDelete(String task, String date) {
		if (task.length() > 0 && date.equals("")) {
			try {
				int num = Integer.parseInt(task);
				Task.delete(num);
			} catch (NumberFormatException e) {
				DukeException.NumberFormatException();
			}
		}

		return false;
	}
}
