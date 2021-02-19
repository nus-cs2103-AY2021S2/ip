package duke;

import java.util.ArrayList;

import duke.command.CreateCommand;
import duke.command.DeleteCommand;
import duke.command.DetectDuplicateCommand;
import duke.command.FindCommand;
import duke.command.ReadCommand;
import duke.command.UpdateCommand;

/**
 * Parser class which handles the parsing of user's input and delivers the expected action accordingly
 */
public class Parser {

    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public Parser() {

    }

    /**
     * Gets the tasklist
     *
     * @return the tasklist
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a new task into the existing tasklist
     *
     * @param newTask Task to be added
     */
    public static String addTask(Task newTask) {
        taskList.add(newTask);
        return ("Got it. I've added this task:" + "\n" + newTask.toString()
                + "\n" + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Lists out all the tasks currently in the tasklist
     */
    public static String listTask() {
        String output = "Here are the task in your list:" + "\n";
        for (int i = 0; i < taskList.size(); ++i) {
            output += Integer.toString(i + 1);
            output = output + ". " + taskList.get(i) + "\n";
        }
        return output;
    }

    /**
     * Deletes a specific task from the tasklist
     *
     * @param i The numbering of the task to be deleted in chronological order
     */
    public static String deleteTask(int i) {
        Task task = taskList.get(i - 1);
        taskList.remove(i - 1);
        return ("Noted. I've removed this task:" + "\n" + task.toString()
                + "\n" + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Marks a current task on the tasklist as done
     *
     * @param i The numbering of the task to be marked as done in chronological order
     */
    public static String markDone(int i) {
        Task task = taskList.get(i - 1);
        task.done();
        String output = "Nice! I've marked this task as done: " + "\n" + task.toString();
        return output;
    }

    /**
     * Reads user input and parse it accordingly so as to deliver the according action correctly
     * @throws DukeException if user input is empty or invalid
     * @throws Exception
     */
    public static String read(String input) throws DukeException, Exception {
        if (input.equals("")) {
            throw new EmptyArgument("OOPS!!! The user input cannot be empty.");
        }
        if (input.startsWith("done")) {
            return UpdateCommand.runCommand(input);
        } else if (input.startsWith("delete")) {
            return DeleteCommand.runCommand(input);
        } else if (input.startsWith("event") || input.startsWith("deadline") || input.startsWith("todo")) {
            return CreateCommand.runCommand(input);
        } else if (input.equals("bye")) {
            return Ui.exit();
        } else if (input.equals("list")) {
            return ReadCommand.runCommand();
        } else if (input.equals("save")) {
            return Storage.saveTaskList();
        } else if (input.equals("load")) {
            return Storage.loadTaskList();
        } else if (input.startsWith("find")) {
            return FindCommand.runCommand(input);
        } else if (input.equals("detect")) {
            return DetectDuplicateCommand.runCommand();
        } else if (input.equals("clean")) {
            return DetectDuplicateCommand.cleanDuplicates();
        } else if (input.equals("help")) {
            return Ui.helpGuide();
        } else {
            throw new InvalidArgument("Your input is invalid, Please try again");
        }
    }

}
