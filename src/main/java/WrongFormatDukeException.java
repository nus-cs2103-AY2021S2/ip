public class WrongFormatDukeException extends DukeException {
    private final String todo = "todo (task description)";
    private final String deadline = "deadline (task description) /by (DD/MM/YYYY TIME)";
    private final String event = "event (task description) /from (DD/MM/YYYY TIME) /to (DD/MM/YYYY TIME)";
    private final String done = "done (valid index)";
    private final String delete = "delete (valid index)";
    private final String list = "list";
    private final String help = "help";
    private String command;

    public WrongFormatDukeException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        String str = "Sorry, I believe you have made a mistake in the format of this command.\nHere is the correct format:\n";
        if (command.equals("todo")) {
            str += todo;
        } else if (command.equals("deadline")) {
            str += deadline;
        } else if (command.equals("event")) {
            str += event;
        } else if (command.equals("done")) {
            str += done;
        } else if (command.equals("delete")) {
            str += delete;
        } else if (command.equals("list")) {
            str += list;
        } else if (command.equals("help")) {
            str += help;
        }
        return str;
    }
}
