package duke;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke Bot that interprets user input.
     *
     * @param filePath Location of the storage list
     */
    public Duke(String[] filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            ui.showError(e);
            this.tasks = new TaskList();
        }
    }

    private void run() {
        this.ui.greet();
        while (true) {
            String[] commandArr = Parser.parseCommand(this.ui.readCommand());
            String[] params;
            int index;
            DukeCommand command = DukeCommand.fromString(commandArr[0]);
            try {
                switch (command) {
                case BYE:
                    this.ui.bye();
                    return;
                case UNKNOWN:
                    this.ui.sendToUser("Command not recognised.");
                    break;
                case LIST:
                    this.ui.sendToUser(this.tasks.listTasks());
                    break;
                case DELETE:
                    params = Parser.parseParams(command, commandArr[1]);
                    index = Parser.parseInt(params[0]);
                    this.ui.sendToUser(this.tasks.deleteTask(index));
                    break;
                case DONE:
                    params = Parser.parseParams(command, commandArr[1]);
                    index = Parser.parseInt(params[0]);
                    this.ui.sendToUser(this.tasks.doTask(index));
                    break;
                case FIND:
                    params = Parser.parseParams(command, commandArr[1]);
                    this.ui.sendToUser(this.tasks.findTask(params[0]));
                    break;
                case TODO:
                    params = Parser.parseParams(command, commandArr[1]);
                    this.ui.sendToUser(this.tasks.addTask(
                        new Todo(params[0], TaskType.TODO)
                    ));
                    break;
                case EVENT:
                    params = Parser.parseParams(command, commandArr[1]);
                    this.ui.sendToUser(this.tasks.addTask(
                        new Event(params[0], TaskType.EVENT, params[1])
                    ));
                    break;
                case DEADLINE:
                    params = Parser.parseParams(command, commandArr[1]);
                    this.ui.sendToUser(this.tasks.addTask(
                        new Deadline(params[0], TaskType.DEADLINE, params[1])
                    ));
                    break;
                default:
                    this.ui.showError();
                }
                this.storage.save(this.tasks);
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(new String[]{"data", "duke.txt"}).run();
    }
}

enum DukeCommand {
    UNKNOWN,
    BYE,
    LIST,
    DELETE,
    DONE,
    FIND,
    TODO,
    EVENT,
    DEADLINE;

    public static DukeCommand fromString(String command) {
        for (int i = 1; i < DukeCommand.values().length; i++) {
            if (DukeCommand.values()[i].toString().equals(command.toUpperCase())) {
                return DukeCommand.values()[i];
            }
        }
        return DukeCommand.values()[0];
    }
}
