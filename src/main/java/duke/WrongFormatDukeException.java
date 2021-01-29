package duke;

public class WrongFormatDukeException extends DukeException {
    private final String TODO = "todo (task description)";
    private final String DEADLINE = "deadline (task description) /by (DD/MM/YYYY TIME)";
    private final String EVENT = "event (task description) /from (DD/MM/YYYY TIME) /to (DD/MM/YYYY TIME)";
    private final String DONE = "done (valid index)";
    private final String DELETE = "delete (valid index)";
    private final String LIST = "list";
    private final String HELP = "help";
    private String command;

    public WrongFormatDukeException(String command) {
        this.command = command;
    }

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
        }
        return str;
    }
}
