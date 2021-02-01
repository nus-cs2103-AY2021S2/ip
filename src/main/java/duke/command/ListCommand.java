package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

	@Override
	public boolean isExit() {
		return false;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		if (tasks.size() == 0) {
			System.out.println("\tHmm... You do not have any tasks!");
		} else {
			System.out.println("\tHere are the tasks in your list:");
		}
		tasks.list();
	}

}
