import java.io.IOException;

public class DeleteCommand extends Command {
    protected String[] info;

    public DeleteCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return info[0].equals("bye");
    }


    /**
     * removes a task from the list using the given information.
     * The information may not be accurate and throw a DukeException
     * if the provided information is inaccurate.
     *
     * @param tasks a TaskList object that contains the tasks of the user
     * @param ui helps to print statement to let user know what has been done
     * @param storage contains the filepath of the file to store the given tasks into
     * @throws DukeException if the provided information is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task task = tasks.delete(info);
        int listLength = tasks.getListLength();
        storage.store(tasks);
        return ui.deletedTask(task, listLength);
    }
}
