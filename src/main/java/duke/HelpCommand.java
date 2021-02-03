package duke;

public class HelpCommand extends Command{
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        return ui.printHelp();
    }
}
