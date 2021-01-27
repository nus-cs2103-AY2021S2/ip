import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static final DateTimeFormatter formatter = new DateTimeFormatterBuilder().
            appendPattern("[d/M/yyyy HHmm]").
            appendPattern("[d/M/yyyy]").
            appendPattern("[yyyy-M-d]").
            appendPattern("[yyyy-M-d HH:mm]").
            appendPattern("[MMM d yyyy]").
            parseDefaulting(ChronoField.HOUR_OF_DAY, 0).
            parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).
            toFormatter(Locale.ENGLISH);

    private static void printIndentOutput(String output) {
        System.out.println('\t' + output);
    }

    private static void printHorizontalLine() {
        printIndentOutput("_____________________________________________________________________");
    }

    private static boolean checkMatchString(String line, String match) {
        return line.length() >= match.length() && line.startsWith(match);
    }

    private static void printTaskListStatus(TaskList tasks, Task curTask) {
        printIndentOutput("Got it. I've added this task:");
        printIndentOutput("   " + curTask);
        printIndentOutput("Now you have " + tasks.size() + " task(s) in the list.");
    }

    private static String[] getCommandArgs(String line, String errorMessage) throws DukeException {
        String[] cmdArgs = line.split(" ", 2);
        if (cmdArgs.length < 2) {
            throw new DukeException(errorMessage);
        }
        return cmdArgs;
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList tasks;
        try {
            tasks = storage.readTasks();
        } catch(IOException e) {
            System.err.println("Unable to create file");
            tasks = new TaskList();
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printHorizontalLine();
        printIndentOutput("What can I do for you?");
        printHorizontalLine();

        Scanner stdin = new Scanner(System.in);
        String line = stdin.nextLine();
        boolean end = false;

        while (line != null) {
            printHorizontalLine();

            try {
                if (line.equals("bye")) {
                    printIndentOutput("Bye. Hope to see you again soon!");
                    end = true;
                } else if (line.equals("list")) {
                    if (tasks.size() <= 0) {
                        printIndentOutput("The current list is empty.");
                    } else {
                        printIndentOutput("Here are the tasks in you list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            printIndentOutput((i + 1) + ". " + tasks.getTask(i));
                        }
                    }
                } else if (checkMatchString(line, "done ")) {
                    String[] cmdArgs = getCommandArgs(line, "I'm sorry, but done needs the index of a Task.");
                    try {
                        int index = Integer.parseInt(cmdArgs[1]);
                        Task curTask = tasks.getTask(index - 1);
                        curTask.markAsDone();
                        printIndentOutput("Nice! I've marked this task as done:");
                        printIndentOutput("   " + curTask);
                    } catch (NumberFormatException e) {
                        throw new DukeException("The index of the task needs to be an integer.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("The index of the task needs to be present in the list.");
                    }
                } else if (checkMatchString(line, "todo ")) {
                    String[] cmdArgs = getCommandArgs(line, "The description of a todo cannot be empty.");
                    String taskName = cmdArgs[1];
                    Task curTask = new Todo(taskName);
                    tasks.addTask(curTask);
                    printTaskListStatus(tasks, curTask);
                } else if (checkMatchString(line, "deadline ")) {
                    String[] cmdArgs = getCommandArgs(line, "The description of a todo cannot be empty.");
                    String[] deadlineArgs = cmdArgs[1].split(" /by ", 2);
                    if (deadlineArgs.length < 2) {
                        throw new DukeException("The deadline needs to have a date specified with \"/by\".");
                    }
                    String taskName = deadlineArgs[0];
                    LocalDateTime deadline = LocalDateTime.parse(deadlineArgs[1], formatter);
                    Task curTask = new Deadline(taskName, deadline);
                    tasks.addTask(curTask);
                    printTaskListStatus(tasks, curTask);
                } else if (checkMatchString(line, "event ")) {
                    String[] cmdArgs = getCommandArgs(line, "The description of an event cannot be empty.");
                    String[] eventArgs = cmdArgs[1].split(" /at ", 2);
                    if (eventArgs.length < 2) {
                        throw new DukeException("The event needs to have a date specified with \"/at\".");
                    }
                    String taskName = eventArgs[0];
                    LocalDateTime datetime = LocalDateTime.parse(eventArgs[1], formatter);
                    Task curTask = new Event(taskName, datetime);
                    tasks.addTask(curTask);
                    printTaskListStatus(tasks, curTask);
                } else if (checkMatchString(line, "delete ")) {
                    String[] cmdArgs = getCommandArgs(line, "I'm sorry, but delete needs the index of a Task.");
                    try {
                        int index = Integer.parseInt(cmdArgs[1]);
                        Task curTask = tasks.getTask(index - 1);
                        tasks.removeTask(index - 1);
                        printIndentOutput("Nice! I've removed this task:");
                        printIndentOutput("   " + curTask);
                        printIndentOutput("Now you have " + tasks.size() + " task(s) in the list.");
                    } catch (NumberFormatException e) {
                        throw new DukeException("The index of the task needs to be an integer.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("The index of the task needs to be present in the list.");
                    }
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
                if (storage != null) {
                    try {
                        storage.writeTasks(tasks);
                    } catch (IOException e) {
                        System.err.println("Unable to write to file");
                    }
                }
            } catch (DukeException e) {
                printIndentOutput("OOPSIE!! " + e.getMessage());
            }

            printHorizontalLine();

            if (end) break;

            line = stdin.nextLine();
        }
    }
}
