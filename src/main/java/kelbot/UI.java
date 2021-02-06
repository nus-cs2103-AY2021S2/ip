package kelbot;

public class UI {
    /**
     * Initializes UI.
     */
    public UI() {
    }
    /**
     * Print out Goodbye message.
     */
    public String sayGoodbye() {
        return "Bye le bye! See you next time!";
    }
    /**
     * Print out full list.
     * @param taskList The task list to be printed.
     * @return String to be printed.
     */
    public String printList(TaskList taskList) {
        return taskList.toString();
    }
    /**
     * Print out message to show that task has been done.
     * @param task The task that has been marked done.
     * @return String to be printed.
     */
    public String printDone(Task task) {
        return "Well done! You have completed this task!\n" + task;
    }
    /**
     * Print out message to show that task has been deleted.
     * @param task The task that has been deleted.
     * @return String to be printed.
     */
    public String printDelete(Task task) {
        return "Noted! You have deleted this task!\n" + task;
    }
    /**
     * Print out message to show that task has been added.
     * @param task The task that has been added.
     * @param taskListSize The number of tasks in the task list.
     * @return String to be printed.
     */
    public String printAdd(Task task, int taskListSize) {
        if (taskListSize == 1) {
            return "Okay! I have added:\n" + task + "\nNow there is " + taskListSize + " task on the list";
        } else {
            return "Okay! I have added:\n" + task + "\nNow there are " + taskListSize + " tasks on the list";
        }
    }
    /**
     * Prints the relevant tasks from given task list.
     * @param taskList The task list to be printed.
     * @return String to be printed.
     */
    public String printRelevantTasks(TaskList taskList) {
        return "These are the tasks that have your keyword\n" + taskList;
    }
}
