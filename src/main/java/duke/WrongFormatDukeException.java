package duke;

public class WrongFormatDukeException extends DukeException {
    private final String TODO = "todo (task description)";
    private final String DEADLINE = "deadline (task description) /by (DD/MM/YYYY TIME)";
    private final String EVENT = "event (task description) /from (DD/MM/YYYY TIME) /to (DD/MM/YYYY TIME)";
    private final String DONE = "done (valid index)";
    private final String DELETE = "delete (valid index)";
    private final String LIST = "list";
    private final String HELP = "help";
    private final String FIND = "find (keyword)";
    private final String ADD_CONTACT = "contact add /name (name) /number (number) /address (address)";
    private final String DELETE_CONTACT = "contact delete (valid index)";
    private final String LIST_CONTACT = "contact list";
    private final String EDIT_CONTACT = "contact edit (valid index) (/name OR /number OR /address) (edit)";
    private final String BYE = "bye";
    private String command;

    public WrongFormatDukeException(String command) {
        this.command = command;
    }

    /**
     * Returns the string representation of the exception where the command is not formatted properly.
     *
     * @return String.
     */
    @Override
    public String toString() {
        String str = "Sorry, I believe you have made a mistake in the format of this command.\nHere is the correct format:\n";
        if (command.equals("todo")) {
            str += TODO;
        } else if (command.equals("deadline")) {
            str += DEADLINE;
        } else if (command.equals("event")) {
            str += EVENT;
        } else if (command.equals("done")) {
            str += DONE;
        } else if (command.equals("delete")) {
            str += DELETE;
        } else if (command.equals("list")) {
            str += LIST;
        } else if (command.equals("help")) {
            str += HELP;
        } else if (command.equals("find")) {
            str += FIND;
        } else if (command.equals("contact add")) {
            str += ADD_CONTACT;
        } else if (command.equals("contact delete")) {
            str += DELETE_CONTACT;
        } else if (command.equals("contact list")) {
            str += LIST_CONTACT;
        } else if (command.equals("contact edit")) {
            str += EDIT_CONTACT;
        } else if (command.equals("bye")) {
            str += BYE;
        }
        return str;
    }
}
