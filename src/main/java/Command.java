public abstract class Command {
    String command, task, date;

    public Command(String command, String task, String date) {
        this.command = command;
        this.task = task;
        this.date = date;
    }
    
    abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    abstract boolean isExit();
}
