public abstract class Command {

    protected String action, info, time;

    public Command(String action, String info, String time) {
        this.action = action;
        this.info = info;
        this.time = time;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
