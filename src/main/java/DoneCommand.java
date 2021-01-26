public class DoneCommand extends Command{
    private int option;

    public DoneCommand(int option) {
        this.option = option;
    }

    public void excute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(ui, this.option);
    }
}
