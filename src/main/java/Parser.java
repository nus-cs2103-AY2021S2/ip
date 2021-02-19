import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Parser which deals with making sense of user commands
 */
public class Parser {
    /**
     * Extracts information from the user input and performs the appropriate actions
     *
     * @param input   String representing input entered by user
     * @param tasks   Class containing the list of tasks being tracked
     * @param storage Class dealing with loading tasks from the file and saving tasks in the file
     * @return String with the output of the requested action / error message.
     */
    public static String parseInput(String input, TaskList tasks, Storage storage) {
        String output;
        try {
            if (input.equals("list")) {
                output = tasks.listTasks();
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.replaceAll("[^-0-9]", ""));
                output = tasks.markAsDone(index - 1);
            } else if (input.startsWith("todo")) {
                String description = input.replace("todo", "");
                ToDo todo = new ToDo(description);
                tasks.addTask(todo);
                output = tasks.addedTaskDetails(todo);
            } else if (input.startsWith("deadline")) {
                String description = input.split("/by", 2)[0].replace("deadline", "");
                LocalDate date = LocalDate.parse(input.split("/by", 2)[1].strip());
                Deadline deadline = new Deadline(description, date);
                output = tasks.addTask(deadline);
            } else if (input.startsWith("event")) {
                String description = input.split("/at", 2)[0].replace("event", "");
                LocalDate date = LocalDate.parse(input.split("/at", 2)[1].strip());
                Event event = new Event(description, date);
                output = tasks.addTask(event);
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.replaceAll("[^-0-9]", ""));
                output = tasks.deleteTask(index - 1);
            } else if (input.startsWith("find")) {
                if (input.split(" ").length < 2) {
                    throw new DukeException("Please enter a keyword to search for");
                }
                output = tasks.findTask(input.split(" ")[1]);
            } else if (input.equals("sort")) {
                output = tasks.sortAlphabetically();
            } else if (input.startsWith("bye")) {
                output = "Goodbye and see you soon!";
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            storage.writeTasksToFile(tasks.getTaskList());
        } catch (DukeException e) {
            output = e.getMessage();
        } catch (NumberFormatException e) {
            output = "Please enter a task number";
        } catch (ArrayIndexOutOfBoundsException e) {
            output = "Please make sure the command format is correct";
        } catch (IOException e) {
            output = e.getMessage();
        } catch (DateTimeParseException wrongFormat) {
            output = "Invalid Date Format: " + wrongFormat.getMessage();
        }
        return output;
    }
}
