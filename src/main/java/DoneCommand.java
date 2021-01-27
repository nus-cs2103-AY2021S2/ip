public class DoneCommand extends Command {

    public static final String COMMAND = "done";
    private final String HEADER = "Nice! I've marked this task as done:\n\t";
    private static final String ERROR_MESSAGE = "☹ Sorry, please enter a valid task number.\n\tCommand: done [task number]";

    private int taskIndex;

    private DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute() throws DukeException {
        try {
            Task task = taskList.markAsDone(taskIndex);
            return HEADER + task;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    @Override
    public boolean shouldSave() {
        return true;
    }

    public static DoneCommand parseArguments(String input) throws DukeException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(input) - 1;
            return new DoneCommand(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }
}
