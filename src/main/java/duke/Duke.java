package duke;

import dukeexception.DukeException;
import parser.Parser;
import storage.Storage;
import ui.Ui;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for duke.Duke object.
     * @param filepath The filepath of the list of tasks to be stored at.
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasks = new TaskList(this.storage);
        this.ui = new Ui();
    }

    /**
     * A process method with a parser object.
     * @param p The parser object with parsed information.
     * @throws DukeException Throws an exception if there are any invalid information.
     */
    public String process(Parser p) throws DukeException {
        switch (p.getCommand()) {
        case "delete":
            if (this.tasks.isEmpty() || this.tasks.getSize() < p.getTaskNum()) {
                return ui.printNoTaskMessage();
            } else {
                this.tasks.remove(p.getTaskNum());
                return ui.printTaskRemovedMessage((p.getTaskNum()));
            }
        case "done":
            Task done = this.tasks.get(p.getTaskNum());
            done.markDone();
            this.tasks.set(p.getTaskNum(), done);
            return ui.printDoneMessage((done));
        case "add":
            this.tasks.add(p.getTask());
            return ui.printTaskAddedMessage(p.getTask(), this.tasks.getSize());
        case "find":
            return ui.printFilteredList(this.tasks.filterTasks(p.getSearchWord()));
        case "list":
            return Ui.printList(this.tasks);
        default:
            return "";
        }
    }

    /**
     * Gets the UI of Duke.
     *
     * @return UI of Duke.
     */
    public Ui getUi() {
        return ui;
    }
}
