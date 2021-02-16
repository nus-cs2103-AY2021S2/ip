public class DeleteCommand extends Command {

    static private final String ERROR_MESSAGE =
            "Oops! I didn't catch which task you wanted to delete, could you please try again?\n" +
                    "Make sure your input is correct! (Hint: use the 'help' command to see input formats)";

    public DeleteCommand(Parser parser, Ui ui, Storage storage) {
        super(parser, ui, storage);
    }

    public String execute() throws DukeException {

        try {
            int indexOfTaskToDelete = parser.getIndexToModify();

            Task task = taskList.getTask(indexOfTaskToDelete);
            taskList = taskList.deleteTask(indexOfTaskToDelete);

            String output = userInterface.displayTaskDeleted(task, taskList);
            storage.writeFile(taskList);

            return output;

        } catch (DukeException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

}
