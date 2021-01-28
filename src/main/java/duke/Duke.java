package duke;

public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;

    public Duke(Ui ui, Storage storage, Parser parser, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.parser = parser;
        this.taskList = taskList;
    }

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

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser parser = new Parser();
        TaskList taskList = storage.readFromFile();
        new Duke(ui, storage, parser, taskList).run();
    }
}