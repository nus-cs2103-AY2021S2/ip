import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The Duke project.
 */
public class Duke {

    /** The number of tasks at the start of the program. */
    static int totalTasks = 0;

    /** A boolean function to check if the user decides to terminate the program. */
    static boolean endOfCycle = false;

    public static void main(String[] args) throws IOException {
        greet();

        Scanner sc = new Scanner(System.in);

        String username = sc.nextLine();
        nextGreet(username);

        ArrayList<Task> tasks = new ArrayList<>();
        File newFile = new File("duke.txt");
        if (newFile.exists()) {
            FileOutputStream fos = new FileOutputStream("duke.txt", true);
            readFileIntoList("duke.txt", tasks);
            fos.close();
        } else {
            newFile.createNewFile();
        }

        while(!endOfCycle) {
            System.out.print(username + ": ");
            String nextInput = sc.nextLine();
            String command = nextInput.contains(" ") ? nextInput.split(" ")[0] : nextInput;
            try {
                switch (command) {
                    case "todo" -> todo(nextInput, tasks);
                    case "deadline" -> deadline(nextInput, tasks);
                    case "event" -> event(nextInput, tasks);
                    case "done" -> done(nextInput, tasks, totalTasks);
                    case "delete" -> delete(nextInput, tasks, totalTasks);
                    case "list" -> list(tasks, totalTasks);
                    case "bye" -> bye(username);
                    default -> wrongCommand();
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        sc.close();

        PrintWriter writer = new PrintWriter("duke.txt");
        for (Task item: tasks) {
            writer.println(item.toString());
        }
        writer.close();
    }

    /**
     * Read the existing task file and create the list of tasks when the program is run.
     * @param file The name of the file.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     */
    public static void readFileIntoList(String file, ArrayList<Task> tasks) {
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String object: lines) {
            if (object.charAt(1) == 'T') {
                if (object.charAt(4) == '-') {
                    tasks.add(new Todo(object.substring(7), true));
                } else {
                    tasks.add(new Todo(object.substring(7), false));
                }
            } else {
                String dateInfo = object.substring(object.indexOf("(") + 5, object.indexOf(")"));
                if (object.charAt(1) == 'D') {
                    if (object.charAt(4) == '-') {
                        tasks.add(new Deadline(object.substring(7, object.indexOf("(") - 1), dateInfo, true, true));
                    } else {
                        tasks.add(new Deadline(object.substring(7, object.indexOf("(") - 1), dateInfo, false, true));
                    }
                } else if (object.charAt(1) == 'E') {
                    if (object.charAt(4) == '-') {
                        tasks.add(new Event(object.substring(7, object.indexOf("(") - 1), dateInfo, true, true));
                    } else {
                        tasks.add(new Event(object.substring(7, object.indexOf("(") - 1), dateInfo, false, true));
                    }
                }
            }
                taskAdded();
        }
    }

    /**
     * Signal termination of the conversation.
     */
    public static void setEndOfCycle() {
        endOfCycle = true;
    }

    /**
     * Increase the total number of tasks in the list by 1.
     */
    public static void taskAdded() {
        totalTasks++;
    }

    /**
     * Decrease the total number of tasks in the list by 1.
     */
    public static void taskDeleted() {
        totalTasks--;
    }

    /**
     * Greets the user when the Duke is launched.
     */
    public static void greet() {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Jay!\n" + "What is your name!");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Greets the user again after knowing the user's name.
     * @param username The name of the user.
     */
    public static void nextGreet(String username) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Hi " + username + "!");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds a Todo task.
     * @param nextInput The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public static void todo(String nextInput, ArrayList<Task> tasks) throws DukeException{
        if (nextInput.length() < 6) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String command = nextInput.substring(5);
        tasks.add(new Todo(command, false));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks).toString());
        taskAdded();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds a Deadline task.
     * @param nextInput The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public static void deadline(String nextInput, ArrayList<Task> tasks) throws DukeException{
        if (nextInput.length() < 10) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!nextInput.contains("/")){
            throw new DukeException("OOPS!!! The date information of a deadline cannot be empty.");
        }
        String command = nextInput.substring(9, nextInput.indexOf("/") - 1);
        String dateInfo = nextInput.substring(nextInput.indexOf("/") + 4);
        tasks.add(new Deadline(command, dateInfo, false, false));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks).toString());
        taskAdded();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds an Event task.
     * @param nextInput The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public static void event(String nextInput, ArrayList<Task> tasks) throws DukeException{
        if (nextInput.length() < 7) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (!nextInput.contains("/")) {
            throw new DukeException("OOPS!!! The date information of an event cannot be empty.");
        }
        String command = nextInput.substring(6, nextInput.indexOf("/") - 1);
        String dateInfo = nextInput.substring(nextInput.indexOf("/") + 4);
        tasks.add(new Event(command, dateInfo, false, false));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks).toString());
        taskAdded();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Mark the task in the given task number as done.
     * @param command The command given by user input.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param count The current number of tasks stored inside the Task Arraylist.
     * @throws DukeException Exception thrown if the number given is out of range.
     */
    public static void done(String command, ArrayList<Task> tasks, int count) throws DukeException{
        if (command.length() < 6) {
            throw new DukeException("OOPS!!! The item number cannot be empty.");
        }
        String[] commandToWords = command.split(" ");
        int itemNum = Integer.parseInt(commandToWords[1]);
        if (itemNum > count || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }
        tasks.get(itemNum - 1).makeDone();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(itemNum - 1).toString());
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Delete the task corresponding to the number input by the user.
     * @param command The command given by user input.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param count The current number of tasks stored inside the Task Arraylist.
     * @throws DukeException Exception thrown if the number given is out of range.
     */
    public static void delete(String command, ArrayList<Task> tasks, int count) throws DukeException {
        if (command.length() < 8) {
            throw new DukeException("OOPS!!! The item number cannot be empty.");
        }
        String[] commandToWords = command.split(" ");
        int itemNum = Integer.parseInt(commandToWords[1]);
        if (itemNum > count || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }
        String taskRemoved = tasks.get(itemNum - 1).toString();
        tasks.remove(itemNum - 1);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Noted. I've removed this task:\n" + "    " + taskRemoved);
        taskDeleted();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * List out all user inputs in sequence.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param count The current number of tasks stored inside the Task Arraylist.
     */
    public static void list(ArrayList<Task> tasks, int count) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++){
            int listNum = i + 1;
            System.out.println(listNum + ". " + tasks.get(i).toString());
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Tells the user that the input given is invalid.
     * @throws DukeException Exception thrown if the user input is invalid.
     */
    public static void wrongCommand() throws  DukeException{
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Saying bye to the user when the user decides to quit.
     * @param username The name of the user.
     */
    public static void bye(String username) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Bye " + username + "! Hope to see you again soon!");
        System.out.println("----------------------------------------------------------------------------------------");
        setEndOfCycle();
    }
}

