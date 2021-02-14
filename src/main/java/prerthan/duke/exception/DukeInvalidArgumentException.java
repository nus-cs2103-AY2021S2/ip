package prerthan.duke.exception;

import prerthan.duke.command.Command.CommandName;

/**
 * InvalidArgumentException
 */
public class DukeInvalidArgumentException extends DukeException
{
	private static final long serialVersionUID = 5221851007504557801L;

	private CommandName cmdName;
	private String argumentString;

	public DukeInvalidArgumentException(String message,
										String thrownBy)
	{
		super(message, thrownBy);
		this.argumentString = String.join(" ", arguments);
		this.cmdName = command;
	}

	@Override public String toString()
	{
		return String.format("%s%nCommand: %s%nArguments: %s%nThrown by: %s%n", this.getMessage(),
				this.cmdName, this.argumentString, this.thrownBy);
	}
}