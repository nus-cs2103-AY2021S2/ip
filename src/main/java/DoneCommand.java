public class DoneCommand extends Command {
    private int option;

    public DoneCommand(int option) {
        this.option = option;
    }

    public String execute(TaskList taskList, Ui ui) {
        return taskList.markAsDone(this.option);
    }
}
