import java.io.IOException;

public class AddCommand extends Command {

    AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
        this.isExit = false;
    }

    /**
     * Adds todo, deadline or event tasks.
     *
     * @param tasks Task list.
     * @param ui User interface.
     * @param storage Storage.
     * @throws IOException If there are any input or output issues.
     * @throws DukeException If user input is not in the correct format or is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        tasks.addTask(TaskType.valueOf(this.type.toUpperCase()), description, false, storage);
    }

    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
