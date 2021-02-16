public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private String taskType;
    private Task taskToAdd;
    private String successMessage;

    /**
     * Construct an AddCommand.
     * @param operator Type of task to be added.
     * @param taskDetail Details of task to be added.
     */
    public AddCommand(String operator, String... taskDetail) {
        String description;
        String time;

        assert taskDetail.length > 0;
        // initialise AddCommand with a specific task
        switch(operator) {
        case "todo":
            this.taskType = "todo";
            description = taskDetail[0];
            taskToAdd = new ToDo(description);
            break;
        case "deadline":
            this.taskType = "deadline";
            description = taskDetail[0];
            time = taskDetail[1];
            taskToAdd = new Deadline(description, time);
            break;
        case "event":
            this.taskType = "event";
            description = taskDetail[0];
            time = taskDetail[1];
            taskToAdd = new Event(description, time);
            break;
        default:
            taskToAdd = new Task("");
        }
    }

    @Override
    public CommandResult execute() {
        super.tasks.addTask(this.taskToAdd);
        successMessage = "Got it. I've added this task:" + "\n" + this.taskToAdd.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
        return new CommandResult(successMessage);
    }
}
