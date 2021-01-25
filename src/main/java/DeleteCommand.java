public class DeleteCommand extends Command {
    static final String COMMAND_WORD = "delete";
    private int position;

    public DeleteCommand(TaskList taskList, Ui ui, Storage storage, int position) {
        super(taskList, ui, storage);
        this.position = position;
    }

    @Override
    public void execute() throws IndexOutOfBoundsException {
        System.out.println("Noted. I've removed this task:");
        this.taskList.printTask(this.position);
        this.taskList.deleteTask(this.position);
        this.taskList.printNumTasksInList();
    }
}
