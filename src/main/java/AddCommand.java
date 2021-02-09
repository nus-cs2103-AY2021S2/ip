public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private String taskType;
    private Task taskToAdd;
    private String successMessage;

    public AddCommand(String operator, String... taskDetail) {
        String description;
        String time;

        switch(operator) {
        case "todo":
            taskType = "todo";
            description = taskDetail[0];
            taskToAdd = new ToDo(description);
            break;
        case "deadline":
            taskType = "deadline";
            description = taskDetail[0];
            time = taskDetail[1];
            taskToAdd = new Deadline(description, time);
            break;
        case "event":
            taskType = "event";
            description = taskDetail[0];
            time = taskDetail[1];
            taskToAdd = new Event(description, time);
        }
    }

    @Override
    public CommandResult execute() {
        tasks.addTask(this.taskToAdd);
        successMessage = "Got it. I've added this task:" + "\n" + this.taskToAdd.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
        return new CommandResult(successMessage);
    }
}
