public class TodoCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    TaskList taskList = Duke.taskList;

    public TodoCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    @Override
    public void run(Storage storage) throws DukeException {
        if (fullCmdStrArray.length == 1) { // handle todo without parameters
            throw new DukeException("Sorry human, please enter a name for this task.");
        }
        String taskName = fullCmd.substring(5); // remove "todo "
        TodoTask newTodoTask = new TodoTask(taskName);

        taskList.add(newTodoTask);
        storage.saveTaskList();
        ui.printAddToList(newTodoTask);
    }

}
