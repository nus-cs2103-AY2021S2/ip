public class ListCommand extends Command {
    public void excute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printAllTask(ui);
    }
}
