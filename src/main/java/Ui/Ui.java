package Ui;

import TaskList.TaskList;
import Tasks.DukeTask;

import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    private final String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";



    public void introduction() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Please enter the keyword:\n" +
                "        help\n" + "to see the list of commands\n");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    public void help() {
        System.out.println("Here is the list of commands:"
                + "\nShow list of commands:\n - help"
                + "\nExit duke:\n - bye"
                + "\nDisplay list of tasks:\n - list"
                + "\nDelete task:\n - delete <task id>"
                + "\nSet task as completed:\n - done <task id>"
                + "\nFilter tasks by keyword keyword:\n - find <keyword>"
              //  + "\nFilter tasks by due date:\n - filter <yyyy-mm-dd>"
                + "\nAdd a new task:\n - todo <task>"
                + "\nAdd a new deadline:\n - deadline <task> /by <yyyy-mm-dd>"
                + "\nAdd a new event:\n - event <name> /at <yyyy-mm-dd> <hh:mm> - <hh:mm>"
             //   + "\nAdd a new weekly recurring task:\n - weekly <name> /every <day> <hhmm>\n"
        + "\n**REMEMBER to exclude the < > when entering keywords\n");

    }

    public void preload() {
        System.out.println("--Booting up Application--");
        System.out.println("Checking if loading data exist...\n");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        return line;
    }

    public void add(DukeTask task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println(String.format("Now you have %d tasks in the list.\n", size));
    }

    public void delete(DukeTask task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println(String.format("Now you have %d tasks in the list.\n", size));
    }

    public void done(DukeTask task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task + "\n");
    }

    public void list(TaskList taskList) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (DukeTask items : taskList.getList()) {
            System.out.println(String.format("%d.%s", i, items));
            i++;
        }
        System.out.println("");
    }

    public void loadFileError() {
        System.out.println("An error occurred while loading!!");
    }

    public void arrayIndexOutOfBoundsError() {
        System.out.println("☹ OOPS!!! The index needs to be in range of the list.\n");
    }

    public void emptyIndexError() {
        System.out.println("☹ OOPS!!! I need the index of the task you want done.\n");
    }

    public void emptyDescriptionError(String tasktype) {
        System.out.println("☹ OOPS!!! The description of a " + tasktype + " cannot be empty.\n");
    }

    public void emptyDetailsError(String tasktype) {
        if (tasktype.equals("deadline")) {
            System.out.println("☹ OOPS!!! The date of a deadline cannot be empty.\n");
        } else {
            System.out.println("☹ OOPS!!! The timing of an event cannot be empty.\n");
        }
    }

    public void unknownCommandError() {
        System.out.println("☹ OOPS!!! Command is not recognized!!\n");
    }
}

