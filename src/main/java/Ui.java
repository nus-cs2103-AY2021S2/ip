import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello! What can I do for you:>");
    }

    public void end() {
        this.sc.close();
        System.out.println("Bye. See you again!");
    }

    public void printSetDone(Task task) {
        System.out.println("Nice, I have set this task as done!");
        printTask(task);
    }

    public void printDeleteTask(Task task) {
        System.out.println("Noted. I have deleted this task:");
        printTask(task);
    }

    public void printAddTask(Task task) {
        System.out.println("Got it. I have added this task:");
        printTask(task);
    }

    public void printTask(Task task) {
        System.out.println(task.toString());
    }


    public void printList() {

    }

    public void printEmptyList() {
        System.out.println("You have no tasks in the list!");
    }

    public String getInput() {
        while (sc.hasNextLine()) {
            return sc.nextLine();
        }

        return "null";
    }

    public void showLoadingError() {
        System.out.println("An error has occurred while reading duke.txt");
    }

    public void showWritingError() {
        System.out.println("Error occurred while writer to file.");
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

}
