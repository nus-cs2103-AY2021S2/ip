import java.io.IOException;

public class DoneCommand extends Command {
    protected String[] info;

    public DoneCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return info[0].equals("bye");
    }


    /**
     * Changes the state of a task to completed.
     *
     * @param tasks A TaskList object that contains the tasks of the user.
     * @param ui Helps to print statement to let user know what has been done.
     * @param storage Contains the filepath of the file to store the given tasks into.
     * @return String containing the response to send back to the user.
     * @throws DukeException If the information given is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task task = tasks.doneTask(info);
        storage.store(tasks);
        return ui.didTask(task);
    }
}
