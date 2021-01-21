import java.util.ArrayList;
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
        ArrayList<String> taskList = new ArrayList<>();

        while (!Done) {
            String nextCommand = sc.nextLine();
            switch (nextCommand) {
                case "list":
                    System.out.println("    ____________________________________________________________");
                    for (int taskNum = 0; taskNum < taskList.size(); taskNum++) {
                        System.out.println((taskNum + 1) + ". " + taskList.get(taskNum));
                    }
                    System.out.println("    ____________________________________________________________");
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
                    System.out.println("    ____________________________________________________________");
                    taskList.add(nextCommand);
                    System.out.println("    added: " + nextCommand);
                    System.out.println("    ____________________________________________________________");
                    break;
            }
        }
    }
}
