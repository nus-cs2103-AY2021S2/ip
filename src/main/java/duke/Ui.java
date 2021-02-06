package duke;

/**
 * Represents the Ui of Duke CLI application with methods to print the output according to the input given.
 */
public class Ui {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";

    /**
     * Prints the welcome greeting when user first runs the Duke program
     */
    public void printWelcomeGreeting() {
        System.out.println(HORIZONTAL_RULE + "\nHello! I am Duke\n" + "What can I do for you?\n" + HORIZONTAL_RULE);
    }

    public String getWelcomeGreetingString() {
        return "\nHello! I am Duke\n" + "What can I do for you?\n";
    }

    /**
     * Prints the exit message when user executes command to quit the duke application.
     */
    public void printExitMessage() {
        System.out.println("GoodBye. Hope to see you again soon!\n" + HORIZONTAL_RULE);
    }

    public String getExitMessageString() {
        return "GoodBye. Hope to see you again soon!\n";
    }

    /**
     * Prints a horizontal line (string of "-" of 80 characters)
     */
    public void printHorizontalRule() {
        System.out.println(HORIZONTAL_RULE);
    }

    /**
     * Prints the tasks is users TaskList line by line
     *
     * @param userList TaskList of the user
     */
    public void printTaskList(TaskList userList) {
        for (int i = 0; i < userList.getTaskListSize(); i++) {
            System.out.println(i + 1 + "." + userList.getTask(i).toString());
        }
        System.out.println(HORIZONTAL_RULE);
    }

    public String getPrintTaskListString(TaskList userList) {
        String stringToBeReturned = "";
        for (int i = 0; i < userList.getTaskListSize(); i++) {
            stringToBeReturned += (i + 1) + "." + userList.getTask(i).toString();
        }
        return stringToBeReturned.length() == 0 ? "Your list is empty.\n" : stringToBeReturned + "\n";
    }

    /**
     * Prints the message when a task is assigned as "done".
     *
     * @param userTaskList TaskList of the user.
     * @param taskNumber   Task number in the list that was marked as done.
     * @see TaskList
     * @see Task
     */
    public void printDoneTask(TaskList userTaskList, int taskNumber) {
        Task doneTask = userTaskList.getTask(taskNumber - 1);
        doneTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + doneTask.getStatusIcon() + "] " + doneTask.getTaskDetail());
        System.out.println(HORIZONTAL_RULE);
    }

    public String getPrintDoneTaskString(TaskList userTaskList, int taskNumber) {
        String doneTaskString = "";
        Task doneTask = userTaskList.getTask(taskNumber - 1);
        doneTask.markAsDone();
        doneTaskString += ("Nice! I've marked this task as done:\n" + "[" + doneTask.getStatusIcon() + "] \n" +
                doneTask.getTaskDetail() + "\n");
        return doneTaskString;
    }

    /**
     * Prints the message when a task is added to the Task list.
     *
     * @param userTaskList Task list of the user.
     * @param task task to be added to the Task list.
     * @see TaskList
     * @see Task
     */
    public void printAddedTask(TaskList userTaskList, Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        userTaskList.addTask(task);
        System.out.println("Now you have " + (userTaskList.getTaskListSize()) + " tasks in the list.");
        System.out.println(HORIZONTAL_RULE);
    }

    public String getPrintAddedTaskString(TaskList userTaskList, Task task) {
        String addedTaskString = "";
        addedTaskString += "Got it. I've added this task:\n";
        addedTaskString += task.toString() + "\n";
        userTaskList.addTask(task);
        addedTaskString += "Now you have " + (userTaskList.getTaskListSize()) + " tasks in the list.\n";
        return addedTaskString;

    }

    /**
     * Prints the message when task is deleted from Task List.
     *
     * @param userTaskList Task list of the user.
     * @param taskNumber Task to be added to the Task list.
     */
    public void printDeletedTask(TaskList userTaskList, int taskNumber) {
        Task taskToBeDeleted = userTaskList.removeTask(taskNumber - 1);
        System.out.println("Noted. I've removed this task");
        System.out.println(taskToBeDeleted);
        System.out.println("Now you have " + (userTaskList.getTaskListSize()) + " tasks in the list.");
        System.out.println(HORIZONTAL_RULE);
    }

    public String getPrintDeletedTaskString(TaskList userTaskList, int taskNumber) {
        Task taskToBeDeleted = userTaskList.removeTask(taskNumber - 1);
        String deletedTaskString = "";
        deletedTaskString += "Noted. I've removed this task\n";
        deletedTaskString += taskToBeDeleted.toString() + "\n";
        deletedTaskString += "Now you have " + (userTaskList.getTaskListSize()) + " tasks in the list.\n";
        return deletedTaskString;
    }

    public void printFoundTasks(TaskList tasksFound) {
        System.out.println("Here are the matching tasks in your list:");
        if (tasksFound.getTaskListSize() == 0) {
            System.out.println("Sorry. No tasks found :-(");
        } else {
            this.printTaskList(tasksFound);
        }
    }

    public String getPrintFoundTasksString(TaskList tasksFound) {
        String foundTasksString = "Here are the matching tasks in your list:\n";
        return tasksFound.getTaskListSize() == 0
                ? foundTasksString + "Sorry. No tasks found :-(\n"
                : foundTasksString + getPrintTaskListString(tasksFound) + "\n";
    }
}
