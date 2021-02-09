package duke;

import java.util.Scanner;

/**
 * Handles receiving user input and outputting relevant textual information for the user to see.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows details of a Task that's been added to the TaskList.
     * @param task Task that's added to the TaskList.
     * @param listSize Size of the TaskList, after the Task is added.
     * @return Message confirming that the task has been added.
     */
    public String showAddedTask(Task task, int listSize) {
        String msg = String.format("I've added this task: %s\nYou now have %d items on your todo list.",
                task.toString(),
                listSize);
        return msg;
    }

    /**
     * Shows details of a Task that's been removed from the TaskList.
     * @param task Task that's been removed from the TaskList.
     * @param listSize Size of the TaskList, after the Task is removed.
     * @return Message confirming that the task has been removed.
     */
    public String showRemovedTask(Task task, int listSize) {
        String msg = String.format("I've removed this task: %s\nYou now have %d items on your todo list.",
                task.toString(),
                listSize);
        return msg;
    }

    /**
     * Shows details of a Task that's been marked as done/completed.
     * @param task Task that's been marked as done/completed.
     * @return Message confirming that the task has been marked as done/completed.
     */
    public String showDoneTask(Task task) {
        assert task.isDone() : " Task not properly marked as done";
        String msg = String.format("Congrats! The following task has been marked as done:\n  %s",
                task.toString());
        return msg;
    }

    /**
     * Returns the contents of a TaskList in a neat format.
     * @param tasks
     * @return
     */
    public String displayList(TaskList tasks) {
        StringBuilder items = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            items.append(String.valueOf(i) + ". " + tasks.get(i).toString() + "\n");
        }
        String output = items.toString().trim();
        if (output.length() > 0) {
            output = "Here's all the matches I found:\n" + output;
        } else {
            assert (tasks.size() == 0) : " Non-empty list displayed as empty";
            output = "I couldn't find anything!";
        }
        return output;
    }
}