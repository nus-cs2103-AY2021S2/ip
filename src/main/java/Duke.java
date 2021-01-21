import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm a bot called Duke. Beep boop. \nWhat do you want?\n");

        final String ADD_TASK_TEXT = "Got it. I've added this task:\n    ";

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            String[] commandList = command.split(" ", 2);
            String[] params;
            Task newTask;
            switch (commandList[0]) {
                case "list":
                    print("Here are the tasks in your list:\n    " + listToString(tasks));
                    break;
                case "done":
                    // TODO handle out of bounds exception for array indexing in commandList, list
                    // TODO handle NumberFormatException for Integer.valueOf
                    Task task = tasks.get(Integer.valueOf(commandList[1]) - 1);
                    task.markAsDone();
                    print("Nice! I've marked this task as done:\n      " + task.toString());
                    break;
                case "todo":
                    try {
                        newTask = new Todo(commandList[1], TaskType.TODO);
                        tasks.add(newTask);
                        print(ADD_TASK_TEXT + newTask.toString() + "\n    Now you have " + tasks.size() + " tasks in the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        print("The description of a todo cannot be empty.");
                    }
                    break;
                case "deadline":
                    // TODO handle out of bounds for commandList
                    params = commandList[1].split(" /by ");
                    newTask = new Deadline(params[0], TaskType.DEADLINE, params[1]);
                    tasks.add(newTask);
                    print(ADD_TASK_TEXT + newTask.toString() + "\n    Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "event":
                    // TODO handle out of bounds for commandList
                    params = commandList[1].split(" /at ");
                    newTask = new Event(params[0], TaskType.EVENT, params[1]);
                    tasks.add(newTask);
                    print(ADD_TASK_TEXT + newTask.toString() + "\n    Now you have " + tasks.size() + " tasks in the list.");
                    break;
                default:
                    print("Command not recognised.");
            }
            command = sc.nextLine();
        }
        print("BYE AND HAVE A GOOD DAY. Beep boop.");
        sc.close();
        return;
    }

    public static void print(String text) {
        String line = "    _______________________________________\n    ";
        System.out.println(line + text + "\n" + line);
    }

    public static String listToString(List<Task> tasks) {
        String str = "";
        if (tasks.size() == 0) {
            return str;
        }
        for (int i = 0; i < tasks.size(); i++) {
            str += String.valueOf(i+1) + ": " + tasks.get(i) + "\n    ";
        }

        return str.substring(0, str.length() - 5);
    }
}
