package duke;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        return ui.printBye();
    }
}
