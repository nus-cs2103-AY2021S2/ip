public class AddCommand extends Command {
    public static final String COMMAND_WORD = "todo/event/deadline";
    public static final String MESSAGE_USAGE =
            String.format(
              "%s: add a task of a specific type to the task list.\n"
                      + "---- Example 1: todo [task name]\n" + "---- Example 2: deadline [task name] /by [yyyy-mm-dd]\n" +
                      "---- Example 3: event [task name] /at [yyyy-mm-dd]", COMMAND_WORD);
    Task toAdd;

    public AddCommand(Task toAdd) {
        super();
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute() {
        try {
            tasks.addTask(toAdd);
            String noun = tasks.getSize() <= 1? "task": "tasks";
            String message = String.format(
                    "Got it. I have added this task to your list:\n%s\n" +
                            "Now you have %d %s in total. Good luck.", toAdd.toString(), tasks.getSize(), noun);
            return new CommandResult(message);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_ADDING_FAILURE);
        }
    }
}
