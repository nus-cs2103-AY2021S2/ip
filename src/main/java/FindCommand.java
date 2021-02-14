public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String execute(TaskList taskList, Ui ui) {
        return taskList.search(searchTerm);
    }
}
