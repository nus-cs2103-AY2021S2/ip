import java.util.Scanner;

public class Ui {
    private Scanner s = new Scanner(System.in);

    public void showLine() {
        System.out.println("----------------------------------------------");
    }

    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.showLine();
        System.out.println("Hello! I`m Duke");
        System.out.println("How can i help you?");
        this.showLine();
    }

    private String readLine() {
        return s.nextLine();
    }

    public String askFilePath() {
        System.out.println("Please enter file name : ");
        return this.readLine();
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public String readCommand() {
        return this.readLine();
    }

    public void printTask(String numbering, String task) {
        System.out.printf("%2s %s\n", numbering, task);
    }

    private void printExecuteResult(String message, String task , int numTasks, String numbering) {
        System.out.println(message);
        printTask(numbering,task);
        System.out.printf("Now you have %d task in the list\n", numTasks);
    }

    public void showSuccessMarkDone(String task, int numTasks) {
        String message = "Got it. I`ve mark this task as done:";
        this.printExecuteResult(message, task, numTasks, "");
    }

    public void showSuccessDeleteTask(String task, int numTasks) {
        String message = "Noted. I`ve removed this task:";
        this.printExecuteResult(message, task, numTasks, "");
    }

    public void showSuccessAddTask(String task, int numTasks) {
        String message = "Got it. I`ve added this task:";
        this.printExecuteResult(message, task, numTasks, "");
    }

    public void showSuccessSearch() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void showFailSearch(String searchTerm) {
        System.out.println("There are no matching task with " + searchTerm);
    }
}
