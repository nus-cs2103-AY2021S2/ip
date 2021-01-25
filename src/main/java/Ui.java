import java.util.Scanner;

public class Ui {

    private static final String LINE = "____________________________________________________________";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String askForInput() {
        return scanner.nextLine();
    }

    public void greet() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?";
        print(greeting);
    }

    public void bye() {
        String output = "Bye. Hope to see you again soon!";
        print(output);
    }

    public void displayAdded(Task task) {
        String output = "I've added this task: \n" + task.toString();
        print(output);
    }

    public void displayDone(Task task) {
        String output = "Marked as done: \n" + task;
        print(output);
    }

    public void displayDeleted(Task task) {
        String output = "I removed this task: \n" + task;
        print(output);
    }

    public void displayError(String message) {
        String output = "ERROR: " + message;
        print(output);
    }

    public void print(String input) {
        System.out.println(LINE + "\n" + input + "\n" + LINE + "\n");
    }
}