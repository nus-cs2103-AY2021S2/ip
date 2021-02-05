public class TodoCommand extends Command {
    private String fullCommand;

    public TodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException.FileLoadError {
        //System.out.println(this.fullCommand);
        taskList.addTask(new Todo(this.fullCommand.substring(5)));

        /*
        System.out.println("Got it. I've added this task:\n"
                + taskList.getTasksList().get(taskList.getTasksCount() - 1)
                + "\nNow you have " + taskList.getTasksCount() + " tasks in the list.");
         */
        storage.save(taskList);
        //System.out.println("i think i saved");
        ui.taskAddMsg(taskList);

    }
    public Boolean isExit() {
        return false;
    }
}
