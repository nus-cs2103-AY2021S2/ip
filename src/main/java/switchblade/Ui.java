package switchblade;

import java.util.ArrayList;
import java.util.List;

/**
 * Returns String required by GUI to output
 *
 * @author leeyueyang
 */
public class Ui {




    public static String addedTask(List<Task> taskList) {
        // Retrieve last task in list and print it
        StringBuilder sb = new StringBuilder();

        // get last item in list
        sb.append("Added to list:\n" + taskList.get(taskList.size() - 1).toString());
        sb.append("\nYou now have " + taskList.size() + " tasks\n");

        return sb.toString();
    }

    public static String removeTask(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it! I've removed the task(s) for you\n");
        sb.append("You now have " + taskList.size() + " tasks\n");

        return sb.toString();
    }

    public static String completedTask() {
        return "Good job! I've marked it as completed for you as well!\n";
    }

    public static String fileNotFound() {
        return "I notice this is the first time you're running the script!";
    }

    public static String initialise() {
        return "Hello! I'm SwitchBlade and I aim to do everything you want me to do!";
    }

    public static String initRetrieveList(myList taskList) {
        if (taskList.getNumTasks() > 0) {
            return "It seems like you've had a task list from a previous session, here's how it looks"
                            + taskList.toString();
        } else {
            return "";
        }
    }

    public static String unknownCommand() {
        return "Unfortunately I don't know what you want me to do :L";
    }

    public static String fileError() {
        return "Some errors were encountered with the file";
    }

    public static String argumentError() {
        return "Too many arguments, please give me just 1 task to mark as completed";
    }

    public static String todoError() {
        return "I don't understand your todo command, the syntax should be {todo} {description}";
    }

    public static String deadlineError() {
        return "I don't understand your deadline command, the syntax should be " +
                "{deadline} {description} /by {yyyy-MM-dd hhmm} with deadline datetime given in 24hr format";
    }

    public static String eventError() {
        return "I don't understand your deadline command, the syntax should be " +
                "{deadline} {description} /at {yyyy-MM-dd hhmm} /to {yyyy-MM-dd hhmm} " +
                "with deadline datetime given in 24hr format";
    }

    public static String taskNotFoundError() {
        return "It seems like nothing you specified exists :(";
    }

    public static String printList(myList taskList) {
        return "Here's your list of tasks!" + taskList.toString();
    }

    public static String printFoundTasks(ArrayList<Task> taskArrayList) {
        if (taskArrayList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Task t : taskArrayList) {
                sb.append(t);
                sb.append("\n");
            }

            if (!sb.toString().isBlank()) {
                return "Here's the tasks I found with your search term!\n" + sb.toString();
            }
        }

        return taskNotFoundError();
    }

    public static String shutdown() {
        return "See you soon!";
    }
}
