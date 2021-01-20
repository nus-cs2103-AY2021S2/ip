import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _ ___ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================");
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println("==============================\n");

        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                echo("Bye. Hope to see you again soon!");
            } else {
                echo(input);
            }
        }
    }

    public static void echo(String input) {
        System.out.println("==============================");
        System.out.println(input);
        System.out.println("==============================\n");
    }
}
