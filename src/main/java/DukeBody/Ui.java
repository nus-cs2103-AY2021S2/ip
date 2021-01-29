package DukeBody;

import DukeTask.*;
import java.time.format.DateTimeFormatter;

/**
 * Class handler for Duke output formatting.
 */
public class Ui {
    public final static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(
        "eee, dd MMM yyyy HH:mm a");

    /**
     * prints out messages with fixed indentation.
     */
    public void print (String... messages) {
        for (String message: messages) {
            System.out.println("    " + message);
        }
    }

    public void brace () {
        print("__________________________________________________"
                + "__________________________________________________");
    }

    /**
     * shows reading database message from userdata.
     * @param username
     */
    public void storageReading (String username) {
        print("Reading tasks from userdata for User: " + username);
    }

    /**
     * greet the users in a flashy and welcoming manner.
     */
    public void greet () {
        print("I am", 
                " ____        _        ", 
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "at your service.");
    }

    /**
     * the user has added even more tasks...
     * prints the task added and the number of tasks remainig in the list.
     * @param tasks     the list of tasks
     */
    public void addedTask (TaskList tasks) {
        print("Added task:");
        print(tasks.get(tasks.size() - 1).taskInformation(Ui.outputFormat));
        print("You now have " + tasks.size() + " tasks.");
    }

    /**
     * the user has marked a task as done successfully!
     * prints out the task information of a task.
     * @param task  the task that was marked done.
     */
    public void markedAsDone (Task task) {
        print("Woohoo the task has been marked as done:");
        print(task.taskInformation(Ui.outputFormat));
    }

    /**
     * prints the task removed from the list of tasks and the number of remaining
     * tasks left.
     * @param tasks     list of tasks to remove the task from.
     * @param task      the task to be removed.
     */
    public void removedTask (TaskList tasks, Task task) {
        print("Removed task:", task.taskInformation(Ui.outputFormat), "You now have " 
            + tasks.size() + " tasks.");
    }

    /**
     * oops! the user did not manage to save his tasks.
     */
    public void saveTasksFailure () {
        print("Save tasks to userdata failed spectacularly :((");
    }

    /**
     * Express absolute confusion, befuddlement and puzzlement.
     */
    public void confuzzled () {
        print("WHaT dO YoU MeAN ?! .___.");
    }

    /**
     * Bids the user farewell.
     */
    public void byebye () {
        print("Byebye, come back again soon! :D");
    }
}