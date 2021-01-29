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
        boolean byeFlag = false;
        while (!byeFlag) {
            try {
                String input = ui.readInput();
                Command command = parser.parseCommand(input);
                switch (command) {
                case TODO:
                    ToDo t = parser.parseToDo(input);
                    taskList.addTask(t);
                    storage.writeToFile(taskList);
                    ui.taskAddConfirmation(t, taskList);
                    break;
                case DEADLINE:
                    Deadline d = parser.parseDeadline(input);
                    taskList.addTask(d);
                    storage.writeToFile(taskList);
                    ui.taskAddConfirmation(d, taskList);
                    break;
                case EVENT:
                    Event e = parser.parseEvent(input);
                    taskList.addTask(e);
                    storage.writeToFile(taskList);
                    ui.taskAddConfirmation(e, taskList);
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
                    ui.taskDoneConfirmation(doneTask);
                    break;
                case DELETE:
                    int deleteIndex = parser.parseDelete(input, taskList);
                    Task deleteTask = taskList.getTask(deleteIndex);
                    taskList.deleteTask(deleteIndex);
                    storage.writeToFile(taskList);
                    ui.taskDeleteConfirmation(taskList, deleteTask);
                    break;
                case BYE:
                    ui.byeMessage();
                    byeFlag = true;
                    break;
                }
            } catch (DukeException e) {
                ui.dukeExceptionMessage(e);
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