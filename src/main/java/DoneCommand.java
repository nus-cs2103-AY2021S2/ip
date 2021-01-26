public class DoneCommand {
    private int option;

    public DoneCommand(int option) {
        this.option = option;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        boolean isSuccess = taskList.markAsDone(this.option);
        if (isSuccess) {

        } else {

        }
    }
}
