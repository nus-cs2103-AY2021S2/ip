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
     * changes the state of a task to completed.
     *
     * @param tasks a TaskList object that contains the tasks of the user
     * @param ui helps to print statement to let user know what has been done
     * @param storage contains the filepath of the file to store the given tasks into
     * @throws DukeException if the information given is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task task = tasks.done(info);
        storage.store(tasks);
        return ui.didTask(task);
    }
}
