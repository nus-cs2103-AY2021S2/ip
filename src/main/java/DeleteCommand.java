public class DeleteCommand extends Command {

    static private final String ERROR_MESSAGE =
            "Oops! I didn't catch which task you wanted to delete, could you please try again?\n" +
                    "Make sure your input is correct! (Hint: use the 'help' command to see input formats)";

    static private final String NO_TASKS =
            "Sorry, you don't have any tasks in your list now!";

    public DeleteCommand(Parser parser, Ui ui, Storage storage) {
        super(parser, ui, storage);
    }

    public String execute() throws DukeException {

        String output = "";

        try {
            if (taskList.getSize() > 0) {

                int indexOfTaskToDelete = parser.getIndexToModify();

                Task task = taskList.getTask(indexOfTaskToDelete);

                taskList = taskList.deleteTask(indexOfTaskToDelete);

                output = userInterface.displayTaskDeleted(task, taskList);
                storage.writeFile(taskList);

            } else {
                output = NO_TASKS;
            }

            return output;

        } catch (DukeException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

}
