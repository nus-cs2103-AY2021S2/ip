public class Command {
    protected TaskList tasks;

//    protected Task relatedTask;
//    protected Task relatedTaskNumber;
//    protected TaskList relatedTaskList;

    public Command(){ }

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    public CommandResult execute() throws DukeException {
        return new CommandResult();
    }
}
