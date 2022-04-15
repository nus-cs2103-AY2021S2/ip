public class TodoCommand extends Command {
    private final String fullCommand;

    public TodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException.FileLoadError {
                //System.out.println(this.fullCommand);
                taskList.addTask(new Todo(this.fullCommand.substring(5)));

                storage.save(taskList);
                //System.out.println("i think i saved");
                ui.taskAddMsg(taskList);

    }
    public Boolean isExit() {
        return false;
    }
}
