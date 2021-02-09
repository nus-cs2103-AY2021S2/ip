import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;
    private String successMessage;
    private String errorMessage;

    public FindCommand (String keyword) {
            this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public CommandResult execute() throws DukeException {
        TaskList matchingTasks = tasks.findMatchingTask(this.keyword);

        String responseToUser = "Here are the matching tasks in your list:";
        // loop through list and print every task in a new line
        int len = matchingTasks.getSize();
        ArrayList<Task> tasks = matchingTasks.getTaskList();
        for (int i = 1; i < len + 1; i++) {
            Task curTask = tasks.get(i - 1);
            String toAdd = "\n" + i + ". " + curTask.toString();
            responseToUser += toAdd;
        }
        successMessage = responseToUser;
        return new CommandResult(successMessage);
    }
}
