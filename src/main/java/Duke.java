import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Personal Assistant Chatbot named Duke that
 * helps users to keep track of things
 * @author Damith C. Rajapakse, Jeffry Lum, Vanessa Tay
 */
public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Greets the user and begins listening to user commands
     * @param args supplies command-line arguments to the Chatbot
     */
    public static void main(String[] args) {
        greetUser();
        listenToUserCommand();
    }

    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nHow can I help?\n");
    }

    /**
     * Takes in user's command line arguments and treats them accordingly
     * until user inputs "bye", then Chatbot will end the session
     */
    public static void listenToUserCommand() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                stopListeningToCommand();
                break;
            } else if (input.equals("list")) {
                displayUserCommands();
            } else if (input.startsWith("done ")) {
                int taskNumber = Integer.parseInt(input.substring(5));
                Task task = taskList.get(taskNumber-1);
                markTaskDone(task);
            } else {
                addUserCommand(input);
                echoUserCommand(input);
            }
        }
    }

    /**
     * Chatbot prints out a farewell message before ending the session
     */
    public static void stopListeningToCommand() {
        System.out.println("Bye. Hope to see you soon!\n");
    }

    /**
     * Chatbot prints out what the user has just fed in as command-line input
     * @param input command-line input
     */
    public static void echoUserCommand(String input) {
        System.out.println(input + "\n");
    }

    /**
     * Chatbot creates new task with the user's command-line input
     * and stores it in a list
     * @param input command-line input
     */
    public static void addUserCommand(String input) {
       Task task = new Task(input);
        taskList.add(task);
        System.out.print("added: ");
    }

    /**
     * Chatbot prints out line-by-line all of the user's tasks
     * stored in the list in that given session.
     * Completed tasks will be marked with a tick and uncompleted
     * ones with a cross.
     */
    public static void displayUserCommands() {
        System.out.println("Here are your tasks!");
        for(int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i-1);
            System.out.println(i + ". [" + task.getStatusIcon()
                    + "]" + task.description);
        }
        System.out.println();
    }

    /**
     * Mark task as done and notify user of that change
     * @param task supplied from the user's command-line input
     */
    public static void markTaskDone(Task task) {
        task.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "]" + task.description);
        System.out.println();
    }
}
