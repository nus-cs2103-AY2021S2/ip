package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Implementation for parsing of input to be routed to specific handlers.
 *  @author Soon Keat Neo
 *  @version CS2103T AY20/21 Sem 1 iP
 */

public class Parser {
    /**
     * Handle the input and passes to the relevant methods.
     * @param inputString User input string to be handled
     * @param tasks Task List to be manipulated
     * @param storage {@Storage} object to be used
     */
    public static void parse(String inputString, TaskList tasks, Storage storage) {

        if (inputString.equals("list")) {
            tasks.print();
        } else if (inputString.equals("bye")) {
            Duke.quit();
        } else if (inputString.startsWith("done")) {
            tasks.completeTask(inputString, storage);
        } else if (inputString.startsWith("delete")) {
            tasks.deleteTask(inputString, storage);
        } else if (inputString.startsWith("todo")) {
            try {
                String taskString = inputString.substring(5);
                Todo newTodo = new Todo(taskString);
                tasks.addTask(newTodo);
                String saveToDisk = "T | 0 | " + taskString;
                storage.saveTaskToDisk(saveToDisk);
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidInputException("Your input format doesn't seem right! For todos, it needs to be: todo <title>");
            }
        } else if (inputString.startsWith("event")) {
            try {
                String[] eventString = inputString.split("/at");
                String taskString = eventString[0].substring(6).trim();
                String dateTime = eventString[1].trim();
                Event newEvent = new Event(taskString, dateTime);
                tasks.addTask(newEvent);
                String saveToDisk = "E | 0 | " + taskString + " | " + dateTime;
                storage.saveTaskToDisk(saveToDisk);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException("Your input format doesn't seem right! For events, it needs to be: event <title> /at [YYYY-MM-DD]");
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("The format of your date and time seem to be wrong! Ensure it adheres to YYYY-MM-DD format.");
            }
        } else if (inputString.startsWith("deadline")) {
            try {
                String[] eventString = inputString.split("/by");
                String taskString = eventString[0].substring(9).trim();
                String deadlineTime = eventString[1].trim();
                Deadline newDeadline = new Deadline(taskString, deadlineTime);
                tasks.addTask(newDeadline);
                String saveToDisk = "D | 0 | " + taskString + " | " + deadlineTime;
                storage.saveTaskToDisk(saveToDisk);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException("Your input format doesn't seem right! For deadlines, it needs to be: deadline <title> /at [YYYY-MM-DD]");
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("The format of your date and time seem to be wrong! Ensure it adheres to YYYY-MM-DD format.");
            }
        } else if (inputString.startsWith("find")) {
            try {
                tasks.find(inputString.substring(5).trim());
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidInputException("Your input format doesn't seem right! For find, it needs to be: find <string>");
            }
        } else {
            throw new InvalidCommandException();
        }
    }
}
