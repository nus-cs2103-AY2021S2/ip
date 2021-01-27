package Duke.command;

import Duke.ui.Ui;
public class ListCommand extends Command{
	public ListCommand() {
		super("", "", "", command -> {
			Ui.LISTING();
			return false;
		});
	}
}
