public class ListCommand extends Command {
    public String excute(TaskList taskList, Ui ui) {
        return taskList.printAllTask();
    }
}
