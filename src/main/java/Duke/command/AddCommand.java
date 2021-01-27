package Duke.command;

import Duke.task.Todo;
import Duke.task.Deadlines;
import Duke.task.Event;
import Duke.ui.Ui;
import Duke.exceptions.DukeException;

public class AddCommand extends Command{
	public AddCommand(String instruction, String task, String date) {
		super(instruction, task, date, command -> {
			if (instruction.equals("todo")) {
				return handleToDo(task);
			} else if (instruction.equals("deadline")) {
				return handleDeadline(task, date);
			} else {
				return handleEvent(task, date);
			}
		});
	}


	/**
	 * handle todo command and create a todo task if task is not empty.
	 *
	 * @param task name of the user task.
	 */
	private static final Boolean handleToDo(String task) {
		if (!task.equals("")) {
			Todo todo = new Todo(task);
			System.out.println(Ui.biggerBox(todo));
		} else {
			DukeException.emptyTaskException();
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
	private static final Boolean handleEvent(String task, String date) {
		if (task.equals("")) {
			DukeException.emptyTaskException();
		} else {
			if (date.equals("")) {
				DukeException.missingDateErrorException();
			} else {
				Event event = new Event(task, date);
				System.out.println(Ui.biggerBox(event));
			}
		}
		return false;
	}
}
