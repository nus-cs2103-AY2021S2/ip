package duke.command;


import duke.exceptions.DukeException;
import duke.task.TaskList;


public class DeleteCommand extends Command {

	public DeleteCommand(String task, String date) {
		super("delete", task, date, command -> handleDelete(task, date));
	}



	private static Boolean handleDelete(String task, String date) {
		if (task.length() > 0 && date.equals("")) {
			try {
				int num = Integer.parseInt(task);
				TaskList.delete(num);
			} catch (NumberFormatException e) {
				DukeException.NumberFormatException();
			}
		}

		return false;
	}
}
