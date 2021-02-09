import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private String successMessage;
    private String errorMessage;

    public ListCommand() {}

    @Override
    public CommandResult execute() {
        String responseToUser = "Here are the tasks in your list:";
        // loop through list and print every task in a new line
        int len = tasks.getSize();
        ArrayList<Task> myTasks = tasks.getTaskList();
        for (int i = 1; i < len + 1; i++) {
            Task curTask = myTasks.get(i - 1);
            String toAdd = "\n" + i + ". " + curTask.toString();
            responseToUser +=  toAdd;
        }
        successMessage = responseToUser;
        return new CommandResult(successMessage);
    }
}
