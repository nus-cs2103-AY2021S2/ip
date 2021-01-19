import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String tab = "     ";
        String line = "     ____________________________________________________________";
        System.out.println(line + "\n"
                + tab + "Hello! I'm Duke\n"
                + tab + "What can I do for you?\n"
                + line);

        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            String input = scan.next();

            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println(tab + "Bye. Hope to see you again soon!");
            } else {
                System.out.println(tab + input);
            }

            System.out.println(line);
        }
    }
}
