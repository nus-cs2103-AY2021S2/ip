import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);

    private static void introduction() {
        String loading = "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";
        System.out.println(loading);
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);
    }

    private static void endProgram() {
        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(endMessage);
        scanner.close();
    }

    private static void run() {
        introduction();
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                endProgram();
                break;
            } else {
                System.out.println(input);
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}