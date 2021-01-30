package sharadhr.duke;

import java.util.Optional;

import javafx.application.Platform;
import sharadhr.duke.command.Command;
import sharadhr.duke.exception.DukeEmptyDetailException;
import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.exception.DukeInvalidCommandException;
import sharadhr.duke.exception.DukeInvalidDateException;
import sharadhr.duke.io.Input;
import sharadhr.duke.io.Output;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 *
 */
public class Duke
{
	public static TaskList tasks;
	public static Input input;
	public static Output output;
	public static Storage fileRW;
	
	/**
	 * Runs the main program loop.
	 *
	 * @return {@code false} when the user says 'bye'; otherwise, never returns.
	 */
	public static boolean programLoop()
	{
		boolean isExit = false;
		while (!isExit)
		{
			try
			{
				Optional<Command> possibleCommand = input.nextLine().getCommand();
				if (!possibleCommand.isPresent())
				{
					output.say("Input cannot be empty; please enter a command.");
					continue;
				}
				possibleCommand.get().execute(tasks, fileRW, output);
				isExit = possibleCommand.get().willTerminate();
			}
			catch (DukeInvalidArgumentException | DukeInvalidCommandException | DukeEmptyDetailException
					| DukeInvalidDateException e)
			{
				output.sayError(e);
			}
		}
		return isExit;
	}
	
	/**
	 * Cleans up objects and quits the program by calling {@link System#exit(int)}.
	 */
	public static void exit()
	{
		output.sayGoodBye();
		
		input.close();
		output.close();
		Platform.exit();
		System.exit(0);
	}
	
	public static void main(String[] args)
	{
		// Initialises file and UI I/O
		fileRW = new Storage("data", "duke.txt");
		input = new Input();
		output = new Output();
		
		// Greets the user.
		output.sayHello();
		
		// Creates the task list
		tasks = fileRW.loadFromFile();
		
		if (programLoop())
		{
			exit();
		}
	}

	public String getResponse(String input)
	{
		return "";
	}
}
