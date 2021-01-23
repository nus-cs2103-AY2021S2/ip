import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public Ui() {
        greetUser();
    }

    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nHow can I help?\n");
    }

    public void showTaskDeleted(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
    }

    public void showLoadingError() {
        System.out.println("File ./savedTasks.txt not found! Creating one...\n");
    }

    public void showTaskAdded() {
        System.out.println("added!");
    }

    public String readUserCommand() {
       return sc.nextLine();
    }

    /**
     * Chatbot prints out a farewell message before ending the session
     */
    public void showGoodbyeMessage() {
       System.out.println("Bye. Hope to see you soon!\n");
    }

    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println();
    }

    /**
     * Chatbot prints out line-by-line all of the user's tasks
     * stored in the task list in that given session.
     */
    public void showTaskList() {
        System.out.println("Here are your tasks!");
        for(int i = 1; i <= TaskList.taskList.size(); i++) {
            Task task = TaskList.taskList.get(i-1);
            System.out.print(i + ".");
            System.out.println(task);
        }
        System.out.println();
    }

    /**
     * prints out the number of current tasks in the task list
     */
    public void showNumberOfTasks() {
        int numTasks = TaskList.taskList.size();
        System.out.println("you have " + numTasks + " tasks in your list");
        System.out.println();
    }
}
