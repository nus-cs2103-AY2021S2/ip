public class DeadlinesCommand extends Command {
    private String fullCommand;

    public DeadlinesCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException.FileLoadError {
                taskList.addTask(new Deadlines(fullCommand.substring(9, fullCommand.indexOf('/') - 1),
                        fullCommand.substring(fullCommand.indexOf('/') + 4)));
                ui.taskAddMsg(taskList);
                storage.save(taskList);
    }
    public Boolean isExit() {
        return false;
    }
}

