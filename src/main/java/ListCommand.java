public class ListCommand extends Command {
    public String execute(TaskList taskList, Ui ui) {
        return taskList.printAllTask();
    }
}
