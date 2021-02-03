package Oracle;

import Command.*;

public class Parser {
    private final Ui ui;

    /**
     * Create a new Parser object
     * @param ui stores ui given to log errors if they occur.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses the input gotten by the Ui, returns a command to the Oracle to execute, but doesnt execute anything
     * by itself
     * @param input this is the raw String given by the user
     * @return a Command
     */
    public Command parse(String input) {
        String[] split = input.split(" ", 2); // Split user input - used for operations with params
        /* CASE: TERMINATION */
        if (input.equals("bye")) {
            return new ExitCommand();
        }
        /* CASE: LIST */
        else if (input.equals("list")) {
            return new ListCommand();
        }
            /*
             CASE: MARK TASK AS DONE
             use: marks the task with given id as done
             form: 'done {id}', where id is a valid int corresponding to size of tasklist
            */
        else if (split[0].equals("done")) {
            try {
                int i = Integer.parseInt(split[1]) - 1;
                return new MarkDoneCommand(i);
            } catch (NumberFormatException e) {
                this.ui.showNumberFormatException("done task");
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showFormatException("MarkDoneCommand");
            }
            return new EmptyCommand();
        }
            /*
             CASE: DELETE TASK
             use: deletes the task with the given id from the arraylist
             form: 'delete {id}', where id is a valid int corresponding to size of tasklist
            */
        else if (split[0].equals("delete")) {
            try {
                int i = Integer.parseInt(split[1]) - 1;
                return new DeleteCommand(i);
            } catch (NumberFormatException e) {
                this.ui.showNumberFormatException("task to be deleted");
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showFormatException("DeleteCommand");
            }
            return new EmptyCommand();
        }
            /*
             CASE: CREATE TODO
             use: creates a todo with the given description
             form: 'todo {description}'
            */
        else if (split[0].equals("todo")) {
            try {
                return new TodoCommand(split[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showFormatException("TodoCommand");
            }
            return new EmptyCommand();
        }
            /*
             CASE: CREATE EVENT
             use: creates a event with the given description and time
             form: 'event {description} /{time}'
            */
        else if (split[0].equals("event") && split.length > 1) {
            try {
                String[] params = split[1].split("/", 2);
                return new EventCommand(params[0], params[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showFormatException("EventCommand");
            }
            return new EmptyCommand();
            /*
             CASE: CREATE DEADLINE
             use: creates a deadline with the given description and deadline
             form: 'event {description} /{deadline}'
            */
        } else if (split[0].equals("deadline")) {
            try {
                String[] params = split[1].split("/", 2);
                return new DeadlineCommand(params[0], params[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showFormatException("DeadlineCommand");
            }
            return new EmptyCommand();
        } else if (split[0].equals("find")) {
            try {
                String kw = split[1];
                return new FindCommand(kw);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showFormatException("FindCommand");
            }
            return new EmptyCommand();
        }
            /*
             CASE: UNKNOWN INSTRUCTION
            */
        else {
            ui.showFormatException();
            return new EmptyCommand();
        }
    }
}
