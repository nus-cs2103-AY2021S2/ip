import java.util.ArrayList;
import java.util.Scanner;

/**
 * The DukeLevel class is an interactive chat bot that can:
 * - add items to a list
 * - display a list of items which were previously added
 * - mark items in the list as done
 * - remove items from the list
 * - throw exceptions if the input is incorrect
 *
 * @author Shaelyn
 * @version CS2103T 20/21 Semester 2, Individual Project
 */

public class DukeLevel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "    _________________________________________________";

        System.out.println(line);
        System.out.println("     Hello! I'm Duke :)");
        System.out.println("     What can I do for you?");
        System.out.println(line);

        ArrayList<Task> tasksArray = new ArrayList<>();
        int count = 0;

        while (sc.hasNext()) {
            try {
                String input = sc.nextLine().trim();
                String[] strArray = input.split(" ", 2);
                String cmd = strArray[0];

                if (input.equalsIgnoreCase("todo") || input.equalsIgnoreCase("deadline") || input.equalsIgnoreCase("event")) {
                    throw new DukeException("Uh oh! There is nothing to do.");
                }

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(line);
                    System.out.println("     Bye. Hope to see you again soon! :)");
                    System.out.println(line);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println(line);
                    if (tasksArray.isEmpty()) {
                        System.out.println("     Your list is empty, there is nothing to do. Yay!");
                    } else {
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < count; i++) {
                            System.out.println("      " + (i + 1) + "." + tasksArray.get(i).toString());
                        }
                    }
                    System.out.println(line);
                } else if (cmd.equalsIgnoreCase("done")) {
                    int cmdNum = Integer.parseInt(strArray[1]);
                    tasksArray.get(cmdNum - 1).markAsDone();
                    System.out.println(line);
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + tasksArray.get(cmdNum - 1).toString());
                    System.out.println(line);
                } else if (cmd.equalsIgnoreCase("delete")) {
                    int cmdNum = Integer.parseInt(strArray[1]);
                    System.out.println(line);
                    System.out.println("     Noted. I've removed this task: ");
                    System.out.println("       " + tasksArray.get(cmdNum - 1).toString());
                    tasksArray.remove(cmdNum - 1);
                    count--;
                    if (count == 1) {
                        System.out.println("     Now you have " + count + " task in the list.");
                    } else {
                        System.out.println("     Now you have " + count + " tasks in the list.");
                    }
                    System.out.println(line);
                } else {
                    if (cmd.equalsIgnoreCase("todo")) {
                        System.out.println(line);
                        String cmdTask = strArray[1];
                        ToDo tempTask = new ToDo(cmdTask);
                        tasksArray.add(tempTask);
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("       " + tempTask.toString());
                    } else if (cmd.equalsIgnoreCase("deadline")) {
                        String cmdTask = strArray[1];
                        if (!strArray[1].contains("/by")) {
                            throw new DukeException("Uh oh! Please specify a timing using /by.");
                        }
                        System.out.println(line);
                        String[] tempStrArray = cmdTask.split("/by", 2);
                        Deadline tempTask = new Deadline(tempStrArray[0], tempStrArray[1]);
                        tasksArray.add(tempTask);
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("       " + tempTask.toString());
                    } else if (cmd.equalsIgnoreCase("event")) {
                        String cmdTask = strArray[1];
                        if (!strArray[1].contains("/at")) {
                            throw new DukeException("Uh oh! Please specify a timing using /at.");
                        }
                        System.out.println(line);
                        String[] tempStrArray = cmdTask.split("/at", 2);
                        Event tempTask = new Event(tempStrArray[0], tempStrArray[1]);
                        tasksArray.add(tempTask);
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("       " + tempTask.toString());
                    } else {
                        throw new DukeException("I'm sorry but I don't know what this means :(");
                    }
                    count++;
                    if (count == 1) {
                        System.out.println("     Now you have " + count + " task in the list.");
                    } else {
                        System.out.println("     Now you have " + count + " tasks in the list.");
                    }
                    System.out.println(line);
                }
            } catch (DukeException exception) {
                System.out.println(line);
                System.out.println("     " + exception);
                System.out.println(line);
            }
        }
    }
}


/**
 * The Task class is a parent class for ToDo, Deadline and Event
 * whereby each Task has a boolean to indicate whether it has been done
 */
class Task {
    /**
     * String description that determines what kind of task the input is
     */
    protected String description;

    /**
     * boolean isDone that indicates whether the task has been done
     */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * For a given task, indicate whether it has been done using a space or a tick
     * @return if the task has been done, return a tick, else return a space
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick symbol
    }

    /**
     * For a given task, if it is done, mark it as done by changing the boolean isDone to true
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() { return "[" + getStatusIcon() + "] " + description; }
}

/**
 * The ToDo class is a child class of the Task Class,
 * it specifies the task as a ToDo using [T]
 */
class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * The Deadline class is a child class of the Task Class,
 * it specifies the task as a Deadline using [D]
 */
class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}

/**
 * The Event class is a child class of the Task Class,
 * it specifies the task as an Event using [E]
 */
class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}

/**
 * The DukeException is a child class of the Java Exception class
 * which throws an exception message when an invalid input is given to the chat bot
 * either a wrong message (unidentified input) or an unclear message (not specific enough)
 */
class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}