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
        List<Task> list = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm a bot called Duke. Beep boop. \nWhat do you want?\n");

        String command = sc.nextLine();
        String preText;
        while (!command.equals("bye")) {
            String[] commandList = command.split(" ");
            switch (commandList[0]) {
                case "list":
                    preText = "Here are the tasks in your list:\n    ";
                    print(preText + listToString(list));
                    break;
                case "done":
                    // TODO handle out of bounds exception for array indexing in commandList, list
                    // TODO handle NumberFormatException for Integer.valueOf
                    preText = "Nice! I've marked this task as done:\n      ";
                    Task item = list.get(Integer.valueOf(commandList[1]) - 1);
                    item.markAsDone();
                    print(preText + item.toString());
                    break;
                default:
                    Task newTask = new Task(command);
                    list.add(newTask);
                    print("Added: " + newTask.getDescription());
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

    public static String listToString(List<Task> list) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += String.valueOf(i+1) + ": " + list.get(i) + "\n    ";
        }

        return str.substring(0, str.length() - 5);
    }
}
