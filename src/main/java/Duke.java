import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Duke is a personal assistant chat bot that
 * helps users to keep track of various things.
 * @author Damith C. Rajapakse, Jeffry Lum, Wu Weiming
 */
public class Duke {
    List<Task> lst = new ArrayList<>();

    /**
     * Iterates and returns enumerated list of tasks.
     */
    public void iterateList() {
        System.out.println("Here are the items in your list:");
        int i = 1;
        for (Task item : lst) {
            System.out.println(i + ". " + item.toString());
            i++;
        }
    }

    /**
     * Formats text.
     */
    public void formatText(String str) {
        System.out.println("**************************************************");
        System.out.println(str);
        System.out.println("**************************************************");
    }

    /**
     * Checks for task in the given index and marks it as completed if valid.
     *
     * @param index task number given one-based indexing.
     */
    public void handleTaskStatus(int index) {
        if (index >= lst.size()) {
            formatText("Index out of bounds! You don't have so many items, hun.");
        } else {
            Task task = lst.get(index);
            task.markAsDone();
            formatText("Sweet! I have marked the following task as done:\n"
                    + task.toString());
        }
    }

    public void addTask(String input) {
        String[] split = input.split(" ",2);
        String command = split[0];

        if (split.length < 2 || (!command.equals("todo") && !command.equals("deadline") && !command.equals("event"))) {
            formatText("Did you have a stroke? I don't\n" +
                    " know what you want me to do.");
            return;
        }

        if (command.equals("todo")) {
            Todo todo = new Todo(split[1]);
            lst.add(todo);
            formatText("You got it! I added this task:\n   "
                + todo.toString()
                + "\nNow you have " + lst.size() + " items.");
        } else {
            String[] separateDetails = split[1].split("/");
            String description = separateDetails[0];
            String date = separateDetails[1];
            if (command.equals("deadline")) {
                Deadline deadline = new Deadline(description, date);
                lst.add(deadline);
                formatText("You got it! I added this task:\n   "
                        + deadline.toString()
                        + "\nNow you have " + lst.size() + " items.");
            } else if (command.equals("event")) {
                Event event = new Event(description, date);
                lst.add(event);
                formatText("You got it! I added this task:\n   "
                        + event.toString()
                        + "\nNow you have " + lst.size() + " items.");
            }
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                // close program
                formatText("Bye, see you soon! Don't miss me too much.");
                break;
            } else if (input.equals("list")) {
                // show everything in the list
                System.out.println("**************************************************");
                iterateList();
                System.out.println("**************************************************");
            } else if (input.split(" ",2)[0].equals("done")){
                // mark task with the given index as completed
                int index = Integer.parseInt(input.split(" ",2)[1]) - 1;
                handleTaskStatus(index);
            } else {
                // add new task to list
                addTask(input);
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        String logo = ".------..------..------..------.\n"
                + "|D.--. ||U.--. ||K.--. ||E.--. |\n"
                + "| :/\\: || (\\/) || :/\\: || (\\/) |\n"
                + "| (__) || :\\/: || :\\/: || :\\/: |\n"
                + "| '--'D|| '--'U|| '--'K|| '--'E|\n"
                + "`------'`------'`------'`------'";

        System.out.println("Hello, this is \n" + logo);
        Duke e = new Duke();
        e.run();
    }
}
