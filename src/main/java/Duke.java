import duke.Task;
import duke.TaskList;
import dukeexception.DukeException;
import parser.Parser;
import storage.Storage;
import ui.Ui;

public class Duke {
    public static final String LOG_PATH = "./duke.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke object.
     * @param filepath The filepath of the list of tasks to be stored at.
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasks = new TaskList(this.storage);
        this.ui = new Ui();
    }

    /**
     * This is the main method which starts the duke chat.
     * @param args Takes in command line arguments to be processed.
     * @throws DukeException Throws exceptions if existing file is of wrong format.
     */
    public static void main(String[] args) throws DukeException {
        try {
            new Duke(LOG_PATH).run();
        } catch (DukeException e) {
            throw new DukeException("unable to load file, probably wrong format.");
        }
    }

    private void process(Parser p) throws DukeException {
        switch (p.getCommand()) {
        case "delete":
            if (this.tasks.isEmpty() || this.tasks.getSize() <= p.getTaskNum()) {
                ui.printNoTaskMessage();
            } else {
                this.tasks.remove(p.getTaskNum());
                ui.printTaskRemovedMessage((p.getTaskNum()));
                break;
            }
        case "done":
            Task done = this.tasks.get(p.getTaskNum());
            done.markDone();
            this.tasks.set(p.getTaskNum(), done);
            ui.printDoneMessage((done));
            break;
        case "add":
            this.tasks.add(p.getTask());
            ui.printTaskAddedMessage(p.getTask(), this.tasks.getSize());
            break;
        case "find":
            ui.printFilteredList(this.tasks.filterTasks(p.getSearchWord()));
            break;
        case "list":
            ui.printList(this.tasks);
            break;
        default:
        }
    }

    /**
     * Run method which starts the program with a greeting message.
     * @throws DukeException Throws DukeException if any input is invalid.
     */
    public void run() throws DukeException {
        Ui.printGreeting();

        while (true) {
            String userInput = ui.getUserInput();
            Parser p;

            try {
                p = Parser.parse(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (p.getCommand().equals("bye")) {
                break;
            }
            this.process(p);
        }
        Ui.printGoodbye();
    }
}
