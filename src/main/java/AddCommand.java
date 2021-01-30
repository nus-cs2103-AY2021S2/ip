public class AddCommand extends Command {
    protected String[] info;

    public AddCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return info[0].equals("bye");
    }


    /**
     * adds a task to the list using the given information.
     *
     * @param tasks a TaskList object that contains the tasks of the user
     * @param ui helps to print statement to let user know what has been done
     * @param storage contains the filepath to the file to store the given tasks into
     * @throws DukeException if the provided information is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(info);
        return ui.addedTask(tasks);
    }
}
