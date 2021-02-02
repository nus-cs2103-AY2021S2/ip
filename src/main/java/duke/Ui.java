package duke;

import java.util.ArrayList;
import java.util.List;

public class Ui {

    public Ui() {
        textWrapper("Hello! I'm Duke \nWhat can I do for you?");
    }

    public void showBye() {
        textWrapper("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints out the TaskList in a nice format.
     * @param tasks the TaskList to be printed.
     */
    public void showList(TaskList tasks) {
        String out = "Here are the tasks in your list: \n";
        out += printList(tasks);
        textWrapper(out);
    }

    /**
     * Searches the TaskList and prints out the items that matches the user
     * specifications.
     * @param tasks  the TaskList to be searched.
     * @param toFind the String that the user is looking for.
     */
    public void showFind(TaskList tasks, String toFind) {
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
        textWrapper(out);
    }

    /**
     * Prints out confirmation after marking an item as done.
     * @param input The string input by the user.
     * @param tasks The TaskList to be checked.
     */
    public void showDone(String input, TaskList tasks) {
        String out = "Nice! I've marked this task as done: \n  ";
        out += tasks.get(getIndex(input));
        textWrapper(out);
    }

    /**
     * Prints out confirmations that the Task was added to the TaskList.
     * @param tasks The TaskList to be added to.
     * @param task  The Task to be added.
     */
    public void showTaskAdded(TaskList tasks, Task task) {
        String out = "Got it. I've added this task:\n ";
        out += task.toString() + "\n";
        out += String.format("Now you have %d tasks in the list.", tasks.size() + 1);
        textWrapper(out);
    }

    /**
     * Prints out confirmation for deleting a task from the TaskList.
     * @param input The input string.
     * @param tasks The TaskList which the item is removed from.
     */
    public void showDeleteTask(String input, TaskList tasks) {
        String out = "Noted. I've removed this task: \n  ";
        out += tasks.get(getIndex(input)) + "\n";
        out += String.format("Now you have %d tasks in the list.", tasks.size() - 1);
        textWrapper(out);
    }

    public void showDukeTaskError() {
        textWrapper("☹ OOPS!!! The description of a task cannot be empty.");
    }

    public void showDukeGeneralError() {
        textWrapper("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showDukeEmptyListError() {
        textWrapper("You have nothing in the list!");
    }

    public void showLoadingError() {
        textWrapper("This file cant be loaded! Creating a new file called duke.txt in CWD");
    }

    public int getIndex(String input) {
        return Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
    }

    private String printList(TaskList contentList) {
        StringBuilder printStr = new StringBuilder();
        for (int i = 0; i < contentList.size(); i++) {
            String textToAdd = String.format("%d.%s%n", i + 1, contentList.get(i).toString());
            printStr.append(textToAdd);
        }
        return printStr.toString();
    }

    private void textWrapper(String a) {
        System.out.println("____________________________________________________________");
        System.out.println(a);
        System.out.println("____________________________________________________________");
    }

}
