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
                "     Duke... booted...\n" +
                "     requesting tasks\n" +
                "    ____________________________________________________________\n");

        boolean Done = false;
        ArrayList<Task> taskList = new ArrayList<>();

        while (!Done) {
            String nextCommand = sc.next();
            switch (nextCommand) {
                case "list":
                    System.out.println("    ____________________________________________________________");
                    for (int taskNum = 0; taskNum < taskList.size(); taskNum++) {
                        System.out.println("     " + (taskNum + 1) + "." + taskList.get(taskNum));
                    }
                    System.out.println("    ____________________________________________________________");
                    break;

                case "todo":
                    String scanTest = sc.nextLine();
                    if (scanTest.equals("") || scanTest.equals(" ")) {
                        System.out.println("    ____________________________________________________________\n" +
                                "     Error: ToDo entry format incorrect.\n" +
                                "    ____________________________________________________________");
                        break;
                    }
                    ToDo nextToDo = new ToDo(scanTest);
                    taskList.add(nextToDo);
                    System.out.println("    ____________________________________________________________\n" +
                            "     task.add:");
                    System.out.println("       " + nextToDo + "\n" +
                            "     task.count = [" + taskList.size() + "].");
                    System.out.println("    ____________________________________________________________");
                    break;

                case "deadline":
                    String[] deadLineArgs = sc.nextLine().split(" /by ");

                    try {
                        Deadline nextDeadLine = new Deadline(deadLineArgs[0], deadLineArgs[1]);
                        taskList.add(nextDeadLine);
                        System.out.println("    ____________________________________________________________\n" +
                                "     task.add:");
                        System.out.println("       " + nextDeadLine + "\n" +
                                "     task.count = [" + taskList.size() + "].");
                        System.out.println("    ____________________________________________________________");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Error: Deadline entry format incorrect.");
                        System.out.println("    ____________________________________________________________");
                    }
                    break;

                case "event":
                    String[] eventArgs = sc.nextLine().split(" /at ");

                    try {
                        Event nextEvent = new Event(eventArgs[0], eventArgs[1]);
                        taskList.add(nextEvent);
                        System.out.println("    ____________________________________________________________\n" +
                                "     task.add:");
                        System.out.println("       " + nextEvent + "\n" +
                                "     task.count = [" + taskList.size() + "].");
                        System.out.println("    ____________________________________________________________");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Error: Event entry format incorrect.");
                        System.out.println("    ____________________________________________________________");
                    }
                    break;

                case "done":
                    System.out.println("    ____________________________________________________________\n" +
                            "     task.done = true:");
                    Task targetTask = taskList.get(sc.nextInt() - 1);
                    targetTask.MarkAsDone();
                    System.out.println("       " + targetTask);
                    System.out.println("    ____________________________________________________________");
                    break;

                case "bye":
                    System.out.println("    ____________________________________________________________\n " +
                            "     goodBye.\n" +
                            "    ____________________________________________________________");
                    Done = true;
                    break;

                default:
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     invalidCommand.");
                    System.out.println("    ____________________________________________________________");
                    break;
            }
        }
    }
}
