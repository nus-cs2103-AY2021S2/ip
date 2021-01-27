public class ListCommand extends Command{
	ListCommand() {
		super("", "", "", command -> {
			Ui.LISTING();
			return false;
		});
	}
}
