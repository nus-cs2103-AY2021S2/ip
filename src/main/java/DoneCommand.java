public class DoneCommand extends Command {
    public DoneCommand() {}
    public String execute(Storage storage, TaskList taskList, Ui ui, String command) {
        String[] splitCommand = command.split(" ");
        int index = Integer.parseInt(splitCommand[1]);
        try {
            Task currTask = taskList.get(index - 1);
            currTask = currTask.doTask();
            taskList.set(index - 1, currTask);
            storage.saveTasks(taskList);
            return ui.getDoneSuccess(currTask);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return ui.getDoneFail();
        }
    }
}
