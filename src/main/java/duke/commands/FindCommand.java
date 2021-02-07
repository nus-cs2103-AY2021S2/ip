package duke.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.dukeexceptions.InvalidTaskTypeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.ConvertType;
import duke.utils.Storage;
import duke.utils.TaskStringConverter;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String toFind;

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
        String msg = "These are the search results:" + listToString(results).toString();
        return msg;
    }

    private StringBuilder listToString(List<Task> results) {
        StringBuilder stringBuilder = new StringBuilder();

        Stream.iterate(1, index -> index <= results.size(), index -> index + 1)
                .forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer index) {
                        Task task = results.get(index - 1);
                        stringBuilder.append("\n")
                                .append(index)
                                .append(". ")
                                .append(task.toString());
                    }
                });

        return stringBuilder;
    }

    private List<Task> searchList(Pattern regEx) {
        List<Task> results = taskList.getList()
                .stream()
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) {
                        String description = task.getDescription();
                        Matcher matcher = regEx.matcher(description);
                        return matcher.find();
                    }
                })
                .collect(Collectors.toList());

        return results;
    }
}
