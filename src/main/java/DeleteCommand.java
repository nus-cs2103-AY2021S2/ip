public class DeleteCommand extends Command {
    private int option;

    public DeleteCommand(int option) {
        this.option = option;
    }

    public String excute(TaskList taskList, Ui ui) {
        return taskList.deleteTask(option);
    }
}
