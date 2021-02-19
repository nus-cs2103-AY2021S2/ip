import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
                if (index > tasks.listSize() || index <= 0) {
                    throw new DukeException("The list item number provided is invalid");
                }
                output = tasks.markAsDone(index - 1);
                ArrayList<Task> allTasks = tasks.getTaskList();
                storage.writeTasksToFile(allTasks);
            } else if (input.startsWith("todo")) {
                String description = input.replace("todo", "");
                if (description.strip().equals("")) {
                    throw new DukeException("todo description cannot be empty");
                }
                ToDo todo = new ToDo(description);
                tasks.addTask(todo);
                output = tasks.addedTaskDetails(todo);
                storage.writeTasksToFile(tasks.getTaskList());
            } else if (input.startsWith("deadline")) {
                if (input.strip().equals("deadline")) {
                    throw new DukeException("deadline description cannot be empty");
                }
                if (!input.contains("/by")) {
                    throw new DukeException("Wrong command format: Missing `/by`");
                }
                if (input.split("/by").length < 2) {
                    throw new DukeException("deadline date/time cannot be empty");
                }
                String description = input.split("/by", 2)[0].replace("deadline", "");
                LocalDate date = LocalDate.parse(input.split("/by", 2)[1].strip());
                Deadline deadline = new Deadline(description, date);
                tasks.addTask(deadline);
                output = tasks.addedTaskDetails(deadline);
                storage.writeTasksToFile(tasks.getTaskList());
            } else if (input.startsWith("event")) {

                if (input.strip().equals("event")) {
                    throw new DukeException("event description cannot be empty");
                }
                if (!input.contains("/at")) {
                    throw new DukeException("Wrong command format: Missing `/at`");
                }
                if (input.split("/at").length < 2) {
                    throw new DukeException("event date/time cannot be empty");
                }
                String description = input.split("/at", 2)[0].replace("event", "");
                LocalDate date = LocalDate.parse(input.split("/at", 2)[1].strip());

                Event event = new Event(description, date);
                tasks.addTask(event);
                output = tasks.addedTaskDetails(event);
                storage.writeTasksToFile(tasks.getTaskList());

            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.replaceAll("[^-0-9]", ""));
                if (index > tasks.listSize() || index <= 0) {
                    throw new DukeException("The list item number provided is invalid");
                }
                output = tasks.deleteTask(index - 1);
                storage.writeTasksToFile(tasks.getTaskList());
            } else if (input.startsWith("find")) {
                if (input.split(" ").length < 2) {
                    throw new DukeException("Please enter a keyword to search for");
                }
                output = tasks.findTask(input.split(" ")[1]);
            } else if (input.equals("sort")) {
                output = tasks.sortAlphabetically();
                storage.writeTasksToFile(tasks.getTaskList());
            } else if (input.startsWith("bye")) {
                output = "Goodbye and see you soon!";
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            output = e.getMessage();
        } catch (NumberFormatException e) {
            output = e.getMessage();
        } catch (IOException e) {
            output = e.getMessage();
        } catch (DateTimeParseException wrongFormat) {
            output = wrongFormat.getMessage();

        }
        return output;

    }
}
