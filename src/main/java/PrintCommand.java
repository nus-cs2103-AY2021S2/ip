public class PrintCommand extends Command {
    protected String[] info;

    public PrintCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return info[0].equals("bye");
    }


    /**
     * prints the tasks stored in the TaskList object.
     *
     * @param tasks a TaskList object that contains the tasks of the user
     * @param ui helps to print statement to let user know what has been done
     * @param storage contains the filepath to the file to store the given tasks into
     * @throws DukeException if the provided inormation is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.printList(tasks);
    }
}
