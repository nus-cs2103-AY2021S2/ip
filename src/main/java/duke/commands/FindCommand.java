package duke.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String toFind;

    /**
     * Creates a FindCommand object to store the find command input from the user.
     * @param taskList the current list of Tasks.
     * @param ui the object in charge of printing user-friendly outputs.
     * @param storage the object in charge of writing to the local storage file.
     * @param toFind the phrase to search in all the tasks.
     */
    public FindCommand(TaskList taskList, Ui ui, Storage storage, String toFind) {
        super(taskList, ui, storage);
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
        Pattern p = Pattern.compile(toFind, Pattern.CASE_INSENSITIVE);
        List<Task> results = searchList(p);
        if (results.size() == 0) {
            return "There are no tasks matching your input :(";
        } else {
            return printList(results).toString();
        }
    }

    private StringBuilder printList(List<Task> results) {
        StringBuilder sb = new StringBuilder("These are the search results:");
        int counter = 1;
        for (Task t : results) {
            sb.append("\n" + counter + ". " + t.toString());
            counter++;
        }
        return sb;
    }

    private List<Task> searchList(Pattern regEx) {
        List<Task> results = new ArrayList<>();
        for (Task t : this.taskList.getList()) {
            String description = t.getDescription();
            Matcher m = regEx.matcher(description);
            if (m.find()) {
                results.add(t);
            }
        }
        return results;
    }
}
