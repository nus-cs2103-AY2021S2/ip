public class ErrorCommand extends Command{
	ErrorCommand() {
		super("", "", "", command -> {
			DukeException.commandErrorException();
			return false;
		});
	}
}
