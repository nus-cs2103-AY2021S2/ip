import java.io.IOException;

public class ByeCommand extends Command {

    @Override
    public boolean isBye() {
        return true;
    }


    /**
     * Stores all the tasks into a file.
     * Filepath of the file will be obtained from the Storage object.
     *
     * @param tasks A TaskList object that contains the tasks of the user.
     * @param ui Helps to print statement to let user know what has been done.
     * @param storage Contains filepath of the file to store the given tasks into.
     * @return A string containing the response to send back to the user.
     * @throws IOException If no such file exist in the directory the filepath directs to.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.store(tasks);
        return ui.bye();
    }
}
