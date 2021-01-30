package duke.command;

import duke.task.Deadlines;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.Ui;


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



	private static Boolean handleToDo(String task) {
		if (!task.equals("")) {
			Todo todo = new Todo(task);
			System.out.println(Ui.biggerBox(todo));
		}
		return false;

	}


	private static Boolean handleDeadline(String task, String date) {

		if (!date.equals("")) {
			Deadlines deadlines = new Deadlines(task, date);
			System.out.println(Ui.biggerBox(deadlines));
		}

		return false;
	}



	private static Boolean handleEvent(String task, String date) {
		if (!task.equals("") && !date.equals("")) {
			Event event = new Event(task, date);
			System.out.println(Ui.biggerBox(event));

		}
		return false;
	}
}
