import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import customClass.Task;

public class Duke {
    public static void main(String[] args) {
        String logo = "   ___     ___    _  _     ___     ___     _     \n" +
                "  |   \\   /   \\  | \\| |   |_ _|   | __|   | |    \n" +
                "  | |) |  | - |  | .` |    | |    | _|    | |__  \n" +
                "  |___/   |_|_|  |_|\\_|   |___|   |___|   | ____| \n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n";
        System.out.println("Welcome back Max, I'm a Dumb Assistant " +
                "Naively Intended (to) Ease Labour\n" + logo +
                "____________________________________________________________\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        //++++++++++++++++++++++++++ IGNORE THE CODE ABOVE +++++++++++++++++++++++++++++

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<Task> list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                // Check if the command is list and display the list of tasks.
                String temp = "";

                for (int i = 0; i < list.size(); i++) {
                    temp += String.format("%d. %s", i + 1, list.get(i));
                    if (i != list.size() - 1) {
                        temp += "\n";
                    }
                }
                System.out.println(
                    "____________________________________________________________\n" +
                    temp + "\n" +
                    "____________________________________________________________\n"
                );
            } else if (input.split(" ")[0].equals("done")) {
                int itemNumber = Integer.valueOf(input.split(" ")[1]) - 1;

                list.get(itemNumber).toggleIsDone();
                System.out.println(
                    "____________________________________________________________\n" +
                    "Nice! I've marked this task as done:\n" +
                    list.get(itemNumber) + "\n" +
                    "____________________________________________________________\n"
                );
            } else {
                // Otherwise add tasks to the list and print it.
                Task currentTask = new Task(input);
                list.add(currentTask);
                System.out.println(
                    "____________________________________________________________\n" +
                    "added: " + input + "\n" +
                    "____________________________________________________________\n"
                );
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}