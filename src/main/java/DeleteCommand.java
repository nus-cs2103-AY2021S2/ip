public class DeleteCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public DeleteCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] strArr = input.split(" ");
        if(strArr.length == 1) {
            throw new DukeException("☹ OOPS!!! I don't know which task to delete.");
        }
        int idx = Integer.parseInt(strArr[1]) - 1;
        Task t = tasks.removeTask(idx);
        storage.removeDataInFile(idx + 1, tasks.getSize());
        System.out.println("Noted. I've removed this task:\n" + t + "\nNow you have "
                + tasks.getSize() + " tasks in the list.");

    }
}
