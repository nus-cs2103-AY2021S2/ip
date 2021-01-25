public class Ui {

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I help you with today! :-)");
    }

    public void responseToAddTask(Task task, int taskListSize) {
        System.out.println("Done! One new task:\n" + task.toString() + "\nNow you have " +
                taskListSize + ((taskListSize == 1) ? " task" : " tasks") + " in the list");
    }

    public void responseToDelete(Task task, int taskListSize) {
        System.out.println("Noted, I've removed this task:\n" + task.toString() +
                "\nNow you have " + taskListSize + ((taskListSize == 1) ? " task" : " tasks") + " in the list");
    }

    public void responseToBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void responseToList(int taskListSize) {
        if (taskListSize != 0) {
            System.out.println("Here are the tasks in your list:");
        } else {
            System.out.println("Your list is currently empty! Let's start adding tasks!");
        }
    }

    public void responseToDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    public static void responseToFind() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public static void responseToNoMatches() {
        System.out.println("There are no matching tasks! Time to add one!");
    }


}
