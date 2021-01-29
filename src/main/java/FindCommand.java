public class FindCommand extends Command {
    protected String[] info;

    public FindCommand(String[] info) {
        this.info = info;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        StringBuilder keyword = new StringBuilder();
        for (int i = 1; i < info.length; i++) {
            keyword.append(info[i]);
            if (i != info.length - 1) {
                keyword.append(" ");
            }
        }
        TaskList filteredList = tasks.find(keyword.toString());
        ui.printFilteredList(filteredList);
    }
}
