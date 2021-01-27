package duke.command;

import duke.exceptions.DukeException;

public class ErrorCommand extends Command{
	public ErrorCommand() {
		super("", "", "", command -> {
			DukeException.commandErrorException();
			return false;
		});
	}
}
