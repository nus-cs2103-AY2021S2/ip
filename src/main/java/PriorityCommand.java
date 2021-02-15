import java.io.IOException;

public class PriorityCommand extends Command {
    protected String[] info;

    public PriorityCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * Adds priority to a certain task in the list.
     *
     * @param tasks A TaskList object that contains the tasks of the user.
     * @param ui Helps to print statement to let user know what has been done.
     * @param storage Contains the filepath to the file to store the given tasks into.
     * @return String containing the response to send back to the user.
     * @throws DukeException If the provided information is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task prioritisedTask = tasks.addPriority(info);
        storage.store(tasks);
        return ui.addedPriority(prioritisedTask);
    }
}
