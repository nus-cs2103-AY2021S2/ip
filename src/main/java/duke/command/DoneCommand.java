package duke.command;

import duke.exceptions.DukeException;
import duke.task.Task;


public class DoneCommand extends Command {


	public DoneCommand(String task, String date) {
		super("done", task, date, command -> handleDone(task));
	}


	private static Boolean handleDone(String task) {
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
