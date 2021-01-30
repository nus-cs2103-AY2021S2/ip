package duke.command;

import duke.exceptions.DukeException;
import duke.task.Task;

<<<<<<< HEAD
/**
 * Sub-class of Command that represents and execute the done instruction of user.
 */
public class DoneCommand extends Command{
	public DoneCommand(String task, String date) {
		super("done", task, date, command -> {
			return handleDone(task);
=======
>>>>>>> branch-A-CodingStandard

public class DoneCommand extends Command {


	public DoneCommand(String task, String date) {
		super("done", task, date, command -> handleDone(task));
	}

<<<<<<< HEAD
	/**
	 * handle done command by marking the task as done.
	 * @param task name of the user task.
	 */
	private static final Boolean handleDone(String task) {
=======

	private static Boolean handleDone(String task) {
>>>>>>> branch-A-CodingStandard
		if (task.length() > 0) {
			try {
				int num = Integer.parseInt(task);
				Task.done(num);
			} catch (NumberFormatException e) {
				DukeException.NumberFormatException();
			}
		}
		return false;
	}


}
