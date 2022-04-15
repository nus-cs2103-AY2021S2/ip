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
     * Prints the tasks stored in the TaskList object.
     *
     * @param tasks A TaskList object that contains the tasks of the user.
     * @param ui Helps to print statement to let user know what has been done.
     * @param storage Contains the filepath to the file to store the given tasks into.
     * @return String containing the response to send back to the user.
     * @throws DukeException If the provided information is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.printList(tasks);
    }
}
