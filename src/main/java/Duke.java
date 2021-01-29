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
     *
     * @param filePath Path of the file that contains the lists of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readFile());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Runs the program.
     *
     * @throws DukeException If user input is incorrect.
     */
    public void run() throws DukeException {

        ui.displayWelcomeMessage();

        FastIO reader = new FastIO();
        String input = reader.nextLine();
        Parser parser = new Parser(input);

        try {
            while (true) {

                String command = parser.getCommand();

                // user exits the program
                if (command.equals("bye")) {
                    ui.displayClosingMessage();
                    break;

                    // user wants list of tasks
                } else if (command.equals("list")) {
                    ui.displayListMessage(taskList);
                    parser = parser.newInput(reader.nextLine());

                    // user wants to complete a task
                } else if (command.equals("done")) {
                    int index = parser.getIndexToModify();
                    taskList = taskList.completeTask(index);
                    ui.displayTaskCompleted(taskList.getTask(index));
                    storage.writeFile(taskList);
                    parser = parser.newInput(reader.nextLine());

                    // user wants to delete a task
                } else if (command.equals("delete")) {
                    int index = parser.getIndexToModify();
                    Task task = taskList.getTask(index);
                    taskList = taskList.deleteTask(index);
                    ui.displayTaskDeleted(task, taskList);
                    storage.writeFile(taskList);
                    parser = parser.newInput(reader.nextLine());

                    // user wants to add a ToDo
                } else if (command.equals("todo")) {
                    String taskDesc = parser.getTaskDescription();
                    ToDo newTask = new ToDo(taskDesc);
                    taskList = taskList.addTask(newTask);
                    ui.displayTaskAdded(newTask, taskList);
                    storage.writeFile(taskList);
                    parser = parser.newInput(reader.nextLine());

                    // user wants to add a Deadline
                } else if (command.equals("deadline")) {
                    String taskDesc = parser.getTaskDescription();
                    LocalDate date = LocalDate.parse(parser.getDate(),
                            DateTimeFormatter.ofPattern("d/MM/yyyy"));
                    LocalTime time = LocalTime.parse(parser.getTime(),
                            DateTimeFormatter.ofPattern("HHmm"));

                    Deadline newTask = new Deadline(taskDesc, date, time);
                    taskList = taskList.addTask(newTask);
                    ui.displayTaskAdded(newTask, taskList);
                    storage.writeFile(taskList);
                    parser = parser.newInput(reader.nextLine());

                    // user wants to add an Event
                } else if (command.equals("event")) {
                    String taskDesc = parser.getTaskDescription();
                    LocalDate date = LocalDate.parse(parser.getDate(),
                            DateTimeFormatter.ofPattern("d/MM/yyyy"));
                    LocalTime time = LocalTime.parse(parser.getTime(),
                            DateTimeFormatter.ofPattern("HHmm"));

                    Event newTask = new Event(taskDesc, date, time);
                    taskList = taskList.addTask(newTask);
                    ui.displayTaskAdded(newTask, taskList);
                    storage.writeFile(taskList);
                    parser = parser.newInput(reader.nextLine());

                // user wants to find tasks using a keyword
                } else if (command.equals("find")) {
                    String keyword = parser.getKeyword();
                    ui.displayTaskSearch(keyword, taskList);
                    parser = parser.newInput(reader.nextLine());

                } else {
                    throw new DukeException("unknown");
                }
            }

        } catch (DukeException e) {
            System.out.println(e.errorMessage());

        } finally {
            reader.close();
        }
    }

    /**
     * Runs the application.
     *
     * @param args Input.
     * @throws DukeException If user input is incorrect.
     */
    public static void main(String[] args) throws DukeException {
        new Duke("dukeTaskList.txt").run();
    }

}

