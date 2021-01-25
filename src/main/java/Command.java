public abstract class Command {

    protected String action, info;

    public Command(String action, String info) {
        this.action = action;
        this.info = info;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
