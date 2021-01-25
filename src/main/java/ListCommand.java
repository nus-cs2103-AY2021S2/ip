public class ListCommand extends Command {
    static final String COMMAND_WORD = "list";

    public ListCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public void execute() {
        this.taskList.printList();
    }
}
