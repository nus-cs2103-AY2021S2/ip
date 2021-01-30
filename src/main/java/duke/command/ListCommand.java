package duke.command;


/**
 * Sub-class of Command that represents and execute the "list" instruction of user.
 */

import duke.task.TaskList;


public class ListCommand extends Command{
	public ListCommand() {
		super("", "", "", command -> {
			TaskList.listing();
			return false;
		});
	}
}
