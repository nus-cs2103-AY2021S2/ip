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
    public static void main(String[] args) throws DukeException {
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
     * Takes in user's command line arguments and treats them accordingly.
     * Chatbot Duke will end the session when user input "bye".
     */
    public static void listenToUserCommand() throws DukeException {
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
                Task task = taskList.get(taskNumber - 1);
                markTaskDone(task);
            } else if (input.startsWith("todo ")) {
                if (input.split(" ").length == 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                String todoTask = input.substring(5);
                addTodoTask(todoTask);
            } else if (input.startsWith("event ")) {
                String[] taskAndDate = input.substring(6).split("/");
                String eventTask = taskAndDate[0];
                String date = taskAndDate[1].substring(3);
                addEventTask(eventTask, date);
            } else if (input.startsWith("deadline ")) {
                String[] taskAndDate = input.substring(9).split("/");
                String deadlineTask = taskAndDate[0];
                String date = taskAndDate[1].substring(3);
                addDeadlineTask(deadlineTask, date);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
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
     * Chatbot prints out line-by-line all of the user's tasks
     * stored in the list in that given session.
     */
    public static void displayUserCommands() {
        System.out.println("Here are your tasks!");
        for(int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i-1);
            System.out.print(i + ".");
            System.out.println(task);
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
        System.out.println(task);
        System.out.println();
    }

    public static void addTodoTask(String input) {
        Task task = new Todo(input);
        updateTaskList(task);
    }

    public static void addEventTask(String input, String date) {
        Task task = new Event(input, date);
        updateTaskList(task);
    }

    public static void addDeadlineTask(String input, String date) {
        Task task = new Deadline(input, date);
        updateTaskList(task);
    }

    public static void updateTaskList(Task task) {
        taskList.add(task);
        int numTasks = taskList.size();
        System.out.println("added: " + task + "\nyou have " + numTasks + " tasks in your list");
        System.out.println();
    }

}
