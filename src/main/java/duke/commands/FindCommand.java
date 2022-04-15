package duke.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.TaskStringConverter;

/**
 * Represents a find command which finds tasks with a specified phrase in the task description.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private String toFind;

    /**
     * Creates a FindCommand object to store the find command input from the user.
     *
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
     *
     * If there exist such Tasks, prints these Tasks.
     * Else, display message indicating no matching Tasks.
     *
     * @return message showing all the relevant Tasks.
     */
    @Override
    public String execute() {
        Pattern regexPattern = Pattern.compile(toFind, Pattern.CASE_INSENSITIVE);
        List<Task> results = searchList(regexPattern);
        if (results.size() == 0) {
            String noMatchingTaskMsg = "There are no tasks matching your input :(";
            return noMatchingTaskMsg;
        }
        String msg = "These are the search results:" + listToString(results);
        return msg;
    }

    private List<Task> searchList(Pattern regEx) {
        List<Task> results = taskList.getList()
                .stream()
                .filter(task -> {
                    String description = task.getDescription();
                    Matcher matcher = regEx.matcher(description);
                    return matcher.find();
                })
                .collect(Collectors.toList());

        return results;
    }

    private String listToString(List<Task> results) {
        String searchResults = TaskStringConverter.stringTasksForProgram(results);

        return searchResults;
    }
}
