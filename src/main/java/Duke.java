import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        DukeBot bot = new DukeBot("Duke");
        Scanner sc = new Scanner(System.in);
        System.out.println(bot);
        String command;

        while (sc.hasNext()) {
            command = sc.next();
            if (command.equals("bye")) {
                System.out.println("\t\t" + "See you again soon!");
                sc.close();
                break;
            }
            System.out.println("\t\t" + command);

        }
    }
}
