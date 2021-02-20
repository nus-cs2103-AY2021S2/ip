/**
 * Parses user input.
 */
public class Parser {

    /** String array of user input */
    private String[] input;

    static private final String LIST = "list";
    static private final String DONE = "done";
    static private final String DELETE = "delete";
    static private final String TODO = "todo";
    static private final String EVENT = "event";
    static private final String DEADLINE = "deadline";
    static private final String FIND = "find";
    static private final String BYE = "bye";
    static private final String HELP = "help";

    static private final String UNKNOWN_ERROR =
            "Oops! I didn't catch your command, could you please try again?\n" +
                    "Make sure your input is correct! (Hint: use the 'help' command to see input formats)";


    /**
     * Creates Parser object from given String input.
     *
     * @param input String input to be parsed.
     */
    public Parser(String input) {
        this.input = input.split(" ");
    }

    /**
     * Returns a Command object based on the user's input.
     *
     * @return An executable command.
     */
    public Command getCommand(Parser parser, Ui ui, String storageLocation) throws DukeException {

        Storage storage = new Storage(storageLocation);
        String userInput = input[0].toLowerCase();
        Command output;

        switch (userInput) {
        case LIST:
            output = new ListCommand(parser, ui, storage);
            break;

        case DONE:
            output = new DoneCommand(parser, ui, storage);
            break;

        case DELETE:
            output = new DeleteCommand(parser, ui, storage);
            break;

        case TODO:
            output = new TodoCommand(parser, ui, storage);
            break;

        case EVENT:
            output = new EventCommand(parser, ui, storage);
            break;

        case DEADLINE:
            output = new DeadlineCommand(parser, ui, storage);
            break;

        case FIND:
            output = new FindCommand(parser, ui, storage);
            break;

        case BYE:
            output = new ByeCommand(parser, ui, storage);
            break;

        case HELP:
            output = new HelpCommand(parser, ui, storage);
            break;

        default:
            throw new DukeException(UNKNOWN_ERROR);
        }

        return output;
    }

    /**
     * Returns number, given by user, indicating index of task on task list to be modified.
     *
     * @return Integer stating the index of the task on task list to be modified.
     * @throws DukeException If no input is found.
     */
    public int getIndexToModify() throws DukeException {

        String index = "";

        try {
            index = input[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }

        return Integer.parseInt(index) - 1;
    }

    /**
     * Returns keyword given by user to search tasks for.
     *
     * @return String of the keyword.
     * @throws DukeException If no keyword is found.
     */
    public String getKeyword() throws DukeException {

        try {
            return input[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("search");
        }
    }

    /**
     * Returns description of the Task that user wants to add.
     *
     * @return String comprising description of the task.
     * @throws DukeException if user input is incorrect.
     */
    public String getTaskDescription() throws DukeException{
        String output = "";

        try {
            for (int counter = 1; counter < input.length; counter++) {
                if (input[counter].startsWith("/")) {
                    break;
                } else {
                    output = output + " " + input[counter];
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }

        return output;
    }

    /**
     * Returns String comprising date or deadline of the task.
     *
     * @return String comprising date or deadline of the task.
     * @throws DukeException if user did not input a date.
     */
    public String getDate() throws DukeException {
        try {
            return input[input.length - 2];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    /**
     * Returns String comprising time of the task.
     *
     * @return String comprising time of the task.
     * @throws DukeException if user did not input a time.
     */
    public String getTime() throws DukeException {
        try {
            return input[input.length - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }

    }
}
