import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
    public static int numTasks;

    public static void printMessage(String message) {
        String s = "     ";
        String line = "    _____________________________________"
                + "__________________________________\n";
        System.out.println(line);
        message = message.replace("\n", "\n" + s);
        System.out.println(s + message);
        System.out.println(line);
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello from \n" + logo + "\n"
                + "What can I do for you?";
        printMessage(welcomeMessage);
    }

    public static void printGoodbyeMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public static void listTasks() {
        int i = 0;
        String listTasksMessage = "Here are the tasks in your list: \n";
        while (i < numTasks) {
            Task t = tasks[i];
            listTasksMessage += String.valueOf(i + 1) + ". ";
            listTasksMessage += "[" + t.getStatusIcon() + "] ";
            listTasksMessage += t.getDescription() + "\n";
            i++;
        }
        printMessage(listTasksMessage);
    }

    public static void addTask(String taskDescription) {
        tasks[numTasks] = new Task(taskDescription);
        numTasks++;
    }

    public static void doTask(int taskNum) {
        Task t = tasks[taskNum - 1];
        t.markAsDone();
        String doTaskMessage = "Nice! I've marked this task as done: \n";
        doTaskMessage += "   [" + t.getStatusIcon() + "] " + t.getDescription();
        printMessage(doTaskMessage);
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();

        while (String.valueOf(userInput).toLowerCase().equals("bye") == false) {
            if (String.valueOf(userInput).toLowerCase().equals("list") == true) {
                listTasks();
            } else if (userInput.length() >= 6 &&
                    String.valueOf(userInput.substring(0, 4)).toLowerCase().equals("done") == true) {
                int taskNum = Integer.parseInt(userInput.substring(5));
                doTask(taskNum);
            } else {
                    addTask(userInput);
                    printMessage("added: " + userInput);
            }
            userInput = scan.nextLine();
        }
        scan.close();
        printGoodbyeMessage();
    }
}
