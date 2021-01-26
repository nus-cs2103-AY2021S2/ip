import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void welcome() {
        System.out.println(DIVIDER);
        System.out.println("Welcome to Duke!");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    public void quit() {
        System.out.println(DIVIDER);
        System.out.println("Bye! Hope to see you again :)");
        System.out.println(DIVIDER);
    }

    public void borderPrint(String msg) {
        System.out.println(DIVIDER);
        System.out.println(msg);
        System.out.println(DIVIDER);
    }

    public void showAddedTask(Task task, int listSize) {
        String msg = String.format("I've added this task: %s\nYou now have %d items on your todo list.",
                task.toString(),
                listSize);
        borderPrint(msg);
    }

    public void showRemovedTask(Task task, int listSize) {
        String msg = String.format("I've removed this task: %s\nYou now have %d items on your todo list.",
                task.toString(),
                listSize);
        borderPrint(msg);
    }

    public void showDoneTask(Task task) {
        String msg = String.format("Congrats! The following task has been marked as done:\n  %s",
                task.toString());
        borderPrint(msg);
    }

    public void showError(Exception e) {
        borderPrint(e.getMessage());
    }

    public void displayList(TaskList tasks) {
        StringBuilder items = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            items.append(String.valueOf(i) + ". " + tasks.get(i).toString() + "\n");
        }
        String output = items.toString().trim();
        if (output.length() > 0) {
            output = "Here's all the matches I found:\n" + output;
        } else {
            output = "I couldn't find anything!";
        }
        borderPrint(output);
    }
}