public abstract class Command {

    protected String action;
    protected String info;

    /**
     * Constructor to form a command with execute.
     *
     * @param action command.
     * @param info detail.
     */
    public Command(String action, String info) {
        this.action = action;
        this.info = info;
    }

    /**
     * Execute the command requested by the action on the current information.
     * If command cannot be fulfilled, throws DukeException.
     *
     * @param tasks tasks.
     * @param ui ui.
     * @param storage storage.
     * @throws DukeException if execute fails.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
