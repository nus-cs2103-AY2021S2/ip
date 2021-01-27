package Duke.command;

import Duke.exceptions.DukeException;

public class ErrorCommand extends Command{
	public ErrorCommand() {
		super("", "", "", command -> {
			DukeException.commandErrorException();
			return false;
		});
	}
}
