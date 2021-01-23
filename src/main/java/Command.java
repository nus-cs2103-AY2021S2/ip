public abstract class Command {
    /**
     * Execute the command and perform the necessary manipulations
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the file writer
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
