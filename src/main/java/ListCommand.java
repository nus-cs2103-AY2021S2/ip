public class ListCommand extends Command {

    public ListCommand(Parser parser, Ui ui, Storage storage) {
        super(parser, ui, storage);
    }

    public String execute() {
        String output = userInterface.displayListMessage(taskList);
        return output;
    }
}
