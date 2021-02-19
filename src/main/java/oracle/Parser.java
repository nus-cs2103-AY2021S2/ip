package oracle;

import command.*;

public class Parser {
    private final Ui ui;

    /**
     * Create a new Parser object
     *
     * @param ui: stores ui given to log errors if they occur.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses the input gotten by the Ui, returns a command to the Oracle to execute, but doesnt execute anything
     * by itself
     *
     * @param input: this is the raw String given by the user
     * @return specified Command
     */
    public Command parse(String input) {
        assert (!input.isEmpty()); // assert that we have non-empty string
        String[] split = input.split(" ", 2); // Split user input - used for operations with params
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (split[0].equals("done")) {
            return parseDoneInstruction(split);
        } else if (split[0].equals("delete")) {
            return parseDeleteInstruction(split);
        } else if (split[0].equals("todo")) {
            return parseTodoInstruction(split);
        } else if (split[0].equals("event") && split.length > 1) {
            return parseEventInstruction(split);
        } else if (split[0].equals("deadline")) {
            return parseDeadlineCommand(split);
        } else if (split[0].equals("find")) {
            return parseFindCommand(split);
        } else if (split[0].equals("postpone")) {
            return parsePostponeCommand(split);
        } else {
            ui.showFormatException();
            return new EmptyCommand();
        }
    }

    /**
     * CASE: POSTPONE TASK
     * use: find the task with the given index and postpone it by the given amount
     * form: 'postpone {id} {value} {measure}'
     * measure: 'mins', 'hrs', 'days', or 'weeks'
     * @param split: contains the arguments of instruction
     * @return FindCommand (success), EmptyCommand (failure)
     */
    private Command parsePostponeCommand(String[] split) {
        try {
            String[] params = split[1].split(" ", 3);
            int taskIndex = Integer.parseInt(params[0]) - 1;
            return new PostponeCommand(taskIndex, Integer.parseInt(params[1]), params[2]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            ui.showFormatException("PostponeCommand");
            return new EmptyCommand();
        }
    }

    /**
     * CASE: FIND TASKS BASED ON KEYWORD
     * use: find all the tasks containing the user specified keyword
     * form: 'find {description} /{deadline}'
     *
     * @param split: contains the arguments of instruction
     * @return FindCommand (success), EmptyCommand (failure)
     */
    private Command parseFindCommand(String[] split) {
        try {
            String keyword = split[1]; // obtain keyword to search for
            return new FindCommand(keyword);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showFormatException("FindCommand");
        }
        return new EmptyCommand();
    }

    /**
     * CASE: CREATE DEADLINE
     * use: creates a deadline with the given description and deadline
     * form: 'deadline {description} /{deadline}'
     *
     * @param split: contains the arguments of instruction
     * @return DeadlineCommand (success), EmptyCommand (failure)
     */
    private Command parseDeadlineCommand(String[] split) {
        try {
            String[] params = split[1].split("/", 2);
            return new DeadlineCommand(params[0], params[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showFormatException("DeadlineCommand");
        }
        return new EmptyCommand();
    }

    /**
     * CASE: CREATE EVENT
     * use: creates a event with the given description and time
     * form: 'event {description} /{time}'
     *
     * @param split: contains the arguments of instruction
     * @return EventCommand (success), EmptyCommand (failure)
     */
    private Command parseEventInstruction(String[] split) {
        try {
            String[] params = split[1].split("/", 2);
            return new EventCommand(params[0], params[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showFormatException("EventCommand");
        }
        return new EmptyCommand();
    }

    /**
     * CASE: CREATE TODO
     * use: creates a todo with the given description
     * form: 'todo {description}'
     *
     * @param split contains the arguments of instruction
     * @return TodoCommand (success), EmptyCommand (failure)
     */
    private Command parseTodoInstruction(String[] split) {
        try {
            return new TodoCommand(split[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showFormatException("TodoCommand");
        }
        return new EmptyCommand();
    }

    /**
     * CASE: DELETE TASK
     * use: deletes the task with the given id from the arraylist
     * form: 'delete {id}', where id is a valid int corresponding to size of tasklist
     *
     * @param split contains the arguments of instruction
     * @return DeleteCommand (success), EmptyCommand (failure)
     */
    private Command parseDeleteInstruction(String[] split) {
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

    /**
     * CASE: MARK TASK AS DONE
     * use: marks the task with given id as done
     * form: 'done {id}', where id is a valid int corresponding to size of tasklist
     *
     * @param split: contains the arguments of instruction
     * @return DoneCommand (success), EmptyCommand (failure)
     */
    private Command parseDoneInstruction(String[] split) {
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
}
