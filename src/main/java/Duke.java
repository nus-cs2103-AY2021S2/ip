import java.util.Scanner;

public class Duke {
    public static String tab = "     ";
    public static String line = "     ............................................................";
    public static Task[] tasks = new Task[100];
    public static int numTasks = 0;

    public static void main(String[] args) {
        printIntro();
        Scanner scan = new Scanner(System.in);

        loop:
        while (scan.hasNextLine()) {
            String input = scan.next();

            System.out.println(line);

            switch (input) {
                case "bye":
                    printBye();
                    break loop;
                case "list":
                    printList();
                    break;
                case "done":
                    int taskIndex = scan.nextInt() - 1;
                    tasks[taskIndex].markAsDone();

                    printDone(taskIndex);
                    break;
                default:
                    String description = scan.nextLine();

                    switch (input) {
                        case "todo":
                            ToDos todo = new ToDos(description);
                            tasks[numTasks] = todo;
                            break;
                        case "deadline":
                            Deadlines deadline = new Deadlines(description);
                            tasks[numTasks] = deadline;
                            break;
                        case "event":
                            Events event = new Events(description);
                            tasks[numTasks] = event;
                            break;
                    }
                    printAdd(numTasks);

                    numTasks++;
                    break;
            }
            System.out.println(line);
        }
    }

    public static void printIntro() {
        String logo =
                          " _____   _   _ \n"
                        + "| ____| | | | | \n"
                        + "| |___  | | | | __   __ \n"
                        + "|  ___| | | | | \\ \\ / / \n"
                        + "| |___  | | | |  \\ v / \n"
                        + "|_____| |_| |_|  /  / \n"
                        + "                /__/  \n";

        System.out.println("   C H A T   W I T H \n" + logo);

        System.out.println(line + "\n"
                + tab + "Hi there! I'm Elly.\n"
                + tab + "How can I help you today?\n"
                + line);
    }

    public static void printList() {
        System.out.println(tab + "Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            int num = i + 1;
            Task task = tasks[i];
            System.out.println(
                    tab + num + "." + task.toString());
        }
    }

    public static void printDone(int index) {
        System.out.println(tab + "Nice! I've marked this task as done:");
        System.out.println(tab + tasks[index].toString());
    }

    public static void printAdd(int index) {
        System.out.println(tab + "Got it. I've added this task: ");
        System.out.println(tab + tasks[index].toString());
        System.out.println(tab + "Now you have " + (index + 1) + " tasks in the list.");
    }

    public static void printBye() {
        System.out.println(tab + "Goodbye, can't wait to see you again!");
        System.out.println(line);
    }

}
