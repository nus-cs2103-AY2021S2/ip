public class DoneCommand extends Command {

    static private final String ERROR_MESSAGE =
            "Oops! I didn't catch which task you wanted to complete, could you please try again?\n" +
                    "Make sure your input is correct! (Hint: use the 'help' command to see input formats)";

    public DoneCommand(Parser parser, Ui ui, Storage storage) {
        super(parser, ui, storage);
    }

    public String execute() throws DukeException {

        try {
            int indexOfTaskToComplete = parser.getIndexToModify();
            taskList = taskList.completeTask(indexOfTaskToComplete);

            String output = userInterface.displayTaskCompleted(taskList.getTask(indexOfTaskToComplete));
            storage.writeFile(taskList);

            return output;

        } catch (DukeException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }
}
