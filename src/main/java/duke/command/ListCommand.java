package duke.command;

import duke.task.TaskList;


public class ListCommand extends Command{
	public ListCommand() {
		super("", "", "", command -> {
			TaskList.listing();
			return false;
		});
	}
}
