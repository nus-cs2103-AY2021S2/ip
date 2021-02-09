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
        String command = parser.getCommand();
        String output;

        try {
                // user exits the program
                if (command.equals("bye")) {
                    output = ui.displayClosingMessage();

                    // user wants list of tasks
                } else if (command.equals("list")) {
                    output = ui.displayListMessage(taskList);

                    // user wants to complete a task
                } else if (command.equals("done")) {
                    int index = parser.getIndexToModify();

                    assert index >= 1;

                    taskList = taskList.completeTask(index);
                    output = ui.displayTaskCompleted(taskList.getTask(index));
                    storage.writeFile(taskList);

                    // user wants to delete a task
                } else if (command.equals("delete")) {
                    int index = parser.getIndexToModify();

                    assert index >= 1;

                    Task task = taskList.getTask(index);
                    taskList = taskList.deleteTask(index);
                    output = ui.displayTaskDeleted(task, taskList);
                    storage.writeFile(taskList);

                    // user wants to add a ToDo
                } else if (command.equals("todo")) {
                    String taskDesc = parser.getTaskDescription();
                    ToDo newTask = new ToDo(taskDesc);
                    taskList = taskList.addTask(newTask);
                    output = ui.displayTaskAdded(newTask, taskList);
                    storage.writeFile(taskList);

                    // user wants to add a Deadline
                } else if (command.equals("deadline")) {
                    String taskDesc = parser.getTaskDescription();
                    LocalDate date = LocalDate.parse(parser.getDate(),
                            DateTimeFormatter.ofPattern("d/MM/yyyy"));
                    LocalTime time = LocalTime.parse(parser.getTime(),
                            DateTimeFormatter.ofPattern("HHmm"));

                    Deadline newTask = new Deadline(taskDesc, date, time);
                    taskList = taskList.addTask(newTask);
                    output = ui.displayTaskAdded(newTask, taskList);
                    storage.writeFile(taskList);

                    // user wants to add an Event
                } else if (command.equals("event")) {
                    String taskDesc = parser.getTaskDescription();
                    LocalDate date = LocalDate.parse(parser.getDate(),
                            DateTimeFormatter.ofPattern("d/MM/yyyy"));
                    LocalTime time = LocalTime.parse(parser.getTime(),
                            DateTimeFormatter.ofPattern("HHmm"));

                    Event newTask = new Event(taskDesc, date, time);
                    taskList = taskList.addTask(newTask);
                    output = ui.displayTaskAdded(newTask, taskList);
                    storage.writeFile(taskList);

                // user wants to find tasks using a keyword
                } else if (command.equals("find")) {
                    String keyword = parser.getKeyword();
                    output = ui.displayTaskSearch(keyword, taskList);

                } else {
                    throw new DukeException("unknown");
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

