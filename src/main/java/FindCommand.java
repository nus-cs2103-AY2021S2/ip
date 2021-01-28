public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void excute(TaskList taskList, Ui ui, Storage storage) {
        taskList.search(ui, searchTerm);
    }
}
