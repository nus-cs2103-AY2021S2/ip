import java.util.Scanner;

public class Ui {
    public final String INVALID_ARGUMENT = "Sorry, you have supplied invalid arguments.";
    public final String INVALID_COMMAND = "Sorry, I don't understand the command.";
    public final String ADD_TASK = "I have added the task to the task list!";
    public final String SHOW_TASKS = "Here are your tasks!";
    public final String COMPLETE_TASK = "I have marked that task as done!";
    public final String DELETE_TASK = "I have deleted that task from the task list!";
    private String name;

    public Ui(String n) {
        name = n;
    }

    public void echo(String message) {
        System.out.println(name + ": " + message);
    }

    public void greeting() {
        this.echo("Hi, I am " + name + "! How can I help you?");
    }

    public void closing() {
        this.echo("Goodbye!");
    }

    public Command prompt(Scanner sc) {
        String[] input = sc.nextLine().split(" ");
        return Parser.parse(input);
    }
}
