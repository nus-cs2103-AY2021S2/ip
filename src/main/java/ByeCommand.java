public class ByeCommand extends Command {

    public ByeCommand(Parser parser, Ui ui, Storage storage) {
        super(parser, ui, storage);
    }

    public String execute() {
        String output = userInterface.displayClosingMessage();
        return output;
    }
}
