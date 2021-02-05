package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** The Parser makes sense of user inputs and executes follow up actions accordingly. */
public class Parser {
    /** Storage to store and update tasks entered in hard drive. */
    private Storage storage;
    /** Template for replying user. */
    private Ui ui;
    /** Manager to organise and retrieve tasks from list. */
    private TaskManager manager;

    /** Initialises a parser in chatbot to make sense of user inputs. */
    public Parser() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.manager = new TaskManager(this.storage.loadContent());
    }

    /**
     * Process the user input and execute the appropriate commands.
     * @param input Text representation of task type and task info.
     * @throws DukeException if input has no keyword or if task has no description.
     */
    public void process(String input) throws DukeException {
        if (input.contains("todo") || input.contains("deadline") 
                || input.contains("event") || input.contains("find")) {
            if (input.split(" ").length == 1) {
                throw new EmptyException();
            }
        }
        if (input.equals("list")) {
            ListCommand listcommand = new ListCommand();
            listcommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("todo")) {
            TodoCommand todocommand = new TodoCommand(input);
            todocommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("deadline")) {
            DeadlineCommand deadlinecommand = new DeadlineCommand(input);
            deadlinecommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("event")) {
            EventCommand eventcommand = new EventCommand(input);
            eventcommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("find")) {
            FindCommand findcommand = new FindCommand(input);
            findcommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("done")) {
            DoneCommand donecommand = new DoneCommand(input);
            donecommand.execute(this.manager, this.ui, this.storage);
        } else if (input.contains("delete")) {
            DeleteCommand deletecommand = new DeleteCommand(input);
            deletecommand.execute(this.manager, this.ui, this.storage);
        } else if (input.equals("bye")) {
            ExitCommand exitcommand = new ExitCommand();
            exitcommand.execute(this.manager, this.ui, this.storage);
        } else {
            throw new KeywordException();
        }
    }

    /**
     * Starts chat, reads user input and replies.
     * Exits when user says bye.
     */
    public void chat() {
        Scanner sc = new Scanner(System.in);
        String input;

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            try {
                this.process(input);
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
            if (!input.equals("bye")) {
                this.ui.separateLine();
            }   
        }
    }
}
