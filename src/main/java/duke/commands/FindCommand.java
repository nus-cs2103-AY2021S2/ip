package duke.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String toFind;

    /**
     * Creates a FindCommand object to store the find command input from the user.
     * @param taskList the current list of Tasks.
     * @param storage the object in charge of writing to the local storage file.
     * @param toFind the phrase to search in all the tasks.
     */
    public FindCommand(TaskList taskList, Storage storage, String toFind) {
        super(taskList, storage);
        this.toFind = toFind;
    }

    /**
     * Searches TaskList for Tasks with descriptions matching toFind String.
     * If there exist such Tasks, prints these Tasks.
     * Else, display message indicating no matching Tasks.
     * @return message showing all the relevant Tasks.
     */
    @Override
    public String execute() {
        Pattern regexPattern = Pattern.compile(toFind, Pattern.CASE_INSENSITIVE);
        List<Task> results = searchList(regexPattern);
        if (results.size() == 0) {
            String noMatchingTaskMsg = "There are no tasks matching your input :(";
            return noMatchingTaskMsg;
        } else {
            return printList(results).toString();
        }
    }

    private StringBuilder printList(List<Task> results) {
        assert results.size() >= 1;

        StringBuilder stringBuilder = new StringBuilder("These are the search results:");
        int counter = 1;
        for (Task task : results) {
            stringBuilder.append("\n").append(counter).append(". ").append(task.toString());
            counter++;
        }
        return stringBuilder;
    }

    private List<Task> searchList(Pattern regEx) {
        List<Task> results = new ArrayList<>();
        for (Task t : this.taskList.getList()) {
            String description = t.getDescription();
            Matcher matcher = regEx.matcher(description);
            if (matcher.find()) {
                results.add(t);
            }
        }
        return results;
    }
}
