public class TodoCommand extends Command {

    static private final String ERROR_MESSAGE =
            "Oops! I didn't catch the details of your ToDo Task, could you please try again?\n" +
                    "Make sure your input is correct! (Hint: use the 'help' command to see input formats)";

    public TodoCommand(Parser parser, Ui ui, Storage storage) {
        super(parser, ui, storage);
    }

    public String execute() throws DukeException {

        try {
            String taskDesc = parser.getTaskDescription();

            ToDo newTask = new ToDo(taskDesc);
            taskList = taskList.addTask(newTask);

            String output = userInterface.displayTaskAdded(newTask, taskList);
            storage.writeFile(taskList);

            return output;

        } catch (DukeException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }
}
