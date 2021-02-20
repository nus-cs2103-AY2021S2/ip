public class FindCommand extends Command {

    static private final String ERROR_MESSAGE =
            "Oops! I didn't catch your keyword, could you please try again?\n" +
                    "Make sure your input is correct! (Hint: use the 'help' command to see input formats)";

    public FindCommand(Parser parser, Ui ui, Storage storage) {
        super(parser, ui, storage);
    }

    public String execute() throws DukeException {

        try {
            String keyword = parser.getKeyword();
            String output = userInterface.displayTaskSearch(keyword, taskList);
            return output;

        } catch (DukeException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }
}
