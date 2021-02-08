// DEPRECATED CLASS, REMNANTS OF WHEN DUKE STILL USES TEXT UI

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the greeting message.
     */
    public void printGreetings() {
        String logo = "$$    $$                                $$\n"
                    + "$$    $$                                $$\n"
                    + "$$    $$   $$$$$$   $$$$$$$    $$$$$$$  $$$$$$$   $$$$$$ $$$$    $$$$$$   $$$$$$$\n"
                    + "$$$$$$$$  $$    $$  $$    $$  $$        $$    $$  $$   $$   $$        $$  $$    $$\n"
                    + "$$    $$  $$$$$$$$  $$    $$  $$        $$    $$  $$   $$   $$   $$$$$$$  $$    $$\n"
                    + "$$    $$  $$        $$    $$  $$        $$    $$  $$   $$   $$  $$    $$  $$    $$\n"
                    + "$$    $$   $$$$$$$  $$    $$   $$$$$$$  $$    $$  $$   $$   $$   $$$$$$$  $$    $$\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Greetings Boss, good to see you.\n" + "What can I do for you?\n");
    }

    public void printInputPrompt() {
        System.out.println("\nHit me up boss.");
    }

    public void printCommand(Command command) {
        System.out.println(command.toDukeOutput());
    }

    public void printTask(Task task) {
        System.out.println(task);
    }
}
