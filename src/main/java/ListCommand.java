public class ListCommand extends Command {
    String type;
    public ListCommand(String t) {
        this.type = t;
    }

    public void execute(TaskList tasks, String input, DataHandler dataHandler) {
            tasks.list();
    }

    public boolean isExit() {
        return false;
    }
}
