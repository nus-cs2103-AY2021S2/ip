import models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static void printIndentOutput(String output) {
        System.out.println('\t' + output);
    }

    private static void printHorizontalLine() {
        printIndentOutput("_____________________________________________________________________");
    }

    private static boolean checkMatchString(String line, String match) {
        return line.length() >= match.length() && line.substring(0, match.length()).equals(match);
    }

    private static void printTaskListStatus(ArrayList<Task> tasks, Task curTask) {
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
        TaskFileWriter writer = null;
        try {
            writer = new TaskFileWriter();
        } catch(IOException e) {
            System.err.println("Unable to create file");
        }
        // TODO: Load from file
        ArrayList<Task> tasks = new ArrayList<>();

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
                            printIndentOutput((i + 1) + ". " + tasks.get(i));
                        }
                    }
                } else if (checkMatchString(line, "done ")) {
                    String[] cmdArgs = getCommandArgs(line, "I'm sorry, but done needs the index of a Task.");
                    try {
                        int index = Integer.parseInt(cmdArgs[1]);
                        Task curTask = tasks.get(index - 1);
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
                    tasks.add(curTask);
                    printTaskListStatus(tasks, curTask);
                } else if (checkMatchString(line, "deadline ")) {
                    String[] cmdArgs = getCommandArgs(line, "The description of a todo cannot be empty.");
                    String[] deadlineArgs = cmdArgs[1].split(" /by ", 2);
                    if (deadlineArgs.length < 2) {
                        throw new DukeException("The deadline needs to have a date specified with \"/by\".");
                    }
                    String taskName = deadlineArgs[0];
                    String deadline = deadlineArgs[1];
                    Task curTask = new Deadline(taskName, deadline);
                    tasks.add(curTask);
                    printTaskListStatus(tasks, curTask);
                } else if (checkMatchString(line, "event ")) {
                    String[] cmdArgs = getCommandArgs(line, "The description of an event cannot be empty.");
                    String[] eventArgs = cmdArgs[1].split(" /at ", 2);
                    if (eventArgs.length < 2) {
                        throw new DukeException("The event needs to have a date specified with \"/at\".");
                    }
                    String taskName = eventArgs[0];
                    String date = eventArgs[1];
                    Task curTask = new Event(taskName, date);
                    tasks.add(curTask);
                    printTaskListStatus(tasks, curTask);
                } else if (checkMatchString(line, "delete ")) {
                    String[] cmdArgs = getCommandArgs(line, "I'm sorry, but delete needs the index of a Task.");
                    try {
                        int index = Integer.parseInt(cmdArgs[1]);
                        Task curTask = tasks.get(index - 1);
                        tasks.remove(index - 1);
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
                if (writer != null) {
                    try {
                        writer.writeTasks(tasks);
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
