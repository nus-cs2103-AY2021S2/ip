import Duke.*;
import DukeException.DukeException;
import Parser.Parser;
import Storage.Storage;
import Ui.Ui;


public class Duke {
    public static final String LOG_PATH = "./duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasks = new TaskList(this.storage);
        this.ui = new Ui();
    }

    public static void main(String[] args) throws DukeException {
        try {
            new Duke(LOG_PATH).run();
        } catch (DukeException e) {
            throw new DukeException("unable to load file, probably wrong format.");
        }
    }

    private void process(Parser p) {

        switch (p.getCommand()) {
            case "delete":
                this.tasks.remove(p.getTaskNum());
                ui.printTaskRemovedMessage((p.getTaskNum()));
                break;
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
            case "set":
                this.tasks.set(p.getTaskNum(), p.getTask());
                break;
            case "list":
                ui.printList(this.tasks);
                break;
            default:
        }
    }

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
