package duke;

import java.util.ArrayList;
import java.util.List;

public class Ui {

    private Ui() {
    }

    public static String showWelcome() {
        return textWrapper("Hello! I'm Duke \nWhat can I do for you?");
    }

    public static String showBye() {
        return textWrapper("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints out the TaskList in a nice format.
     * @param tasks the TaskList to be printed.
     */
    public static String showList(TaskList tasks) {
        String out = "Here are the tasks in your list: \n";
        out += printList(tasks);
        return textWrapper(out);
    }

    /**
     * Searches the TaskList and prints out the items that matches the user
     * specifications.
     * @param tasks  the TaskList to be searched.
     * @param toFind the String that the user is looking for.
     */
    public static String showFind(TaskList tasks, String toFind) {
        String out;
        List<Task> tempTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getName().contains(toFind)) {
                tempTasks.add(t);
            }
        }
        if (!tempTasks.isEmpty()) {
            out = "Here are the matching tasks in your list: \n";
            out += printList(new TaskList(tempTasks));
        } else {
            out = "Nothing in the list matches\n";
        }
        return textWrapper(out);
    }

    /**
     * Prints out confirmation after marking an item as done.
     * @param input The string input by the user.
     * @param tasks The TaskList to be checked.
     */
    public static String showDone(String input, TaskList tasks) {
        String out = "Nice! I've marked this task as done: \n  ";
        out += tasks.get(getIndex(input));
        return textWrapper(out);
    }

    /**
     * Prints out confirmations that the Task was added to the TaskList.
     * @param tasks The TaskList to be added to.
     * @param task  The Task to be added.
     */
    public static String showTaskAdded(TaskList tasks, Task task) {
        String out = "Got it. I've added this task:\n ";
        out += task.toString() + "\n";
        out += String.format("Now you have %d tasks in the list.", tasks.size() + 1);
        return textWrapper(out);
    }

    /**
     * Prints out confirmation for deleting a task from the TaskList.
     * @param input The input string.
     * @param tasks The TaskList which the item is removed from.
     */
    public static String showDeleteTask(String input, TaskList tasks) {
        String out = "Noted. I've removed this task: \n  ";
        out += tasks.get(getIndex(input)) + "\n";
        out += String.format("Now you have %d tasks in the list.", tasks.size() - 1);
        return textWrapper(out);
    }

    public static String showDukeTaskError() {
        return textWrapper("The description of a task cannot be empty.");
    }

    public static String showDukeGeneralError() {
        return textWrapper("I'm sorry, but I don't know what that means");
    }

    public static String showDukeEmptyListError() {
        return textWrapper("You have nothing in the list!");
    }

    public static String showLoadingError() {
        return textWrapper("This file cant be loaded! Creating a new file called duke.txt in CWD");
    }

    public static String showEmptyError() {
        return textWrapper("Please Enter a command!");
    }

    public static int getIndex(String input) {
        return Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
    }

    private static String printList(TaskList contentList) {
        StringBuilder printStr = new StringBuilder();
        for (int i = 0; i < contentList.size(); i++) {
            String textToAdd = String.format("%d. %s%n", i + 1, contentList.get(i).toString());
            printStr.append(textToAdd);
        }
        return printStr.toString();
    }

    private static String textWrapper(String a) {
        return a;
    }

}
