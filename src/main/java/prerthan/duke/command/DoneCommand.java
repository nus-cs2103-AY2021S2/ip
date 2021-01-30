package sharadhr.duke.command;

import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.io.Output;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.Task;
import sharadhr.duke.task.TaskList;

/**
 * DoneCommand
 */
public class DoneCommand extends Command
{
	private int position;

	DoneCommand(String[] commandTokens, int position) throws DukeInvalidArgumentException
	{

		// if (this.argumentTokens.length != 1 || !this.argumentTokens[0].matches("\\d+")) {
		//     throw new DukeInvalidArgumentException(argument, this.commandName, this.getClass().getSimpleName());
		// }
		// this.commandName = CommandName.DONE;
		// this.position = position;
	}

	/**
	 * @param argumentTokens
	 * @throws DukeInvalidArgumentException
	 */
	public DoneCommand(String[] argumentTokens) throws DukeInvalidArgumentException
	{
		this(argumentTokens, Integer.parseInt(argumentTokens[1]));
	}

	@Override public void execute(TaskList tasks, Storage storage, Output output)
	{
		if (!(this.position >= 1) && this.position <= tasks.numberOfTasks())
		{
			Task toComplete = tasks.getTaskAtPosition(position);
			if (toComplete.markComplete())
			{
				output.sayTaskMarkedComplete(toComplete);
			}
		}
	}
}