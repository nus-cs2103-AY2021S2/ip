/**
 * This UI class handles the output string of Duke
 * 
 * @param logo Duke logo
 * @param line Indentation line
 * @param terminate_input input to end Duke
 * @param indentation spaces to aline the output string
 * 
 * @param sc Scanner to scan the user input
 * 
 * @author WangYihe
 * @author E0424695
 */

package duke.ui;

import duke.task.*;
import duke.exception.DukeException;
import duke.command.Command;
import duke.fileSaver.FileSaver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Ui {
    public static String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    public static String line = "_______________________________________\n";
    public static String indentation = "    ";

    public final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void printLine() {
        System.out.println(line);
    }

    public void close() {
        sc.close();
    }

    public String printMessage(String output) {
        return line + output + "\n" + line;
    }

    public String greetingMessage() {
        return printMessage("Hello! I'm Duke. What I can do for you?");
    }

    public String greeting() {
        return logo + line + greetingMessage() + line;
    }

    public String bye() {
        return line + "Bye. Hope to see you again soon!\n" + line;
    }

    public String reportTask(Task t, TaskList task) {
        int count = task.getSize();
        String output = "Got it, I've added this task to the list:\n" +
                t.toString() + "\n" +
                "You now have " + count + "task in the list.\n";
        return printMessage(output);
    }

    public String printErrorMessage(String message) {
        return printMessage("☹ OOPS!!! " + message);
    }

    public Command getUserInputType(String userInput) throws DukeException {
        try {
            return Command.valueOf(userInput.toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new DukeException("Sorry, I dont understand what that means :-(");
        }
    }

    public String reportFindedTask(List<Task> task) {
        String ans = "Here are the matching tasks in your list\n";
        for (int i = 0; i < task.size(); i++) {
            int index = i + 1;
            ans += index + "." + task.get(i).toString() + "\n";
        }
        return printMessage(ans);
    }

    public String getResponse(String userInput, TaskList task, FileSaver fs) {
        String response = " ";
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
                    response = task.printTask(this);
                    break;
                case DONE:
                    response = task.doneTask(input[1], this);
                    break;
                case FIND:
                    response = reportFindedTask(task.findTask(input[1]));
                    break;
//                case BYE:
//                    bye();
//                    run = false;
//                    break;
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
