public class SaveCommand extends Command{
    private String command;

    public SaveCommand() {
        this.command = "";
    }

    public SaveCommand(String command) {
        this.command = command;
    }

    public void excute(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return command.equalsIgnoreCase("bye");
    }
}
