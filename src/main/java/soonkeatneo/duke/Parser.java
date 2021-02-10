package soonkeatneo.duke;

import java.time.format.DateTimeParseException;

import soonkeatneo.duke.task.Deadline;
import soonkeatneo.duke.task.Event;
import soonkeatneo.duke.task.Todo;
import static soonkeatneo.duke.AddTask.addTask;

/**
 * Implementation for parsing of input to be routed to specific handlers.
 *  @author Soon Keat Neo
 *  @version CS2103T AY20/21 Sem 2 iP
 */

public class Parser {
    /**
     * Handle the input and passes to the relevant methods.
     * @param inputString User input string to be handled
     * @param tasks Task List to be manipulated
     * @param storage {@Storage} object to be used
     */
    public static String parse(String inputString, TaskList tasks, Storage storage) {
        if (inputString.equals("list")) {
            return tasks.print();
        } else if (inputString.equals("bye")) {
            System.exit(0);
        } else if (inputString.startsWith("done")) {
            return tasks.completeTask(inputString, storage);
        } else if (inputString.startsWith("delete")) {
            return tasks.deleteTask(inputString, storage);
        } else if (inputString.startsWith("todo")) {
            return addTask("T", inputString, tasks, storage);
        } else if (inputString.startsWith("event")) {
            return addTask("E", inputString, tasks, storage);
        } else if (inputString.startsWith("deadline")) {
            return addTask("D", inputString, tasks, storage);
        } else if (inputString.startsWith("find")) {
            try {
                return tasks.find(inputString.substring(5).trim());
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidInputException(
                        "Your input format doesn't seem right! For find, it needs to be: find <string>");
            }
        } else {
            throw new InvalidCommandException();
        }
        return "";
    }
}
