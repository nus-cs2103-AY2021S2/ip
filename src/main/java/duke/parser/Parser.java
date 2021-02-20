package duke.parser;

import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.CommandException;
import duke.exception.DukeException;
import duke.exception.EmptyException;
import duke.exception.KeywordException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/** The Parser makes sense of user inputs and executes follow up actions accordingly. */
public class Parser {
    /** Storage to store and update tasks entered in hard drive. */
    private Storage storage;
    /** Template for replying user. */
    private Ui ui;
    /** Manager to organise and retrieve tasks from list. */
    private TaskManager manager;

    /** Initialises a parser in chatbot to make sense of user inputs. */
    public Parser(Storage storage) {
        this.storage = storage;
        this.ui = new Ui();
        this.manager = new TaskManager(this.storage.loadContent());
    }

    /**
     * Process the user input and execute the appropriate commands.
     * @param input Text representation of task type and task info.
     * @throws DukeException if input has no keyword or if task has no description.
     */
    public String process(String input) throws DukeException {
        if (input.contains("todo") || input.contains("deadline")
                || input.contains("event") || input.contains("find")) {
            if (input.split(" ").length == 1) {
                throw new EmptyException();
            }
        }
        if (input.equals("list")) {
            ListCommand listcommand = new ListCommand();
            return listcommand.execute(this.manager, this.ui, this.storage);
        } else if (input.equals("help")) {
            HelpCommand helpcommand = new HelpCommand();
            return helpcommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("todo")) {
            TodoCommand todocommand = new TodoCommand(input);
            return todocommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("deadline")) {
            if (!input.contains("by")) {
                throw new KeywordException();
            }
            DeadlineCommand deadlinecommand = new DeadlineCommand(input);
            return deadlinecommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("event")) {
            if (!input.contains("at")) {
                throw new KeywordException();
            }
            EventCommand eventcommand = new EventCommand(input);
            return eventcommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("find")) {
            FindCommand findcommand = new FindCommand(input);
            return findcommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("done")) {
            DoneCommand donecommand = new DoneCommand(input);
            return donecommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("delete")) {
            DeleteCommand deletecommand = new DeleteCommand(input);
            return deletecommand.execute(this.manager, this.ui, this.storage);
        } else if (input.equals("bye")) {
            ExitCommand exitcommand = new ExitCommand();
            return exitcommand.execute(this.manager, this.ui, this.storage);
        } else {
            throw new CommandException();
        }
    }
}
