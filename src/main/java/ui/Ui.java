package ui;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import tasklist.TaskList;
import tasks.DukeTask;

public class Ui {

    private final String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    public Ui() { }


    public void printMessage(String message) {
        System.out.println(message);
    }


    /**
     * Introduction output from the UI.
     *
     * @return the String output for this command.
     */
    public String introduction() {
        return "Hello from\n" + logo
                + "Please enter the keyword:\n"
                + "        help\n" + "to see the list of commands\n"
                + "Hello! I'm Duke\n" + "What can I do for you?\n";
    }

    /**
     * Help output from the UI.
     *
     * @return the String output for this command.
     * */
    public String help() {
        return "Here is the list of commands:"
                + "\nShow list of commands:\n - help"
                + "\nExit duke:\n - bye"
                + "\nDisplay list of tasks:\n - list"
                + "\nDelete task:\n - delete <task id>"
                + "\nSet task as completed:\n - done <task id>"
                + "\nFilter tasks by keyword (in description):\n - find <keyword>"
                + "\nAdd a new task:\n - todo <task>"
                + "\nAdd a new deadline:\n - deadline <task> /by <yyyy-mm-dd>"
                + "\nAdd a new event:\n - event <name> /at <yyyy-mm-dd> <hh:mm> - <hh:mm>"
                + "\n**REMEMBER to exclude the < > when entering keywords\n";
    }

    /**
     * Pre-loading output from the UI.
     *
     * @return the String output for this command.
     */
    public String preload() {
        return "--Booting up Application--\n"
            + "Checking if loading data exist...\n";
    }

    /**
     * Goodbye output from the UI.
     *
     * @return the String output for this command.
     * */
    public String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads the input by the user.
     *
     * @return the input by the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Adding Tasks output from the UI.
     *
     * @param task The DukeTask Added.
     * @param size The size of the TaskList.
     * @return the String output for this command.
     */
    public String add(DukeTask task, int size) {
        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + String.format("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Deleting Tasks output from the UI.
     *
     * @param task The DukeTask deleted.
     * @param size The size of the TaskList.
     * @return the String output for this command.
     */
    public String delete(DukeTask task, int size) {
        return "Noted. I've removed this task:\n"
            + "  " + task
            + String.format("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Done output from the UI.
     *
     * @param task Task marked as done.
     * @return the String output for this command.
     */
    public String done(DukeTask task) {
        return "Nice! I've marked this task as done:\n"
            + "  " + task + "\n";
    }

    /**
     * Lists the tasks in the taskList.
     *
     * @param taskList The TaskList.
     * @return the String output for this command.
     */
    public String list(TaskList taskList) {
        AtomicInteger count = new AtomicInteger(1);
        StringBuilder output = new StringBuilder( "Here are the tasks in your list:\n");

        taskList.getList().stream().map(x -> "." + x.toString() + "\n")
                .forEach(x -> {
                    output.append(count.getAndIncrement());
                    output.append(x);
                });
        return output.toString();
    }

    /**
     * Shows the item in the List.
     *
     * @param taskList List of Tasks containing the keyword.
     * @return the String output for this command.
     */
    public String find(List<DukeTask> taskList) {
        AtomicInteger count = new AtomicInteger(1);
        StringBuilder output = new StringBuilder( "Here are the matching tasks in your list:\n");

        taskList.stream().map(x -> "." + x.toString() + "\n")
                .forEach(x -> {
                    output.append(count.getAndIncrement());
                    output.append(x);
                });
        return output.toString();
    }

    /**
     * Loading File Error output from the UI.
     *
     * @return the String output for this command.
     */
    public String loadFileError() {
        return "An error occurred while loading!!";
    }

    /**
     * Index Out of Bounds Error output from the UI.
     *
     * @return the String output for this command.
     */
    public String arrayIndexOutOfBoundsError() {
        return "☹ OOPS!!! The index needs to be in range of the list.\n";
    }

    /**
     * Empty Index Error output from the UI.
     *
     * @return the String output for this command.
     */
    public String emptyIndexError() {
        return "☹ OOPS!!! I need the index of the task you want done.\n";
    }

    /**
     * Empty Description Error output from the UI.
     *
     * @param tasktype Type of the Task.
     * @return the String output for this command.
     */
    public String emptyDescriptionError(String tasktype) {
        return "☹ OOPS!!! The description of a " + tasktype + " cannot be empty.\n";
    }

    /**
     * Empty Details Error output from the UI.
     *
     * @param tasktype Type of the Task.
     * @return the String output for this command.
     */
    public String emptyDetailsError(String tasktype) {
        return tasktype.equals("deadline")
                ? "☹ OOPS!!! The date of a deadline cannot be empty.\n"
                : "☹ OOPS!!! The timing of an event cannot be empty.\n";
    }

    /**
     * Unknown Command Error output from the UI.
     *
     * @return the String output for this command.
     */
    public String unknownCommandError() {
        return "☹ OOPS!!! Command is not recognized!!\n";
    }
}

