import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import customClass.*;

public class Duke {
    public static void main(String[] args) {
        String logo = "   ___     ___    _  _     ___     ___     _\n" +
                "  |   \\   /   \\  | \\| |   |_ _|   | __|   | |\n" +
                "  | |) |  | - |  | .` |    | |    | _|    | |__\n" +
                "  |___/   |_|_|  |_|\\_|   |___|   |___|   |____|\n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|\n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\n";
        System.out.println("Welcome back Max, I'm a Dumb Assistant " +
                "Naively Intended (to) Ease Labour\n" + logo +
                "____________________________________________________________\n" +
                "What can I do for you?\n" +
                "____________________________________________________________");

        //++++++++++++++++++++++++++ IGNORE THE CODE ABOVE +++++++++++++++++++++++++++++

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<Task> list = new ArrayList<>();

        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            if (input.equals("list")) {
                // Check if the command is list and display the list of tasks.
                String temp = "";

                for (int i = 0; i < list.size(); i++) {
                    temp += String.format("%d. %s", i + 1, list.get(i));
                    if (i != list.size() - 1) {
                        temp += "\n";
                    }
                }
                System.out.println(temp);
            } else if (input.split(" ")[0].equals("done")) {
                int itemNumber = Integer.valueOf(input.split(" ")[1]) - 1;

                list.get(itemNumber).toggleIsDone();
                System.out.println(
                    "Nice! I've marked this task as done:\n" +
                    list.get(itemNumber)
                );
            } else if (input.split(" ")[0].equals("todo")) {
                try {
                    Todo todo = new Todo(input.split(" ", 2)[1]);
                    list.add(todo);
                    System.out.println("added: " + todo);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops, your todo requires at least a description.");
                }
            } else if (input.split(" ")[0].equals("deadline")) {
                try {
                    String taskAndDate = input.split(" ", 2)[1];
                    String task = taskAndDate.split(" /by ")[0];
                    String date = taskAndDate.split(" /by ")[1];
                    Deadline deadline = new Deadline(task, date);
                    list.add(deadline);
                    System.out.println("added: " + deadline);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops, your deadline requires both a description and date.");
                }
            } else if (input.split(" ")[0].equals("event")) {
                try {
                    String taskAndDate = input.split(" ", 2)[1];
                    String task = taskAndDate.split(" /at ")[0];
                    String date = taskAndDate.split(" /at ")[1];
                    Event event = new Event(task, date);
                    list.add(event);
                    System.out.println("added: " + event);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops, your Event requires both a description and date");
                }
            } else {
                System.out.println("Oops, I'm sorry but that command is not valid.");
            }

            System.out.println("____________________________________________________________");
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}

