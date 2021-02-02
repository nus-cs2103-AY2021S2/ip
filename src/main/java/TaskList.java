import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle all the tasks.
 */
public class TaskList {
    private static final String ADD_TODO_COMMAND = "todo";
    private static final String ADD_DEADLINE_COMMAND = "deadline";
    private static final String ADD_EVENT_COMMAND = "event";

    List<Task> lst = new ArrayList<>();

    /**
     * Iterates and returns enumerated list of tasks.
     */
    public String iterateList() {
        String s = "Here are the items in your list:\n";
        System.out.println("Here are the items in your list:");
        int i = 1;
        for (Task item : lst) {
            System.out.println(i + ". " + item.toString());
            s += i + ". " + item.toString() + "\n";
            i++;
        }
        return s;
    };

    public List<Task> fetchTasks() {
        return lst;
    }

    /**
     * Checks for task in the given index and marks it as completed if valid.
     *
     * @param str task number given one-based indexing
     */
    public String markAsDone(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            Task task = lst.get(index);
            task.markAsDone();

            Ui.formatText();
            System.out.println("Sweet! I have marked the following task as done:\n"
                    + task.toString());
            Ui.formatText();
            return "Sweet! I have marked the following task as done:\n" + task.toString();
        } catch (final NumberFormatException e) {
            System.err.println("Oof, did you type a valid number or not?");
            return "Oof, did you type a valid number or not?";
        } catch (IndexOutOfBoundsException exception) {
            System.err.println("You don't have so many items, dumbass!");
            return "You don't have so many items, dumbass!";
        }
    }

    /**
     * Adds task to list of tasks if it is a valid input.
     * @param input user input in command line
     */
    public String addTask(String input) {
        try {
            String[] split = input.split(" ", 2);
            String command = split[0];

            Ui.formatText();

            if (!command.equals(ADD_TODO_COMMAND) && !command.equals(ADD_DEADLINE_COMMAND)
                    && !command.equals(ADD_EVENT_COMMAND)) {
                // error: unknown command
                System.out.println("Hey! What is this gibberish?");
                return "Hey! What is this gibberish?";
            } else if (split.length < 2) {
                // error: command empty
                System.out.println("You gotta give me a description to work with, buddy:\nCommand \""
                        + command + "\" cannot be empty.");
                return "You gotta give me a description to work with, buddy:\nCommand \""
                        + command + "\" cannot be empty.";
            } else if (command.equals("todo")) {
                Todo todo = new Todo(split[1]);
                lst.add(todo);
                System.out.println("You got it! I added this task:\n   "
                        + todo.toString());
                countTasks();
                return "You got it! I added this task:\n   "
                        + todo.toString() + "\n"
                        + countTasks();
            } else {
                try {
                    String[] separateDetails = split[1].split("/by |/at ");
                    String description = separateDetails[0];

                    String date = separateDetails[1];
                    LocalDate localDate = LocalDate.parse(date);

                    if (command.startsWith(ADD_DEADLINE_COMMAND)) {
                        Deadline deadline = new Deadline(description, localDate);

                        lst.add(deadline);
                        System.out.println("You got it! I added this task:\n   "
                                + deadline.toString());
                        return "You got it! I added this task:\n   "
                                + deadline.toString() + "\n" + countTasks();
                    } else {
                        // command is ADD_EVENT_COMMAND
                        Event event = new Event(description, localDate);
                        lst.add(event);
                        System.out.println("You got it! I added this task:\n   "
                                + event.toString());
                        return "You got it! I added this task:\n   "
                                + event.toString() + "\n" + countTasks();
                    }
                    //countTasks();
                } catch (ArrayIndexOutOfBoundsException e){
                    System.err.println("Did you format your request properly? " +
                            "This is getting old.");
                    return "Did you format your request properly? " +
                            "This is getting old.";
                }
            }
        } catch (DateTimeParseException e) {
            System.err.println("Your date formatting is invalid, use YYYY-MM-DD please...");
            return "Your date formatting is invalid, use YYYY-MM-DD please...";
        }
    }

    /**
     * Deletes task from list of tasks.
     * @param str task number given one-based indexing
     */
    public String deleteTask(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            Task task = lst.get(index);
            lst.remove(index);

            Ui.formatText();
            System.out.println("Sweet! I have deleted the following task:\n"
                    + task.toString());
            countTasks();
            Ui.formatText();
            return "Sweet! I have deleted the following task:\n"
                    + task.toString() + "\n" + countTasks();
        } catch (final NumberFormatException e) {
            System.err.println("Oof, did you type a valid number or not?");
            return "Oof, did you type a valid number or not?";
        } catch (IndexOutOfBoundsException exception) {
            System.err.println("You don't have so many items, dumbass!");
            return "You don't have so many items, dumbass!";
        }
    }

    public String searchTask(String str) {
        System.out.println("Here are the items that match your search:");
        String s = "Here are the items that match your search:";
        int i = 1;
        for (Task item : lst) {
            if (item.description.toLowerCase().contains(str.toLowerCase())) {
                System.out.println(i + ". " + item.toString());
                s += i + ". " + item.toString() + "\n";
                i++;
            }
        }
        return s;
    }

    /**
     * Determines if task is Todo, Deadline or Event and handles them accordingly.
     *
     * @param str input from user.
     */
    public void readTask(String str) {
        String[] split = str.split("`");

        switch (split[0]) {
        case "Todo":
            Todo todo = new Todo(split[2], Boolean.parseBoolean(split[1]));
            lst.add(todo);
            break;
        case "Deadline":
            Deadline deadline = new Deadline(Boolean.parseBoolean(split[1]),
                    split[2], LocalDate.parse(split[3]));
            lst.add(deadline);
            break;
        case "Event":
            Event event = new Event(Boolean.parseBoolean(split[1]),
                    split[2], LocalDate.parse(split[3]));
            lst.add(event);
            break;
        }
    }

    /**
     * Outputs current number of active tasks in the TaskList.
     */
    public String countTasks() {
        System.out.println("Now you have " + lst.size() + " tasks.");
        return "Now you have " + lst.size() + " tasks.";
    }
}
