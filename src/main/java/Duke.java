import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {

        List<Task> contentList = new ArrayList<>();

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            StringBuilder output = new StringBuilder();

            try {
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

                } else if (input.endsWith("todo") || input.endsWith("deadline") || input.endsWith("event")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");

                } else if (input.startsWith("todo")) {
                    String[] arr = input.split(" ", 2);
                    Task temp = new ToDo(arr[1]);
                    contentList.add(temp);
                    taskAdded(output, contentList, temp);

                } else if (input.startsWith("deadline")) {
                    String[] arr = input.split(" ", 2);
                    Task temp = new Deadline(arr[1]);
                    contentList.add(temp);
                    taskAdded(output, contentList, temp);

                } else if (input.startsWith("event")) {
                    String[] arr = input.split(" ", 2);
                    Task temp = new Event(arr[1]);
                    contentList.add(temp);
                    taskAdded(output, contentList, temp);

                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    output.append("Noted. I've removed this task: \n  ");
                    output.append(contentList.get(index) + "\n");
                    contentList.remove(index);
                    output.append(String.format("Now you have %d tasks in the list.", contentList.size()));

                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                output.append(e.toString());
            } catch (IndexOutOfBoundsException e) {
                output.append("You have nothing in the list!");
            }

            textWarper(output.toString());
        }

    }

    public static void taskAdded(StringBuilder output, List<Task> contentList, Task temp) {
        output.append("Got it. I've added this task:\n ");
        output.append(temp.toString() + "\n");
        output.append(String.format("Now you have %d tasks in the list.", contentList.size()));
    }

    public static void textWarper(String a) {
        System.out.println("____________________________________________________________");
        System.out.println(a);
        System.out.println("____________________________________________________________");
    }
}
