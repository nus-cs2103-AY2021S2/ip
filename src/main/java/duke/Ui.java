package duke;

public class Ui {
    private final String SPACER = "----------------------------------------------------";

    private void printSpacer() {
        System.out.println(SPACER);
    }

    /**
     * Prints the greeting when Duke is started.
     */
    public String printGreeting() {
        String str = "Hello my name is Mr. C!" +
                "\nYou may type the command 'help' to see my list of commands." +
                "\nHow may I assist you?";
        return str;
    }

    /**
     * Prints the goodbye message when Duke is exited.
     */
    public String printBye() {
        String str = "Farewell sir/ma'am. I hope to see you again soon.";
        return str;
    }

    private String printTask(Task task) {
        return task.toString();
    }

    /**
     * Prints a message for when a task is added to the list.
     *
     * @param task Task to be added.
     * @param size Number of tasks currently in the list.
     */
    public String printAddTask(Task task, int size) {
        String str = "I have added the following task to your list:\n" +
                printTask(task) +
                String.format("\nThere are %d tasks in your list. I hope this pleases you.", size);
        return str;
    }

    /**
     * Prints a message for when a task is marked as done.
     *
     * @param task Task to be marked as done.
     */
    public String printDoneTask(Task task) {
        String str = "Congratulations on conquering this task:\n" +
                printTask(task) +
                "\nYou are one step closer to victory";
        return str;
    }

    /**
     * Prints a message for when a task is being removed from the list.
     *
     * @param task Task to be removed.
     */
    public String printDeleteTask(Task task) {
        String str = "I have removed the following task from your list:\n" +
                printTask(task) +
                "\nDoes this bring you the satisfaction you so crave?";
        return str;
    }

    /**
     * Prints all the tasks in the list.
     *
     * @param taskList List of tasks.
     */
    public String printTaskList(TaskList taskList) {
        String str = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.getSize(); i++) {
            str += String.format("%d. ", i) +
                    taskList.getTask(i - 1).toString() +
                    "\n";
        }
        str += "Do what you will with this information.";
        return str;
    }

    /**
     * Prints the error message.
     *
     * @param e Exception that has occurred.
     */
    public String printError(Exception e) {
        return e.toString();
    }

    /**
     * Prints the help message.
     */
    public String printHelp() {
        String str = "These are the following commands that I understand:" +
                "\n'help' (shows the list of commands)" +
                "\n'list' (shows the list of tasks you have on your list)" +
                "\n'todo (task description)' (adds a To Do task to your list)" +
                "\n'deadline (task description) /by (DD/MM/YYYY TIME)' (adds a Deadline task to your list)" +
                "\n'event (task description) /from (DD/MM/YYYY TIME) /to (DD/MM/YYYY TIME)' (adds an Event task to your list)" +
                "\n'done (valid index)' (checks a task on your list)" +
                "\n'delete (valid index)' (removes a task from your list)" +
                "\n'find (keyword)' (searches for all tasks in your list containing the keyword)" +
                "\n'bye' (exits Duke)" +
                "\nDo omit the parentheses in the actual command." +
                "\nI pray that I will be of use to you.";
        return str;
    }

    /**
     * Prints the tasks that have been found.
     *
     * @param taskList Task list that only contains found tasks.
     */
    public String printFoundTaskList(TaskList taskList) {
        String str = "Here are the tasks that fit your search criteria:\n";
        for (int i = 1; i <= taskList.getSize(); i++) {
            str += String.format("%d. ", i) +
                    taskList.getTask(i - 1).toString() + "\n";
        }
        str += "I can only pray that I have been of use to you.";
        return str;
    }
}
