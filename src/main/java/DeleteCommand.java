public class DeleteCommand implements Command {
	private final int index;

	public DeleteCommand(int index) {
		this.index = index;
	}

	@Override
	public boolean isExit() {
		return false;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		if (0 <= index && index < tasks.size()) {
			final Task removed = tasks.delete(index);
			System.out.println("\tNoted. I've removed this task: ");
			System.out.printf("\t%s\n", removed);
			System.out.printf("\tNow you have %d task%s in the list.\n", tasks.size(), tasks.size() == 1 ? "" : "s");
		} else {
			System.out.println("\tOops! The index is out of bound.");
		}
	}

}
