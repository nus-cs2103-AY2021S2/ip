package duke.ui;

import java.util.List;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.filesaver.FileSaver;
import duke.task.Task;
import duke.task.TaskList;

/**
 * This UI class handles the output string of Duke
 * @author WangYihe
 * @author E0424695
 */
public class Ui {
    private static String logo = " ____           _        \n"
            + "|   _  \\ _    _ | |    _____ \n"
            + "|  |  |  |  |  |  |  | / /  _  \\\n"
            + "|  |_|   |  |_|  |   <    ___/\n"
            + "|____/ \\__,__|_|\\_\\_____|\n";
    private static String line = "____________________________\n";
    //public static String indentation = "    ";

    public final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void printLine() {
        System.out.println(line);
    }

    public String printMessage(String output) {
        return line + output + "\n" + line;
    }

    /**
     * @return greeting message
     */
    public String greetingMessage() {
        return printMessage("Hello! I'm Duke. What I can do for you?");
    }

    /**
     * print greeting message
     */
    public String greeting() {
        return line + logo + greetingMessage();
    }

    /**
     * return bye message
     */
    public String bye() {
        return line
                + "Bye. Hope to see you again soon!\n"
                + line;
    }

    /**
     * report how many tasks are there
     */
    public String reportTask(Task t, TaskList task) {
        int count = task.getSize();
        String output = "Got it, I've added this task to the list:\n"
                + t.toString()
                + "\n"
                + "You now have "
                + count
                + " task in the list.\n";
        assert count >= 0 : "Size is less than 0";
        return output;
    }

    /**
     * return error message
     */
    public String printErrorMessage(String message) {
        return printMessage("☹ OOPS!!! " + message);
    }

    /**
     * parser user input
     */
    public Command getUserInputType(String userInput) throws DukeException {
        try {
            return Command.valueOf(userInput.toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new DukeException("Sorry, I dont understand what that means :-(");
        }
    }

    /**
     * reported on the finded task
     */
    public String reportFindedTask(List<Task> task) {
        if (task.isEmpty()) {
            return "There is no matching tasks in your list";
        }
        String ans = "Here are the matching tasks in your list\n";
        for (int i = 0; i < task.size(); i++) {
            int index = i + 1;
            ans += index + "." + task.get(i).toString() + "\n";
        }
        return ans;
    }

    /**
     * parser for response user input
     */
    public String getResponse(String userInput, TaskList task, FileSaver fs) {
        String response;
        try {
            String[] input = userInput.split(" ", 2);
            Command command = getUserInputType(input[0]);
            switch (command) {
            case DEADLINE:
            case TODO:
            case EVENT:
                response = task.add(input, this);
                break;
            case DELETE:
                response = task.deleteTask(input[1], this);
                break;
            case LIST:
                response = task.printTask();
                break;
            case DONE:
                response = task.doneTask(input[1], this);
                break;
            case FIND:
                response = reportFindedTask(task.findTask(input[1]));
                break;
            //case BYE:
            //bye();
            //run = false;
            //break;
            default:
                throw new DukeException("Sorry, I dont understand that");
            }
            fs.save(task);
        } catch (DukeException e) {
            // TODO: handle exception
            response = e.getMessage();
        }
        return printMessage(response);
    }
}
