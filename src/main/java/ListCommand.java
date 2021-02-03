public class ListCommand extends Command {
    public String excute(TaskList taskList) {
        return taskList.printAllTask();
    }
}
