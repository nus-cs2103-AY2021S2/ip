public class EventsCommand extends Command {
    private String fullCommand;

    public EventsCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException.FileLoadError {
        taskList.addTask(new Events(fullCommand.substring(6, fullCommand.indexOf('/') - 1),
                fullCommand.substring(fullCommand.indexOf('/') + 4)));
        ui.taskAddMsg(taskList);
        storage.save(taskList);
        /*
        System.out.println("Got it. I've added this task:\n"
                + taskList.getTasksList().get(taskList.getTasksCount() - 1)
                + "\nNow you have " + taskList.getTasksCount() + " tasks in the list.");

         */
    }
    public Boolean isExit() {
        return false;
    }
}
