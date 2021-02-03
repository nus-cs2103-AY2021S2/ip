public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String excute(TaskList taskList) {
        return taskList.search(searchTerm);
    }
}
