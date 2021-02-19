package duke;

import duke.task.Task;

import java.util.List;

/**
 * Ui is a class representing the user interface. The main function of the class is
 * to output result on the console.
 */
public class Ui {
    /**
     * Returns the error message.
     *
     * @param msg The message that caused the error.
     * @return The error message.
     */
    String showError(String msg) {
        return msg;
    }

    /**
     * Returns a goodbye message before the end of program.
     *
     * @return A goodbye message to the user.
     */
    public String showExit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        return exitMessage;
    }

    /**
     * Returns a loading error when the file containing data cannot be loaded
     * from the hard disk.
     *
     * @return The loading error message.
     */
    String showLoadingError() {
        String loadingFileError = "Unable to load the file. Empty list created.";
        return loadingFileError;
    }

    /**
     * Returns a message and the added task.
     *
     * @param task The added task.
     * @param size Current size of the list.
     * @return A string of message and added task.
     */
    public String showAdd(String task, int size) {
        String addMessage = "Got it. I've added this task:" + System.lineSeparator();
        return addMessage + showTaskAndSize(task, size);
    }

    /**
     * Returns when deleting a task from the list is successful.
     *
     * @param task The deleted task.
     * @param size Current size of the list.
     * @return A string of message and deleted task.
     */
    public String showDelete(String task, int size) {
        String deleteMessage = "Noted, I've removed this task: " + System.lineSeparator();
        return deleteMessage + showTaskAndSize(task, size);
    }

    /**
     * Outputs when marking a task as done is successful.
     *
     * @param task The task that is marked as done.
     * @param size Current size of the list.
     * @return A string of message and the task marked as done.
     */
    public String showDone(String task, int size) {
        String doneMessage = "Nice! I've mark this task as done" + System.lineSeparator();
        return doneMessage + showTaskAndSize(task, size);
    }

    private String showTaskAndSize(String task, int size) {
        return task + System.lineSeparator()
                + String.format("Now you have %d tasks in the list.", size);
    }

    /**
     * Lists out the current task(s) in the list. If the current list
     * is empty, outputs "There is currently no task in the list".
     *
     * @param list The list storing the tasks.
     */
    public String showCurrentList(List<Task> list) {
        if (list.isEmpty()) {
            String emptyListMessage = "There is currently no task in the list.";
            return emptyListMessage;
        } else {
            String showListMessage = "Here are the tasks in your list:" + System.lineSeparator();
            return showListMessage + showListItem(list);
        }
    }

    /**
     * Outputs the task(s) that matches the keyword.
     *
     * @param list The list storing the matching tasks.
     */
    public String showFindKeywordList(List<Task> list) {
        if (list.isEmpty()) {
            String emptyListMessage = "There is no task containing the keyword.";
            return emptyListMessage;
        } else {
            String showListMessage = "Here are the matching tasks in your list:" + System.lineSeparator();
            return showListMessage + showListItem(list);
        }
    }

    private String showListItem(List<Task> list) {
        String res = "";
        int index = 1;
        for (Task t: list) {
            res += String.format("%d. %s", index, t.toString())
                    + System.lineSeparator();
            index++;
        }

        return res;
    }

    public String showPrior(String task, String priority) {
        String priorDoneMessage = "Noted! I have successfully set the task to " + priority + " priority: ";
        return priorDoneMessage + System.lineSeparator() + task;
    }
}
