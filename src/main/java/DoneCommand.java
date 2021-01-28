public class DoneCommand implements Command {
	private final int index;

	public DoneCommand(int index) {
		this.index = index;
	}

	@Override
	public boolean isExit() {
		return false;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		if (0 <= index && index < tasks.size()) {
			tasks.markAsDone(index);
			System.out.println("\tNice! I've marked this task as done:");
			System.out.printf("\t%s\n", tasks.getTaskDescription(index));
		} else {
			System.out.println("\tOops! The index is out of bound.");
		}
	}

}
