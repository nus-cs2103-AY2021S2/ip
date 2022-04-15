package duke.command;

import duke.Parser;
import duke.Task;

public class FindCommand {

    /**
     * Searches the task list for tasks containing the keyword the user inputted
     * @param input user input of keyword he wants to search for
     * @return List of all tasks that matches his keyword
     */
    public static String runCommand(String input) {
        String[] splitInput = input.split("\\s+");
        String toSearch = "";
        for (int i = 1; i < splitInput.length; ++i) {
            toSearch += splitInput[i];
        }
        toSearch = toSearch.trim();
        StringBuilder sb = new StringBuilder();
        boolean hasFound = false;
        for (Task t : Parser.getTaskList()) {
            if (t.getDescription().contains(toSearch)) {
                hasFound = true;
                sb.append(t.toString() + "\n");
            }
        }
        if (hasFound) {
            return sb.toString();
        } else {
            return "no tasks found that matches your input!";
        }
    }
}
