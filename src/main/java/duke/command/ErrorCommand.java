package duke.command;

public class ErrorCommand extends Command{
	public ErrorCommand() {
		super("", "", "", command -> {
			return false;
		});
	}
}
