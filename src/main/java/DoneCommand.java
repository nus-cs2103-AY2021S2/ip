public class DoneCommand extends Command {
    static final String COMMAND_WORD = "done";
    private int position;

    public DoneCommand(TaskList taskList, Ui ui, Storage storage, int position) {
        super(taskList, ui, storage);
        this.position = position;
    }

    @Override
    public void execute() {
        this.taskList.setTaskDone(this.position);
        System.out.println("Nice! I've marked this task as done:");
        this.taskList.printTask(this.position);
    }
}
