import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "    ____________________________________________________________\n";
        //initialising greeting to be printed and print greeting.
        String greeting = line
                + "    Hello! I'm Duke\n"
                + "    What can I do for you?\n"
                + line;
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        String command = "";
        //while loop to echo commands of the user
        while(!command.equals("bye")) {
            command = sc.nextLine();
            System.out.println(line + "    " + command + "\n" + line);
        }

        //print out the bye message
        String byeMessage = line
                + "    Bye. Hope to see you again soon!\n"
                + line;
        System.out.println(byeMessage);
    }
}
