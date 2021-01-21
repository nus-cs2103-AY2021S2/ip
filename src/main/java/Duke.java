import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke. What can I do for you?\n");
        chat();
    }

    public static void chat() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            scanner.close();
        } else {
            System.out.println(input + "\n");
            chat();
        }
    }
}
