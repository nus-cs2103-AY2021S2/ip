package duke;

/**
 * Handles receiving user input and outputting relevant textual information for the user to see.
 */
public class Ui {

    /**
     * Shows the details of every Task in the TaskList.
     * @param tasks TaskList to show Tasks from.
     * @return String representation of the Tasks in the TaskList.
     */
    public String showAllTasks(TaskList tasks) {
        String msg;

        if (tasks.size() > 0) {
            msg = String.format("Here's the current state of your to-do list:\n%s",
                    displayListNumbered(tasks));
        } else {
            msg = "Your to-do list is empty! Congratulations!";
        }

        return msg;
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
     * @param tasks Tasks that are being removed from the list.
     * @param listSize Size of the TaskList, after the Task is removed.
     * @return Message confirming that the tasks have been removed.
     */
    public String showRemovedTasks(TaskList tasks, int listSize) {
        String msg;

        if (tasks.size() > 0) {
            msg = String.format("Congrats! The following tasks have been removed from your to-do list:\n%s"
                    + "\nYou now have %d tasks on your to-do list.",
                    displayList(tasks),
                    listSize);
        } else {
            msg = "I couldn't find any of those tasks to remove!";
        }

        return msg;
    }

    /**
     * Shows details of a Task that's been marked as done/completed.
     * @param tasks Tasks that are being marked as done/completed.
     * @return Message confirming that the tasks have been marked as done/completed.
     */
    public String showDoneTasks(TaskList tasks) {
        String msg;

        if (tasks.size() > 0) {
            msg = String.format("Congrats! The following tasks have been marked as done:\n%s",
                    displayList(tasks));
        } else {
            msg = "I couldn't find any of those tasks to mark as done!";
        }

        return msg;
    }


    /**
     * Shows the details of Tasks that have been found from the TaskList.
     * @param tasks TaskList of Tasks found via a search.
     * @return String representation of the found Tasks in the TaskList.
     */
    public String showFoundTasks(TaskList tasks) {
        String msg;

        if (tasks.size() > 0) {
            msg = String.format("Here's all the matches I found:\n%s",
                    displayList(tasks));
        } else {
            msg = "I couldn't find anything matching that!";
        }

        return msg;
    }

    /**
     * Returns the contents of a TaskList in a neat format, with tasks numbered from 1.
     * @param tasks TaskList containing the tasks to be displayed.
     * @return String summarizing all the tasks in the TaskList.
     */
    private String displayListNumbered(TaskList tasks) {
        String output;

        assert (tasks.size() > 0) : "Calling displayListNumbered() on an empty list";

        StringBuilder items = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            items.append(String.valueOf(i) + ". " + tasks.get(i).toString() + "\n");
        }

        output = items.toString().trim();
        return output;
    }

    /**
     * Returns the contents of a TaskList in a neat format, with tasks not numbered.
     * @param tasks TaskList containing the tasks to be displayed.
     * @return String summarizing all the tasks in the TaskList.
     */
    private String displayList(TaskList tasks) {
        String output;

        assert (tasks.size() > 0) : "Calling displayList() on an empty list";

        StringBuilder items = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            items.append("- " + tasks.get(i).toString() + "\n");
        }

        output = items.toString().trim();
        return output;
    }
}