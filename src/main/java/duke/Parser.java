package duke;

import java.time.LocalDate;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {
    /**
     * Handle the input and passes to the relevant methods.
     * @param inputString User input string to be handled.
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
                throw new InvalidInputException();
            }
        } else if (inputString.startsWith("event")) {
            try {
                String[] eventString = inputString.split("/at");
                String taskString = eventString[0].substring(6).trim();
                String dateTime = eventString[1].trim();
                LocalDate eventTime = LocalDate.parse(dateTime);
                Event newEvent = new Event(taskString, eventTime);
                tasks.addTask(newEvent);
                String saveToDisk = "E | 0 | " + taskString + " | " + eventTime;
                storage.saveTaskToDisk(saveToDisk);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException();
            }
        } else if (inputString.startsWith("deadline")) {
            try {
                String[] eventString = inputString.split("/by");
                String taskString = eventString[0].substring(9).trim();
                LocalDate deadlineTime = LocalDate.parse(eventString[1].trim());
                Deadline newDeadline = new Deadline(taskString, deadlineTime);
                tasks.addTask(newDeadline);
                String saveToDisk = "D | 0 | " + taskString + " | " + deadlineTime;
                storage.saveTaskToDisk(saveToDisk);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }
}
