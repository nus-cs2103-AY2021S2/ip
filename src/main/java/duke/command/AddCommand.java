package duke.command;

import duke.task.*;
import duke.ui.Ui;



/**
 * Sub-class of Command that represents and execute the todo, deadline and event instructions of user.
 */
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
	 * @param task name of the user task.
	 */
	private static Boolean handleToDo(String task) {
		if (!task.equals("")) {
			Todo todo = new Todo(task);
			TaskList.addTask(todo);
			System.out.println(Ui.biggerBox(todo));
		}
		return false;

	}


	private static Boolean handleDeadline(String task, String date) {

		if (!date.equals("")) {
			Deadlines deadlines = new Deadlines(task, date);
			TaskList.addTask(deadlines);
			System.out.println(Ui.biggerBox(deadlines));
		}

		return false;
	}



	private static Boolean handleEvent(String task, String date) {
		if (!task.equals("") && !date.equals("")) {
			Event event = new Event(task, date);
			TaskList.addTask(event);
			System.out.println(Ui.biggerBox(event));

		}
		return false;
	}
}
