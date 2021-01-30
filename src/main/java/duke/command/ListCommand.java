package duke.command;

import duke.ui.Ui;


public class ListCommand extends Command{
	public ListCommand() {
		super("", "", "", command -> {
			Ui.LISTING();
			return false;
		});
	}
}
