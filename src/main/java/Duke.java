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
        ArrayList<Task> taskList = new ArrayList<>();

        while (!Done) {
            String nextCommand = sc.next();
            switch (nextCommand) {
                case "list":
                    System.out.println("    ____________________________________________________________");
                    for (int taskNum = 0; taskNum < taskList.size(); taskNum++) {
                        System.out.println((taskNum + 1) + "." + taskList.get(taskNum));
                    }
                    System.out.println("    ____________________________________________________________");
                    break;

                case "todo":
                    ToDo nextToDo = new ToDo(sc.nextLine());
                    taskList.add(nextToDo);
                    System.out.println("    ____________________________________________________________\n" +
                            "    Got it. I've added this task: \n");
                    System.out.println("       " + nextToDo + "\n" +
                            "     Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                    break;

                case "done":
                    System.out.println("    ____________________________________________________________\n" +
                            "    Nice! I've marked this task as done:");
                    Task targetTask = taskList.get(sc.nextInt() - 1);
                    targetTask.MarkAsDone();
                    System.out.println("    " + targetTask);
                    System.out.println("    ____________________________________________________________");
                    break;

                case "bye":
                    System.out.println("    ____________________________________________________________\n " +
                            "    Bye. Hope to see you again soon!\n" +
                            "    ____________________________________________________________");
                    Done = true;
                    break;

                case "blah":
                    System.out.println("    ____________________________________________________________\n " +
                            "    blah\n" +
                            "    ____________________________________________________________");
                    break;

                default:
                    String nextTask = nextCommand + sc.nextLine();
                    System.out.println("    ____________________________________________________________");
                    taskList.add(new Task(nextTask));
                    System.out.println("    added: " + nextTask);
                    System.out.println("    ____________________________________________________________");
                    break;
            }
        }
    }
}
