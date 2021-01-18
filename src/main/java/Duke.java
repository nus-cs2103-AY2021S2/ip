import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String partition = "\n/**********************************************************/\n";

        System.out.println(logo + "\nHello! I'm Duke.\n" + "What can I do for you?\n" + partition);
        command = sc.nextLine();

        while(!command.equals("bye")) {
            System.out.println("Your command is: " + command + "\n" + partition);
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n" + logo);
    }
}
