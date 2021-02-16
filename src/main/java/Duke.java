import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "         ____        _        \n"
                + "        |  _ \\ _   _| | _____ \n"
                + "        | | | | | | | |/ / _ \\\n"
                + "        | |_| | |_| |   <  __/\n"
                + "        |____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "        ____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(logo);
        System.out.println("        Hello! I'm Duke\n        What can I do for you?");
        System.out.println(horizontalLine);

        Scanner sc = new Scanner(System.in);
        String cmd = "";
        while (!cmd.equals("bye")) {
            cmd = sc.next();
            if (cmd.equals("bye")) {
                System.out.println(horizontalLine);
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            }
            System.out.println(horizontalLine);
            System.out.println("        " + cmd);
            System.out.println(horizontalLine);
        }




    }
}
