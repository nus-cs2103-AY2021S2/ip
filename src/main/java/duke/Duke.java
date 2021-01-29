package duke;

/**
 * The Duke program implements an application that
 * allows users to store Tasks in a list, which will
 * be saved in between sessions.
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;

    /**
     * Class constructor.
     * @param ui handles interactions with user.
     * @param storage handles loading and saving of Tasks.
     * @param parser handles user commands.
     * @param taskList contains list of Tasks.
     */
    public Duke(Ui ui, Storage storage, Parser parser, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.parser = parser;
        this.taskList = taskList;
    }

    /**
     * This method executes the main logic of the Duke program.
     */
    public void run() {
        ui.greet();
        boolean isTerminated = false;
        while (!isTerminated) {
            try {
                String input = ui.readInput();
                Command command = parser.parseCommand(input);
                switch (command) {
                case TODO:
                    ToDo toDo = parser.parseToDo(input);
                    taskList.addTask(toDo);
                    storage.writeToFile(taskList);
                    ui.printTaskAddedConfirmation(toDo, taskList);
                    break;
                case DEADLINE:
                    Deadline deadline = parser.parseDeadline(input);
                    taskList.addTask(deadline);
                    storage.writeToFile(taskList);
                    ui.printTaskAddedConfirmation(deadline, taskList);
                    break;
                case EVENT:
                    Event event = parser.parseEvent(input);
                    taskList.addTask(event);
                    storage.writeToFile(taskList);
                    ui.printTaskAddedConfirmation(event, taskList);
                    break;
                case LIST:
                    ui.listTasks(taskList);
                    break;
                case FIND:
                    String keyword = parser.parseKeyword(input);
                    TaskList matchingTasks = taskList.findMatchingTasks(keyword);
                    ui.printMatchingTasks(matchingTasks);
                    break;
                case DONE:
                    int doneIndex = parser.parseDone(input, taskList);
                    Task doneTask = taskList.getTask(doneIndex);
                    taskList.markTaskAsDone(doneIndex);
                    storage.writeToFile(taskList);
                    ui.printTaskDoneConfirmation(doneTask);
                    break;
                case DELETE:
                    int deleteIndex = parser.parseDelete(input, taskList);
                    Task deleteTask = taskList.getTask(deleteIndex);
                    taskList.deleteTask(deleteIndex);
                    storage.writeToFile(taskList);
                    ui.printTaskDeleteConfirmation(taskList, deleteTask);
                    break;
                case BYE:
                    ui.printByeMessage();
                    isTerminated = true;
                    break;
                }
            } catch (DukeException dukeException) {
                ui.printDukeExceptionMessage(dukeException);
            }
        }
    }

    /**
     * Initialises a Duke object after loading a saved file
     * of Tasks if it exists, then executes main logic.
     * @param args unused.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser parser = new Parser();
        TaskList taskList = storage.readFromFile();
        new Duke(ui, storage, parser, taskList).run();
    }
}