public class Ui {
    private String greet;
    private String bye;

    Ui() {
        greet = "Hello! I'm Duke \n What can I do for you?";
        bye = "Bye. Hope to see you again soon!";
    }

    public void welcomeUser() {
        System.out.println(greet);
    }

    public void userLeaving() {
        System.out.println(bye);
    }

    /**
     * String to print when a user is done with the given task in the list.
     * @param task
     */
    public void userDoneTask(String task) {
        System.out.println("Nice! I've marked this task as done:\n " + task + ")");
    }

    /**
     * String to print when a user adds a new Task to the list.
     * @param list
     */
    public void userAddTask(TaskList list) {
        System.out.println("Got it. I've added this task: \n  "
                            + list.getLastAddedTask().toString()
                            + "\nNow you have " + String.valueOf(list.size()) + " tasks in the list.");
    }

    /**
     * String to print when a user deletes the given task from the given list.
     * @param task that user deleted
     * @param list that user deleted from
     */
    public void userDeleteTask(Task task, TaskList list) {
        System.out.println("Noted. I've removed this task: \n "
                + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    /**
     * Prints header string before the filtered list of task is printed.
     */
    public void tellUserListFound() {
        System.out.println("Here are the matching tasks in your list: ");
    }

}
