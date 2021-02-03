public class ListCommand extends Command{
    public ListCommand() {}
    public String execute(Storage storage, TaskList taskList, Ui ui, String command) {
        String result = "";
        for (int i=0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            result += ui.getList(i, task);
            result += "\n";
        }
        return result;
    }
}
