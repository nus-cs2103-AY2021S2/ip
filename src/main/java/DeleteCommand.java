public class DeleteCommand extends Command{
    private int option;

    public DeleteCommand(int option) {
        this.option = option;
    }

    public void excute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(ui, this.option);
    }
}
