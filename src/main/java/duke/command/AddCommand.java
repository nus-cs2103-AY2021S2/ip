package duke.command;

import duke.task.Todo;
import duke.task.Deadlines;
import duke.task.Event;
import duke.ui.Ui;
import duke.exceptions.DukeException;

public class AddCommand extends Command{
	public AddCommand(String instruction, String task, String date) {
		super(instruction, task, date, command -> {
			try {
				if (instruction.equals("todo")) {
					return handleToDo(task);
				} else if (instruction.equals("deadline")) {
					return handleDeadline(task, date);
				} else {
					return handleEvent(task, date);
				}
			} catch (DukeException e) {
				System.out.println(e);
			}
			return false;
		});
	}


	/**
	 * handle todo command and create a todo task if task is not empty.
	 *
	 * @param task name of the user task.
	 */
	private static final Boolean handleToDo(String task) throws DukeException {
		if (!task.equals("")) {
			Todo todo = new Todo(task);
			System.out.println(Ui.biggerBox(todo));
		} else {
			throw new DukeException(Ui.EMPTYTASK);

		}
		return false;

	}

	/**
	 * handle deadline command by creating deadline task if task and date are not empty.
	 *
	 * @param task name of the user task.
	 * @param date date of the task to be done.
	 */
	private static final Boolean handleDeadline(String task, String date) {
		if (task.equals("")) {
			DukeException.emptyTaskException();
		} else {
			if (date.equals("")) {
				DukeException.missingDateErrorException();
			} else {
				Deadlines deadlines = new Deadlines(task, date);
				System.out.println(Ui.biggerBox(deadlines));
			}
		}
		return false;
	}


	/**
	 * handle event command by creating event if task and date are not empty.
	 *
	 * @param task name of the user task.
	 * @param date date of the task to be done.
	 */
	private static final Boolean handleEvent(String task, String date) throws DukeException {
		if (task.equals("")) {
			throw new DukeException(Ui.EMPTYTASK);
		} else {
			if (date.equals("")) {
				throw new DukeException(Ui.MISSINGDATE);
			} else {
				Event event = new Event(task, date);
				System.out.println(Ui.biggerBox(event));
			}
		}
		return false;
	}
}
