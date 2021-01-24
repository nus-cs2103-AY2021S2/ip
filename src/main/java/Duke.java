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
            i++;
            listTasksMessage += String.valueOf(i) + ". " + t.toString() + "\n";
        }
        printMessage(listTasksMessage);
    }

    public static void addTask(String userCommand, String userDescription) {
        Task t;
        String taskDescription;
        SplitString parsedUserDescription;
        
        switch (userCommand) {
            case "todo":
                taskDescription = userDescription;
                t = new Task(taskDescription);
                break;
            case "deadline":
                parsedUserDescription = new SplitString(userDescription, "/by ");
                taskDescription = parsedUserDescription.getFirstString();
                String taskBy = parsedUserDescription.getSecondString();
                t = new Deadline(taskDescription, taskBy);
                break;
            case "event":
                parsedUserDescription = new SplitString(userDescription, "/at ");
                taskDescription = parsedUserDescription.getFirstString();
                String taskAt = parsedUserDescription.getSecondString();
                t = new Event(taskDescription, taskAt);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userCommand);
        }

        tasks[numTasks] = t;
        String addTaskMessage = "Got it. I've added this task: \n   " + tasks[numTasks].toString() + "\n";
        numTasks++;
        addTaskMessage += "Now you have " + String.valueOf(numTasks) + " task";
        addTaskMessage += numTasks == 1 ? "" : "s";
        addTaskMessage += " in the list.";
        printMessage(addTaskMessage);

    }

    public static void doTask(String userDescription) {
        int taskNum = Integer.parseInt(userDescription);
        Task t = tasks[taskNum - 1];
        t.markAsDone();
        String doTaskMessage = "Nice! I've marked this task as done: \n" + "   " + t.toString();
        printMessage(doTaskMessage);
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        String userInput, userCommand, userDescription;
        SplitString parsedUserInput;
        Scanner scan = new Scanner(System.in);

        while (true) {
            userInput = scan.nextLine();
            parsedUserInput = new SplitString(userInput, " ");
            userCommand = parsedUserInput.getFirstString().toLowerCase();
            userDescription = parsedUserInput.getSecondString();

            if (String.valueOf(userCommand).equals("bye") == true) {
                break;
            }
            switch (userCommand) {
                case "list":
                    listTasks();
                    break;
                case "done":
                    doTask(userDescription);
                    break;
                case "todo": //Fallthrough
                case "deadline": //Fallthrough
                case "event":
                    addTask(userCommand, userDescription);
                    break;
                default:
                    printMessage("Oops, not sure what you are asking for.");
            }
        }

        scan.close();
        printGoodbyeMessage();
    }
}
