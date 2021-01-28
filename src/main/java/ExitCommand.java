import java.util.List;

public class ExitCommand implements Command {

	@Override
	public boolean isExit() {
		return true;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		final List<String> data = tasks.encode();
		storage.saveFile(data);

		System.out.println("\tBye. Hope to see you again soon!");
		ui.showLine();
	}

}
