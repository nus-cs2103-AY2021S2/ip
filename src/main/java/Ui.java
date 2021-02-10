import java.util.ArrayList;

/**
 * Stores all the output and error messages printed by the program.
 */
public class Ui {
    /** Constants that help in the formatting of the printed messages. */
    private static final String SPACE = "     ";
    private static final String LINE = "     <<>><<>><<>><<>><<>><<>><<>><<>>\n";

    /**
     * Message to be printed when the program starts.
     */
    public static String greet() {
        return LINE + SPACE + "Heyyoo!! I am Luna :D\n" + SPACE + "What can I do for you today?\n" + LINE;
    }

    /**
     * Message to be printed if any number that has to be inputted is invalid.
     * @return The error message string.
     */
    public static String invalidNumberExceptionMessage() {
        return LINE + SPACE + "ERROR! D: Give a number greater than zero and smaller than the total number of tasks\n"
                + LINE;
    }

    /**
     * Message to be printed if the keyword that has been inputted is invalid.
     * @return The error message string.
     */
    public static String invalidKeywordExceptionMessage() {
        return LINE + SPACE + "ERROR! D: The supported keywords are: "
                + "todo, deadline, event, done, list, delete, bye, tag only.\n" + LINE;
    }

    /**
     * Message to be printed if the input format for a specific task is wrong.
     * @param task The type of task that had the wrong format.
     * @return The error message string.
     */
    public static String invalidTaskFormatExceptionMessage(String task) {
        return LINE + SPACE + "ERROR! D: The format for the following task is wrong: " + task
                + "\n" + SPACE + "The expected format for the task is: " + Ui.correctTaskFormat(task) + "\n" + LINE;
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
            return "deadline *description of task* /by *date and time*";
        case "event":
            return "event *description of task* /at *date and time*";
        case "done":
            return "done *index of task according to the list*";
        case "list":
            return "list";
        case "delete":
            return "delete *index of task according to the list*";
        case "tag":
            return "tag *index number of task according to the list* *description of the tag*";
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
        return LINE + SPACE + "Done adding the " + keyword + " Task: " + task + "\n" + LINE;
    }

    /**
     * Message to be printed after a task is done.
     * @param task The Task object that has been created with the user input.
     */
    public static String outputMessageDone(Task task) {
        return LINE + SPACE + "Good job! Another Task completed! I have marked it as done:\n"
                + SPACE + task + "\n" + LINE;
    }

    /**
     * Message to be printed after a task is deleted.
     * @param task The task object that has been deleted according to the user input.
     */
    public static String outputMessageDelete(Task task) {
        return LINE + SPACE + "Deleted the following task: " + task + "\n" + LINE;
    }

    /**
     * Lists all the tasks that are currently stored.
     * @param storage Array List of tasks.
     * @param count Number of tasks in the Array List.
     */
    public static String outputMessageList(ArrayList<Task> storage, int count) {
        String output = LINE;
        for (int i = 1; i <= count; i++) {
            output += SPACE + i + ". " + storage.get(i - 1) + "\n";
        }
        return output + LINE;
    }

    /**
     * Lists all the tasks with the searched for words.
     * @param storage Array List of tasks.
     * @param description The word/phrase the user wants to find in the currentlist.
     */
    public static String outputMessageFind(ArrayList<Task> storage, String description) {
        String output = LINE;
        int index = 1;
        for (Task task : storage) {
            if (task.getDescription().contains(description)) {
                output += SPACE + index + ". " + task + "\n";
                index++;
            }
        }
        return output + LINE;
    }

    public static String outputMessageTagged(ArrayList<Task> storage, String description) {
        String output = LINE;
        int index = 1;
        for (Task task : storage) {
            if (task.getTag().equals(description)) {
                output += SPACE + index + ". " + task + "\n";
                index++;
            }
        }
        return output + LINE;
    }

    public static String outputMessageTag(Task toTag, String description) {
        return LINE + SPACE + "The following tag: (" + description + ") has been added to the follwing task:"
                + toTag + LINE;
    }

    /**
     * Message to be printed when the program ends.
     */
    public static String outputMessageBye() {
        return LINE + SPACE + "Byee, hope to see you again soon!" + LINE;
    }
}
