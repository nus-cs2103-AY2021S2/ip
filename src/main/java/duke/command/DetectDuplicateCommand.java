package duke.command;

import duke.Parser;
import duke.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DetectDuplicateCommand {

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

    public static <Task> Set<Task> findUniques(ArrayList<Task> taskList) {
        Set<Task> uniques = new HashSet<>();
        for (Task t : taskList) {
            uniques.add(t);
        }
        return uniques;
    }

    public static String cleanDuplicates() {
        ArrayList<Task> taskList = Parser.getTaskList();
        Set<Task> uniques = findUniques(taskList);
        taskList.clear();
        taskList.addAll(uniques);
        return "your duplicated tasks have been removed";
    }

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
