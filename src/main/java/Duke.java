import java.util.Scanner;

public class Duke {
    public static final String s = "     ";
    public static final String line = "    _______________________________________________________________________\n";
    public static String[] tasks = new String[100];
    public static int numTasks;

    public static void printMessage(String message) {
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
        String tasksMessage = "";
        while (i < numTasks) {
            tasksMessage = tasksMessage + String.valueOf(i + 1) + ". " + tasks[i] + "\n";
            i++;
        }
        printMessage(tasksMessage);
    }

    public static void addTask(String taskString) {
        tasks[numTasks] = taskString;
        numTasks++;
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        String userInput;
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();

        while (String.valueOf(userInput).toLowerCase().equals("bye") == false) {
            if (String.valueOf(userInput).toLowerCase().equals("list") == true) {
                listTasks();
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
