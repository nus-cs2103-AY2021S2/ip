import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        greet();
        echo(sc);
    }

    public static void greet() {
        System.out.println("\t____________________________________________________________\n"
                        + "\tHello! I'm Duke\n\tWhat can I do for you?\n"
                        + "\t____________________________________________________________\n");
    }

    public static void echo(Scanner sc) {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("\t____________________________________________________________\n"
                            + "\t" + input + "\n"
                            + "\t____________________________________________________________\n");
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                        + "\tBye. Hope to see you again soon!\n"
                        + "\t____________________________________________________________\n"
        );
    }
}
