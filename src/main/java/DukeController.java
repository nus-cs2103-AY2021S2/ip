import java.util.Scanner;

public class DukeController {

    DukeController() {
    }

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void run() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        while (!line.equals("bye")) {
            System.out.println(line + "\n");
            line = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}

