import java.util.Scanner;

public class DukeLevel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "    _________________________________________________";

        System.out.println(line);
        System.out.println("     Hello! I'm Duke :)");
        System.out.println("     What can I do for you?");
        System.out.println(line);

        Task[] tasksArray = new Task[100];
        int count = 0;

        try {
            while (sc.hasNext()) {

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
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println("      " + (i + 1) + "." + tasksArray[i].toString());
                    }
                    System.out.println(line);
                } else if (cmd.equalsIgnoreCase("done")) {
                    int cmdNum = Integer.parseInt(strArray[1]);
                    tasksArray[cmdNum - 1].markAsDone();
                    System.out.println(line);
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + tasksArray[cmdNum - 1].toString());
                    System.out.println(line);
                } else {
                    tasksArray[count] = new Task(input);
                    System.out.println(line);
                    if (cmd.equalsIgnoreCase("todo")) {
                        String cmdTask = strArray[1];
                        ToDo tempTask = new ToDo(cmdTask);
                        tasksArray[count] = tempTask;
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("       " + tempTask.toString());
                    } else if (cmd.equalsIgnoreCase("deadline")) {
                        String cmdTask = strArray[1];
                        String[] tempStrArray = cmdTask.split("/by", 2);
                        Deadline tempTask = new Deadline(tempStrArray[0], tempStrArray[1]);
                        tasksArray[count] = tempTask;
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("       " + tempTask.toString());
                    } else if (cmd.equalsIgnoreCase("event")) {
                        String cmdTask = strArray[1];
                        String[] tempStrArray = cmdTask.split("/at", 2);
                        Event tempTask = new Event(tempStrArray[0], tempStrArray[1]);
                        tasksArray[count] = tempTask;
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("       " + tempTask.toString());
                    } else {
                        throw new DukeException("I'm sorry but I don't know what this means :(");
                    }
                    if (count == 0) {
                        System.out.println("     Now you have " + (count + 1) + " task in the list.");
                    } else {
                        System.out.println("     Now you have " + (count + 1) + " tasks in the list.");
                    }
                    System.out.println(line);
                    count++;
                }
            }
        } catch (DukeException exception) {
            System.err.print(exception);
        }
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() { return "[" + getStatusIcon() + "] " + description; }
}

class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

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

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}