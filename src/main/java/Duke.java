import java.util.Scanner;

public class Duke {

    public Duke() {
        greet();
    }

    private void printWithStyle(String output) {
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + output);
        System.out.println("    ________________________________________________________________");
    }
    void greet() {
        printWithStyle("Hello! I'm Duke\nWhat can I do for you?");
    }

    void echo(String userInput) {
        printWithStyle(userInput);
    }

    void bye() {
        printWithStyle("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            duke.echo(input);
            input = scanner.nextLine();
        }
        duke.bye();

    }
}
