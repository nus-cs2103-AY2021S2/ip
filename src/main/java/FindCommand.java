public class FindCommand extends Command {
    protected String[] info;

    public FindCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * Finds the tasks in the list that matches the keyword provided.
     *
     * @param tasks TaskList object that contains the tasks of the user.
     * @param ui Helps to print statement to let user know what has been done.
     * @param storage Contains filepath of the file to store the given tasks into.
     * @return String containing the response to send back to the user.
     * @throws DukeException If no such file exist in the directory the filepath directs to.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder keyword = new StringBuilder();
        for (int i = 1; i < info.length; i++) {
            keyword.append(info[i]);
            if (i != info.length - 1) {
                keyword.append(" ");
            }
        }
        TaskList filteredList = tasks.find(keyword.toString());
        return ui.printFilteredList(filteredList);
    }
}
