import java.util.ArrayList;

/**
 * Stores all the output and error messages printed by the program.
 */
public class Ui {
    /**
     * Message to be printed when the program starts.
     */
    public static String greet() {
        return "Heyyoo!! I am Luna :D\n" + "What can I do for you today?\n";
    }

    /**
     * Message to be printed if any number that has to be inputted is invalid.
     * @return The error message string.
     */
    public static String invalidNumberExceptionMessage() {
        return "ERROR! D: Give a number greater than zero and smaller than the total number of tasks.\n";
    }

    /**
     * Message to be printed if the keyword that has been inputted is invalid.
     * @return The error message string.
     */
    public static String invalidKeywordExceptionMessage() {
        return "ERROR! D: The supported keywords are: todo, deadline, event, done, list, delete, bye, tag only.\n";
    }

    /**
     * Message to be printed if the input format for a specific task is wrong.
     * @param task The type of task that had the wrong format.
     * @return The error message string.
     */
    public static String invalidTaskFormatExceptionMessage(String task) {
        return invalidTaskFormatBasicExceptionMessage() + task + "\n" + "The expected format for the task is: "
                + Ui.correctTaskFormat(task) + "\n";
    }

    /**
     * To return part of the common part of the error message for easy identification of response.
     * @return String with the first part of the invalidTaskFormatExceptionMessage() method.
     */
    public static String invalidTaskFormatBasicExceptionMessage() {
        return "ERROR! D: The format for the following task is wrong: ";
    }

    /**
     * Prints the correct format of the given task.
     * @param task The task for which we want the format for.
     * @return A string explaining the format of the task.
     */
    public static String correctTaskFormat(String task) {
        switch(task) {
        case "todo":
            return "todo *description of task*";
        case "deadline":
            return "deadline *description of task* /by *yyyy-mm-dd hh:mm*";
        case "event":
            return "event *description of task* /at *yyyy-mm-dd hh:mm*";
        case "done":
            return "done *index of task according to the list*";
        case "list":
            return "list";
        case "find":
            return "find *keywords you want to find";
        case "delete":
            return "delete *index of task according to the list*";
        case "tag":
            return "tag *index number of task according to the list* *description of the tag*";
        case "tagged":
            return "tagged *keywords of the tag you want to find in the tasks that have been tagged*";
        case "bye":
            return "bye";
        default:
            return "Impossible output. Error in code.";
        }
    }

    /**
     * Message to be printed after a Task has been added.
     * @param keyword The type of Task added.
     * @param task The actual Task object that has been created with the user input.
     */
    public static String outputMessageTask(String keyword, Task task) {
        return "Done adding the " + keyword + " Task: " + task + "\n";
    }

    /**
     * Message to be printed after a task is done.
     * @param task The Task object that has been created with the user input.
     */
    public static String outputMessageDone(Task task) {
        return "Good job! Another Task completed! I have marked it as done:\n" + task + "\n";
    }

    /**
     * Message to be printed after a task is deleted.
     * @param task The task object that has been deleted according to the user input.
     */
    public static String outputMessageDelete(Task task) {
        return "Deleted the following task: " + task + "\n";
    }

    /**
     * Lists all the tasks that are currently stored.
     * @param storage Array List of tasks.
     * @param count Number of tasks in the Array List.
     */
    public static String outputMessageList(ArrayList<Task> storage, int count) {
        String output = "";
        for (int i = 1; i <= count; i++) {
            output += i + ". " + storage.get(i - 1) + "\n";
        }
        return output;
    }

    /**
     * Lists all the tasks with the searched for words.
     * @param storage Array List of tasks.
     * @param description The word/phrase the user wants to find in the currentlist.
     */
    public static String outputMessageFind(ArrayList<Task> storage, String description) {
        String output = "";
        int index = 1;
        for (Task task : storage) {
            if (task.getDescription().contains(description)) {
                output += index + ". " + task + "\n";
                index++;
            }
        }
        return output;
    }

    /**
     * Outputs all the tasks with the description in their tags, if there are tags available.
     * @param storage The list of tasks to search from
     * @param description The keywords in the tags that are being searched for.
     * @return The list of all tasks with the given description in their tags.
     */
    public static String outputMessageTagged(ArrayList<Task> storage, String description) {
        String output = "";
        int index = 1;
        for (Task task : storage) {
            if (task.getIsTagged() && task.getTag().contains(description)) {
                output += index + ". " + task + "\n";
                index++;
            }
        }
        return output;
    }

    /**
     * Message to be printed when a tag has been added to a task.
     */
    public static String outputMessageTag(Task toTag, String description) {
        return "The following tag: (" + description + ") has been added to the follwing task:" + toTag;
    }

    /**
     * Message to be printed when the program ends.
     */
    public static String outputMessageBye() {
        return "Byee, hope to see you again soon!";
    }
}
