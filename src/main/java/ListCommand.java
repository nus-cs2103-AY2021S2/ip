public class ListCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list += String.valueOf(i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        ui.list(list);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
