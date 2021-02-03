public class DoneCommand extends Command {
    private int option;

    public DoneCommand(int option) {
        this.option = option;
    }

    public String excute(TaskList taskList) {
        return taskList.markAsDone(this.option);
    }
}
