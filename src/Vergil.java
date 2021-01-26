import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Vergil {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command;
        ArrayList<Task> tasks = load();

        System.out.println("Welcome! I'm Vergil!");
        System.out.println("How may I help you?");
        System.out.println();

        do {
            // User commands are prefixed with >>>.
            System.out.print(">>> ");
            command = scan.nextLine().trim();

            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. See you soon!");
                } else if (command.equals("list")) {
                    listTasks(tasks);
                } else if (command.startsWith("done")) {
                    completeTask(command, tasks);
                    save(tasks);
                } else if (command.startsWith("delete")) {
                    deleteTask(command, tasks);
                    save(tasks);
                } else if (command.startsWith("todo")) {
                    addTodo(command, tasks);
                    save(tasks);
                } else if (command.startsWith("deadline")) {
                    addDeadline(command, tasks);
                    save(tasks);
                } else if (command.startsWith("event")) {
                    addEvent(command, tasks);
                    save(tasks);
                } else {
                    throw new VergilException("Sorry! I cannot resolve this command.");
                }
            } catch (VergilException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        } while (!command.equals("bye"));
    }

    /**
     * Adds a Todo task to a given list of tasks.
     * @param command the 'todo' command to be processed and added as a task.
     * @param tasks the list of tasks to be modified.
     * @throws VergilException if the 'todo' command's formatting is invalid.
     */
    public static void addTodo(String command, List<Task> tasks) throws VergilException {
        try {
            String desc = command.split(" ", 2)[1];
            tasks.add(new Todo(desc));
            System.out.printf("Added '%s' as a ToDo task.\n", desc);
        } catch (IndexOutOfBoundsException e) {
            throw new VergilException("Sorry! 'todo' commands should be typed as follows:\n"
                    + "    todo <description>");
        }
    }

    /**
     * Adds a Deadline task to a given list of tasks.
     * @param command the 'deadline' command to be processed and added as a task.
     * @param tasks the list of tasks to be modified.
     * @throws VergilException if the 'deadline' command's formatting is invalid.
     */
    public static void addDeadline(String command, List<Task> tasks) throws VergilException {
        try {
            String[] bySplit = command.split(" /by ");
            String desc = bySplit[0].split(" ", 2)[1];
            LocalDateTime time = LocalDateTime.parse(
                    bySplit[1], DateTimeFormatter.ofPattern("d/M/y HHmm"));

            tasks.add(new Deadline(desc, time));
            System.out.printf(
                    "Added '%s' as a deadline task by '%s'.\n",
                    desc,
                    time.format(DateTimeFormatter.ofPattern("d MMM y @ h:mm a")));

        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new VergilException("Sorry! 'deadline' commands should be typed as follows:\n"
                    + "    deadline <description> /by <date (d/m/yyyy)> <time, 24 hours (hhmm)>");
        }
    }

    /**
     * Adds an Event task to a given list of tasks.
     * @param command the 'event' command to be processed and added as task.
     * @param tasks the list of tasks to be modified.
     * @throws VergilException if the 'event' command's formatting is invalid.
     */
    public static void addEvent(String command, List<Task> tasks) throws VergilException {
        try {
            String[] atSplit = command.split(" /at ");
            String desc = atSplit[0].split(" ", 2)[1];
            LocalDateTime time = LocalDateTime.parse(
                    atSplit[1], DateTimeFormatter.ofPattern("d/M/y HHmm"));

            tasks.add(new Event(desc, time));
            System.out.printf(
                    "Added '%s' as a event task at '%s'.\n",
                    desc,
                    time.format(DateTimeFormatter.ofPattern("d MMM y @ h:mm a")));

        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new VergilException("Sorry! 'event' commands should be typed as follows:\n"
                    + "    event <description> /at <date (d/m/yyyy)> <time, 24 hours (hhmm)>");
        }
    }

    /**
     * Marks a task in the list as done. Does nothing if the task is already completed.
     * @param command the 'done' command to be processed and completed as a task.
     * @param tasks the list of tasks to be modified.
     * @throws VergilException if the 'done' command's formatting is invalid, or if there is no matching task
     * in the list.
     */
    public static void completeTask(String command, List<Task> tasks) throws VergilException {
        try {
            int taskSerialNum = Integer.parseInt(command.split(" ")[1]);
            Task t = tasks.get(taskSerialNum - 1);
            if (t.isDone()) {
                System.out.printf("You have already completed this task: %s\n", t);
            } else {
                t.doTask();
                System.out.println("Sweet! Task completed:");
                System.out.printf("   %s\n", t);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
           throw new VergilException("Sorry! 'done' commands should be typed as follows:\n"
                   + "    done <task number in list>");
        } catch (IndexOutOfBoundsException e) {
            throw new VergilException("Sorry! There is no task with the given number in the list.");
        }
    }

    /**
     * Deletes a specific task from the given list.
     * @param command the 'delete' command to be processed and deleted (as a task) from the list.
     * @param tasks the list of tasks to be modified.
     * @throws VergilException if the 'delete' command's formatting is invalid, or if there is no matching task
     * in the list.
     */
    public static void deleteTask(String command, List<Task> tasks) throws VergilException {
        try {
            int taskSerialNum = Integer.parseInt(command.split(" ")[1]);
            Task t = tasks.remove(taskSerialNum - 1);
            System.out.println("Acknowledged. Task deleted:");
            System.out.printf("   %s\n", t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new VergilException("Sorry! 'delete' commands should be typed as follows:\n"
                    + "    delete <task number in list>");
        } catch (IndexOutOfBoundsException e) {
            throw new VergilException("Sorry! There is no task with the given number in the list.");
        }
    }

    /**
     * Displays the current tasks on the given list.
     * @param tasks the list of tasks to be displayed.
     */
    public static void listTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    public static void save(List<Task> tasks) throws VergilException {
        try {
            FileWriter fw = new FileWriter("data/vergil_list.sav");
            for (Task t:tasks) {
                fw.write(t.getSaveString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new VergilException("Error! Unable to save list.");
        }
    }

    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("data/vergil_list.sav"));

            while (scan.hasNextLine()) {
                // INDEX | ITEM
                // ------------
                // 0     | Task type
                // 1     | Task description
                // 2     | Task completion status
                // 3     | Task time (if any)
                String[] taskDetails = scan.nextLine().split("::");
                switch (taskDetails[0]) {
                case "t":
                    tasks.add(new Todo(
                            taskDetails[1],
                            Boolean.parseBoolean(taskDetails[2])));
                    break;
                case "d":
                    tasks.add(new Deadline(
                            taskDetails[1],
                            LocalDateTime.parse(taskDetails[3]),
                            Boolean.parseBoolean(taskDetails[2])));
                    break;
                case "e":
                    tasks.add(new Event(taskDetails[1],
                            LocalDateTime.parse(taskDetails[3]),
                            Boolean.parseBoolean(taskDetails[2])));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            // No save file found; create a new file if there are any new tasks.
        }

        return tasks;
    }
}
