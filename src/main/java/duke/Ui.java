package duke;

/**
 * Represents the Ui of Duke CLI application with methods to print the output according to the input given.
 */
public class Ui {

    /**
     * Gets the welcome greeting when user first runs the Duke program.
     *
     * @return Welcome greeting as String
     */
    public String getWelcomeGreetingString() {
        return "\nHello! I am Duke\n" + "What can I do for you?\n";
    }

    /**
     * Gets the exit message when user executes command to quit the duke application.
     *
     * @return Exit message, when user inputs 'bye' command, as String
     */
    public String getExitMessageString() {
        return "GoodBye. Hope to see you again soon!\n";
    }

    /**
     * Gets the tasks is users TaskList line by line.
     *
     * @param userList TaskList of the user
     * @return Tasks in task list as String if TaskList not empty. If empty, returns String stating list is empty
     */
    public String getPrintTaskListString(TaskList userList) {
        String stringToBeReturned = "";
        for (int i = 0; i < userList.getTaskListSize(); i++) {
            stringToBeReturned += (i + 1) + "." + userList.getTask(i).toString() + "\n";
        }
        return stringToBeReturned.length() == 0
                ? "Your list is empty.\n"
                : stringToBeReturned + "\nYou have " + userList.getTaskListSize() + " tasks in your list.\n";
    }

    /**
     * Gets the message when a task is assigned as "done".
     *
     * @param userTaskList TaskList of the user.
     * @param taskNumber   Task number in the list that was marked as done.
     * @return message for a task marked done as String
     * @see TaskList
     * @see Task
     */
    public String getPrintDoneTaskString(TaskList userTaskList, int taskNumber) {
        Task doneTask = userTaskList.getTask(taskNumber - 1);
        doneTask.markAsDone();
        String doneTaskString = String.format("Nice! I've marked this task as done:\n[%s] \n%s\n",
                doneTask.getStatusIcon(),
                doneTask.getTaskDetail());
        return doneTaskString;
    }

    /**
     * Gets the message when a task is added to the Task list.
     *
     * @param userTaskList Task list of the user.
     * @param task task to be added to the Task list.
     * @return message upon adding task as a String.
     * @see TaskList
     * @see Task
     */
    public String getPrintAddedTaskString(TaskList userTaskList, Task task) {
        String addedTaskString = "";
        addedTaskString += "Got it. I've added this task:\n";
        addedTaskString += task.toString() + "\n";
        addedTaskString += "Now you have " + (userTaskList.getTaskListSize()) + " tasks in the list.\n";
        return addedTaskString;
    }

    /**
     * Gets the message when task is deleted from Task List.
     *
     * @param userTaskList Task list of the user.
     * @param taskToBeDeleted Task to be deleted from the Task list.
     */
    public String getPrintDeletedTaskString(Task taskToBeDeleted, TaskList userTaskList) {
        String deletedTaskString = "";
        deletedTaskString += "Noted. I've removed this task\n";
        deletedTaskString += taskToBeDeleted.toString() + "\n";
        deletedTaskString += "Now you have " + (userTaskList.getTaskListSize()) + " tasks in the list.\n";
        return deletedTaskString;
    }

    /**
     * Gets all tasks found when key search words are entered by user.
     *
     * @param tasksFound : TaskList of the tasks found with the required key words.
     * @return all tasks found and message when "find" command executed.
     */
    public String getPrintFoundTasksString(TaskList tasksFound) {
        String foundTasksString = "Here are the matching tasks in your list:\n";
        return tasksFound.getTaskListSize() == 0
                ? foundTasksString + "Sorry. No tasks found :-(\n"
                : foundTasksString + getPrintTaskListString(tasksFound) + "\n";
    }
}
