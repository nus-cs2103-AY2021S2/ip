public class ListCommand extends Command {

    static private String ERROR_MESSAGE = "Oops! There appears to be some error with the tasklist";

    public ListCommand(Parser parser, Ui ui, Storage storage) {
        super(parser, ui, storage);
    }

    public String execute() throws DukeException {
        try {
            String output = userInterface.displayListMessage(taskList);
            return output;
        } catch (DukeException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }
}
