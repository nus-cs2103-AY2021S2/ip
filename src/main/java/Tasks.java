import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle all the tasks.
 */
public class Tasks {
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
    };

    /**
     * Checks for task in the given index and marks it as completed if valid.
     *
     * @param str task number given one-based indexing.
     */
    public void markAsDone(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            Task task = lst.get(index);
            task.markAsDone();

            Duke.formatText();
            System.out.println("Sweet! I have marked the following task as done:\n"
                    + task.toString());
            Duke.formatText();
        } catch (final NumberFormatException e) {
            System.err.println("Oof, did you type a number or not?");
        } catch (IndexOutOfBoundsException exception) {
            System.err.println("You don't have so many items, dumbass!");
        }
    }

    /**
     *
     * @param input
     */
    public void addTask(String input) {
        String[] split = input.split(" ",2);
        String command = split[0];

        Duke.formatText();

        if (!command.equals("todo") && !command.equals("deadline") && !command.equals("event")) {
            // error: unknown command
            System.out.println("Hey! What is this gibberish?");
            return;
        } else if (split.length < 2) {
            // error: command empty
            System.out.println("You gotta give me a description to work with, buddy.");
        } else if (command.equals("todo")) {
            Todo todo = new Todo(split[1]);
            lst.add(todo);
            System.out.println("You got it! I added this task:\n   "
                    + todo.toString()
                    + "\nNow you have " + lst.size() + " items.");
        } else {
            String[] separateDetails = split[1].split("/");
            String description = separateDetails[0];
            String date = separateDetails[1];
            if (command.equals("deadline")) {
                Deadline deadline = new Deadline(description, date);
                lst.add(deadline);
                System.out.println("You got it! I added this task:\n   "
                        + deadline.toString()
                        + "\nNow you have " + lst.size() + " items.");
            } else if (command.equals("event")) {
                Event event = new Event(description, date);
                lst.add(event);
                System.out.println("You got it! I added this task:\n   "
                        + event.toString()
                        + "\nNow you have " + lst.size() + " items.");
            }
        }

        Duke.formatText();
    }
}
