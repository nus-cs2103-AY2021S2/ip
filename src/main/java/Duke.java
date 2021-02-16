import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * Task List manager that can store task lists.
 */
public class Duke {

    /** Storage to handle task list files */
    private Storage storage;

    /** TaskList to hold list of tasks */
    private TaskList taskList;

    /** Ui to display messages for user */
    private Ui ui;

    private final String UNKNOWN_MESSAGE = "unknown";

    /**
     * Creates a new Duke object that contains a list of tasks from file at given path.
     * If no file is found, an empty task list is created instead.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("dukeTaskList.txt");
        try {
            taskList = new TaskList(storage.readFile());
        } catch (DukeException e) {
            taskList = new TaskList();
        }

        assert taskList != null;
    }

    /**
     * Runs the program.
     *
     * @throws DukeException If user input is incorrect.
     */
    public String run(String input) {

        Parser parser = new Parser(input);
        String command = parser.getCommand().toUpperCase();
        String output, taskDesc;
        int index;
        LocalDate date;
        LocalTime time;
        Task newTask;

        try {
            switch (command) {

            case "bye":
                output = ui.displayClosingMessage();
                break;

            case "list":
                output = ui.displayListMessage(taskList);
                break;


            case "done":
                index = parser.getIndexToModify();
                taskList = taskList.completeTask(index);
                output = ui.displayTaskCompleted(taskList.getTask(index));
                storage.writeFile(taskList);
                break;

            case "delete":
                index = parser.getIndexToModify();
                Task task = taskList.getTask(index);
                taskList = taskList.deleteTask(index);
                output = ui.displayTaskDeleted(task, taskList);
                storage.writeFile(taskList);
                break;

            case "todo":
                taskDesc = parser.getTaskDescription();
                newTask = new ToDo(taskDesc);
                taskList = taskList.addTask(newTask);
                output = ui.displayTaskAdded(newTask, taskList);
                storage.writeFile(taskList);
                break;

            case "deadline":
                taskDesc = parser.getTaskDescription();
                date = LocalDate.parse(parser.getDate(),
                        DateTimeFormatter.ofPattern("d/MM/yyyy"));
                time = LocalTime.parse(parser.getTime(),
                        DateTimeFormatter.ofPattern("HHmm"));

                newTask = new Deadline(taskDesc, date, time);
                taskList = taskList.addTask(newTask);
                output = ui.displayTaskAdded(newTask, taskList);
                storage.writeFile(taskList);
                break;

            case "event":
                taskDesc = parser.getTaskDescription();
                date = LocalDate.parse(parser.getDate(),
                        DateTimeFormatter.ofPattern("d/MM/yyyy"));
                time = LocalTime.parse(parser.getTime(),
                        DateTimeFormatter.ofPattern("HHmm"));
                
                newTask = new Event(taskDesc, date, time);
                taskList = taskList.addTask(newTask);
                output = ui.displayTaskAdded(newTask, taskList);
                storage.writeFile(taskList);
                break;

            case "find":
                String keyword = parser.getKeyword();
                output = ui.displayTaskSearch(keyword, taskList);
                break;

            default:
                throw new DukeException(UNKNOWN_MESSAGE);

            }

        } catch (DukeException e) {
            output = e.errorMessage();
        }

        return output;
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return run(input);
    }

}

