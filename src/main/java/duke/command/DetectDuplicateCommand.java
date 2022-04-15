package duke.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import duke.Parser;
import duke.Task;



/**
 * handles the detection of duplicate tasks
 */
public class DetectDuplicateCommand {

    /**
     * finds duplicates of the task list
     * @param taskList the list of all tasks currently stored
     * @param <Task> Task
     * @return A set of duplicated tasks
     */
    public static <Task> Set<Task> findDuplicates(ArrayList<Task> taskList) {
        Set<Task> duplicates = new HashSet<>();
        Set<Task> uniques = new HashSet<>();
        for (Task t : taskList) {
            if (!uniques.add(t)) {
                duplicates.add(t);
            }
        }
        return duplicates;
    }

    /**
     * finds the unique tasks of the task list
     * @param taskList the list of all tasks currently stored
     * @param <Task> Task
     * @return A set of unique tasks
     */
    public static <Task> Set<Task> findUniques(ArrayList<Task> taskList) {
        Set<Task> uniques = new HashSet<>();
        for (Task t : taskList) {
            uniques.add(t);
        }
        return uniques;
    }

    /**
     * Deletes all the duplicated tasks
     * @return response string to the user upon deletion of all duplicated tasks
     */
    public static String cleanDuplicates() {
        ArrayList<Task> taskList = Parser.getTaskList();
        Set<Task> uniques = findUniques(taskList);
        taskList.clear();
        taskList.addAll(uniques);
        return "your duplicated tasks have been removed";
    }

    /**
     * runs a duplication check when the user requests so
     * @return response string to user upon detection of duplicates
     */
    public static String runCommand() {
        Set<Task> duplicates = findDuplicates(Parser.getTaskList());
        String output = "These are the duplicated tasks:" + "\n";
        for (Task t : duplicates) {
            output = output + t.toString() + "\n";
        }
        output = output + "\n" + "Enter clean to remove duplicates" + "\n";
        return output;
    }

}
