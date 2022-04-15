package kelbot;

public class UI {
    /**
     * Initializes UI.
     */
    public UI() {
    }
    /**
     * Prints out Goodbye message.
     */
    public String sayGoodbye() {
        return "Bye le bye! See you next time!";
    }
    /**
     * Prints out full list.
     * @param taskList The task list to be printed.
     * @return String to be printed.
     */
    public String printList(TaskList taskList) {
        if (taskList.toString().equals("")) {
            return "There are no tasks in your list";
        } else {
            return taskList.toString();
        }
    }
    /**
     * Prints the task has been done.
     * @param task The task that has been marked done.
     * @return String to be printed.
     */
    public String printDone(Task task) {
        return "Well done! You have completed this task!\n" + task;
    }
    /**
     * Prints out message to show that task has been deleted.
     * @param task The task that has been deleted.
     * @return String to be printed.
     */
    public String printDelete(Task task) {
        return "You have deleted this task!\n" + task;
    }
    /**
     * Prints the task that has been added.
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
        return "These are the tasks that have your keyword:\n" + taskList;
    }
    /**
     * Prints the task that has just been snoozed.
     * @param task The task that is snoozed.
     * @return String to be printed.
     */
    public String printSnooze(Task task) {
        return "You have rescheduled this task:\n" + task;
    }
    /**
     * Prints the task that has just been tagged.
     * @param task The task that is tagged.
     * @return String to be printed.
     */
    public String printTag(Task task) {
        return "You have tagged this task:\n" + task;
    }
}
