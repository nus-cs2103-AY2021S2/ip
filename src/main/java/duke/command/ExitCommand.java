package duke.command;


public class ExitCommand extends Command {
	public ExitCommand(String task, String date) {
		super("bye", task, date, command -> handleBye(task, date));
	}


	private static boolean handleBye(String task, String date) {
		return task.equals("") && date.equals("");
	}

}
