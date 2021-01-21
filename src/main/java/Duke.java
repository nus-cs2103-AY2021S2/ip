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

        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");

        boolean Done = false;
        while (!Done) {
            switch (sc.next()) {
                case "list":
                    System.out.println("    ____________________________________________________________\n " +
                            "    list\n" +
                            "    ____________________________________________________________");
                    break;

                case "blah":
                    System.out.println("    ____________________________________________________________\n " +
                            "    blah\n" +
                            "    ____________________________________________________________");
                    break;

                case "bye":
                    System.out.println("    ____________________________________________________________\n " +
                            "    Bye. Hope to see you again soon!\n" +
                            "    ____________________________________________________________");
                    Done = true;
                    break;

                default:
                    System.out.println("What have you done...");
                    break;
            }
        }
    }
}
