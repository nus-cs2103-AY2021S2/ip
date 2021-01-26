import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("------------------------------------------------");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Unable to load file!");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hi! I'm Timmy!\nWhat can Timmy note down for you today?");
        System.out.println("Please type in any of these format!");
        System.out.println("todo [title]");
        System.out.println("event [title] /at [yyyy-mm-dd] [HH:MM]");
        System.out.println("deadline [title] /by [yyyy-mm-dd] [HH:MM]");
        System.out.println("list");
        System.out.println("delete [index]");
        System.out.println("done [index]");
    }

    public void showExit() {
        sc.close();

        System.out.println("Bye! Hope to see you again!");
    }

    void printList(TaskList tasklist) {
        System.out.println("Here are the tasks in your list:");

        ArrayList<Task> tasks = tasklist.getList();

        for (int j = 0; j < tasks.size(); j++) {
            System.out.println(j + 1 + "." + tasks.get(j).toString());
        }
    }

    void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    void showDeleteTask(TaskList tasklist, int taskIndex) {
        System.out.println("Ok! I've removed this task:\n" + tasklist.getList().get(taskIndex).toString());
        System.out.println("Currently, you have " + (tasklist.getList().size() - 1) + " task(s) in the list!");
    }

    void showAddTask(TaskList tasklist) {
        System.out.println("Ok! I've added this task:\n" + tasklist.getList().get(tasklist.getList().size() - 1).toString());
        System.out.println("Currently, you have " + tasklist.getList().size() + " task(s) in the list!");
    }
}
