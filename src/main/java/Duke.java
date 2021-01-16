import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Duke {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        List<Task> list = new ArrayList<>();

        greet();
        commandList(list);
    }

    //Duke greets the user.
    public static void greet() {
        System.out.println("\t____________________________________________________________\n"
                        + "\tHello! I'm Duke\n\tWhat can I do for you?\n"
                        + "\t____________________________________________________________\n");
    }

    //Echos the input back to the user.
    public static void echo() {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("\t____________________________________________________________\n"
                    + "\t" + input + "\n"
                    + "\t____________________________________________________________\n");
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n"
        );
    }

    //Adds the input in a list and echo it back to the user.
    //Prints the list if input is "list"
    //Mark task as done if input is "done" with task number.
    public static void commandList(List<Task> list) {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                showList(list);
            } else {
                String[] check = input.split(" ");
                if (check.length == 2 && check[0].equals("done") && isNumber(check[1])) {
                    markTaskDone(list, check);
                } else {
                    addToList(list, input, check);
                }
            }
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n"
        );
    }

    //Prints out the items in the list.
    public static void showList(List<Task> list) {
        if (list.size() == 0) {
            System.out.println("\t____________________________________________________________\n"
                        + "\tThere are no items in your list."
                        + "\t____________________________________________________________\n");
        } else {
            System.out.println("\t____________________________________________________________\n"
                    + "\tHere are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
            }
            System.out.println("\t____________________________________________________________\n");
        }
    }

    //Check if string is number
    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void markTaskDone(List<Task> list, String[] check) {
        int num = Integer.parseInt(check[1]);
        if (num > 0 && num <= list.size()) {
            list.get(num - 1).markAsDone();
            System.out.println("\t____________________________________________________________\n"
                    + "\tNice! I've marked this task as done:\n\t\t"
                    + list.get(num - 1).toString() + "\n"
                    + "\t____________________________________________________________\n");

        } else {
            System.out.println("\t____________________________________________________________\n"
                    + "\tTask not in list.\n"
                    + "\t____________________________________________________________\n");
        }
    }

    public static void addToList(List<Task> list, String input, String[] check) {
        Task temp;
        String description;
        String date;

        if (check[0].equals("todo")) {
            description = input.substring(5);
            temp = new TodoTask(description);
        } else if (check[0].equals("deadline")) {
            int index = input.lastIndexOf("/by");
            description = input.substring(9, index - 1);
            date = input.substring(index + 4);
            temp = new DeadlineTask(description, date);
        } else if (check[0].equals("event")) {
            int index = input.lastIndexOf("/at");
            description = input.substring(6, index - 1);
            date = input.substring(index + 4);
            temp = new EventTask(description, date);
        } else {
            description = input;
            temp = new Task(description);
        }
        list.add(temp);
        System.out.println("\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t   " + temp.toString() + "\n"
                + "\tNow you have " + list.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }
}
