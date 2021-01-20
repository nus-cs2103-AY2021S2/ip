import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        List<Task> contentList = new ArrayList<>();

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            StringBuilder output = new StringBuilder();

            if (input.equals("bye")) {
                textWarper("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                output.append("Here are the tasks in your list: \n");

                for (int i = 0; i < contentList.size(); i++) {
                    output.append(String.format("%d.%s%n", i + 1, contentList.get(i).toString()));
                }
            } else if (input.startsWith("done")) {
                output.append("Nice! I've marked this task as done: \n  ");

                int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                contentList.get(index).setCompleted();

                output.append(contentList.get(index));
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                String[] arr = input.split(" ", 2);
                Task temp;
                if (input.endsWith("todo") || input.endsWith("deadline") || input.endsWith("event")) {
                    output.append("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    switch (arr[0]) {
                        case "todo":
                            temp = new ToDo(arr[1]);
                            break;
                        case "deadline":
                            temp = new Deadline(arr[1]);
                            break;
                        case "event":
                            temp = new Event(arr[1]);
                            break;
                        default:
                            temp = new Task(arr[1]);
                    }
                    contentList.add(temp);
                    output.append("Got it. I've added this task:\n ");
                    output.append(temp.toString() + "\n");
                    output.append(String.format("Now you have %d tasks in the list.", contentList.size()));
                }
            } else {
                textWarper("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                // contentList.add(new Task(input));
                // output.append("added: " + input);
            }

            textWarper(output.toString());
        }

    }

    public static void textWarper(String a) {
        System.out.println("____________________________________________________________");
        System.out.println(a);
        System.out.println("____________________________________________________________");
    }
}
