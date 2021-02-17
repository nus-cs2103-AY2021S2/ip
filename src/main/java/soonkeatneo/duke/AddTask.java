package soonkeatneo.duke;

import java.time.format.DateTimeParseException;

import soonkeatneo.duke.task.Deadline;
import soonkeatneo.duke.task.Event;
import soonkeatneo.duke.task.Todo;

/**
 * Implementation for the adding of tasks to the TaskList.
 *  @author Soon Keat Neo
 *  @version CS2103T AY20/21 Sem 2 iP
 */

public class AddTask {
    /**
     * Handles addition of tasks into the TaskList and returns confirmation message.
     * @param type type of task to be added
     * @param inputString string containing description of the task
     * @param tasks {@TaskList} to be manipulated
     * @param storage {@Storage} to be manipulated
     * @return String confirmation of success/failure
     */
    public static String addTask(String type, String inputString, TaskList tasks, Storage storage) {
        if (type.equals("T")) {
            try {
                String taskString = inputString.substring(5);
                Todo newTodo = new Todo(taskString);
                String saveToDisk = "T | 0 | " + taskString;
                storage.saveTaskToDisk(saveToDisk);
                return tasks.addTask(newTodo);
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidInputException(
                        "Your input format doesn't seem right! For todos, it needs to be: todo <title>");
            }
        } else if (type.equals("E")) {
            try {
                String[] eventString = inputString.split("/at");
                String taskString = eventString[0].substring(6).trim();
                String dateTime = eventString[1].trim();
                Event newEvent = new Event(taskString, dateTime);
                String saveToDisk = "E | 0 | " + taskString + " | " + dateTime;
                storage.saveTaskToDisk(saveToDisk);
                return tasks.addTask(newEvent);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException(
                        "Your input format doesn't seem right!"
                                + " For events, it needs to be: event <title> /at [YYYY-MM-DD]");
            } catch (DateTimeParseException e) {
                throw new InvalidInputException(
                        "The format of your date and time seem to be wrong! Ensure it adheres to YYYY-MM-DD format.");
            }
        } else if (type.equals("D")) {
            try {
                String[] eventString = inputString.split("/by");
                String taskString = eventString[0].substring(9).trim();
                String deadlineTime = eventString[1].trim();
                Deadline newDeadline = new Deadline(taskString, deadlineTime);
                String saveToDisk = "D | 0 | " + taskString + " | " + deadlineTime;
                storage.saveTaskToDisk(saveToDisk);
                return tasks.addTask(newDeadline);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException(
                        "Your input format doesn't seem right!"
                                + " For deadlines, it needs to be: deadline <title> /by [YYYY-MM-DD]");
            } catch (DateTimeParseException e) {
                throw new InvalidInputException(
                        "The format of your date and time seem to be wrong! Ensure it adheres to YYYY-MM-DD format.");
            }
        }
        return "";
    }
}
