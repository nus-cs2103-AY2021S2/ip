import java.time.LocalDate;

/**
 * Represents a Parser which deals with making sense of user commands
 */
public class Parser {
    public static Ui ui = new Ui();

    /**
     * Extracts information from the user input and performs the appropriate actions
     *
     * @param input   String representing input entered by user
     * @param tasks   Class containing the list of tasks being tracked
     * @param storage Class dealing with loading tasks from the file and saving tasks in the file
     */
    public static void parseInput(String input, TaskList tasks, Storage storage) {
        try {
            if (input.equals("list")) {
                tasks.listTasks();
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.replaceAll("[^-0-9]", ""));
                if (index > tasks.size() || index <= 0) {
                    throw new DukeException("The list item number provided is invalid");
                }
                tasks.markAsDone(index - 1);
                storage.writeTasksToFile(tasks.getTaskList());
            } else if (input.startsWith("todo")) {
                String description = input.replace("todo", "");
                ToDo todo = new ToDo(description);
                if (description.strip().equals("")) {
                    throw new DukeException("todo description cannot be empty");
                }
                tasks.addTask(todo);
                tasks.printAddedTask(todo);
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
                tasks.printAddedTask(deadline);
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
                tasks.printAddedTask(event);
                storage.writeTasksToFile(tasks.getTaskList());

            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.replaceAll("[^-0-9]", ""));
                if (index > tasks.size() || index <= 0) {
                    throw new DukeException("The list item number provided is invalid");
                }
                tasks.deleteTask(index - 1);
                storage.writeTasksToFile(tasks.getTaskList());

            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}