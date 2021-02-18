package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EditNameCommand;
import duke.commands.EditTimeCommand;
import duke.commands.ErrorCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Parser class to parse user input
 */
public class Parser {

    private Ui ui = new Ui();

    /**
     * Parses user input and returns the corresponding command
     *
     * @param str User input
     * @return Corresponding command for Duke to execute
     */
    public Command parse(String str) {
        if (str.startsWith("bye")) {
            return parseByeCommand(str);
        } else if (str.startsWith("list")) {
            return parseListCommand(str);
        } else if (str.startsWith("help")) {
            return parseHelpCommand(str);
        } else if (str.startsWith("delete ")) {
            return parseDeleteString(str);
        } else if (str.startsWith("done ")) {
            return parseDoneString(str);
        } else if (str.startsWith("find ")) {
            return parseFindString(str);
        } else if (str.startsWith("edit ")) {
            if (str.contains(" /name ")) {
                return parseEditNameString(str);
            } else if (str.contains( " /time ")) {
                return parseEditTimeString(str);
            } else {
                return new ErrorCommand(ui.printUnknownInputError());
            }
        } else if (str.startsWith("todo ")) {
            return parseTodoString(str);
        } else if (str.startsWith("deadline ")) {
            return parseDeadlineString(str);
        } else if (str.startsWith("event ")) {
            return parseEventString(str);
        } else {
            return new ErrorCommand(ui.printUnknownInputError());
        }
    }

    public Command parseByeCommand(String str) {
        if (str.equals("bye")) {
            return new ByeCommand();
        } else {
            return new ErrorCommand(ui.printUnknownInputError());
        }
    }

    public Command parseListCommand(String str) {
        if (str.equals("list")) {
            return new ListCommand();
        } else {
            return new ErrorCommand(ui.printUnknownInputError());
        }
    }

    public Command parseHelpCommand(String str) {
        if (str.equals("help")) {
            return new HelpCommand();
        } else {
            return new ErrorCommand(ui.printUnknownInputError());
        }
    }

    public Command parseDeleteString(String str) {
        try {
            int taskNum = Integer.parseInt(str.substring(7));
            return new DeleteCommand(taskNum);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return new ErrorCommand(ui.printWrongIndexError());
        }
    }

    public Command parseDoneString(String str) {
        try {
            int taskNum = Integer.parseInt(str.substring(5));
            return new DoneCommand(taskNum);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return new ErrorCommand(ui.printWrongIndexError());
        }
    }

    public Command parseFindString(String str) {
        try {
            String keyword = str.substring(5);
            return new FindCommand(keyword);
        } catch (StringIndexOutOfBoundsException e) {
            return new ErrorCommand(ui.printMissingKeywordError());
        }

    }

    public Command parseTodoString(String str) {
        String name = str.substring(5);
        if (name.length() == 0) {
            return new ErrorCommand(ui.printWrongNameError());
        }
        if (name.contains("||")) {
            return new ErrorCommand(ui.printSpecialCharacterUsedError());
        }
        Todo curr = new Todo(name, false);
        return new AddCommand(curr);
    }

    public Command parseDeadlineString(String str) {
        int cut = str.indexOf(" /by ");
        if (cut == -1) {
            return new ErrorCommand(ui.printWrongTimeError());
        }

        String name = str.substring(9, cut);
        if (name.length() == 0) {
            return new ErrorCommand(ui.printWrongNameError());
        }
        if (name.contains("||")) {
            return new ErrorCommand(ui.printSpecialCharacterUsedError());
        }

        String time = str.substring(cut + 5);
        try {
            LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            return new ErrorCommand(ui.printWrongTimeError());
        }

        Deadline curr = new Deadline(name, false, time);
        return new AddCommand(curr);
    }

    public Command parseEventString(String str) {
        int cut = str.indexOf(" /at ");
        if (cut == -1) {
            return new ErrorCommand(ui.printWrongTimeError());
        }

        String name = str.substring(6, cut);
        if (name.length() == 0) {
            return new ErrorCommand(ui.printWrongNameError());
        }
        if (name.contains("||")) {
            return new ErrorCommand(ui.printSpecialCharacterUsedError());
        }

        String time = str.substring(cut + 5);
        try {
            LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            return new ErrorCommand(ui.printWrongTimeError());
        }

        Event curr = new Event(name, false, time);
        return new AddCommand(curr);
    }

    public Command parseEditNameString(String str) {
        try {
            int cut = str.indexOf(" /name ");
            int index = Integer.parseInt(str.substring(5, cut));
            String newName = str.substring(cut + 7);

            if (newName.length() == 0) {
                return new ErrorCommand(ui.printWrongNameError());
            }
            if (newName.contains("||")) {
                return new ErrorCommand(ui.printSpecialCharacterUsedError());
            }

            return new EditNameCommand(index, newName);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return new ErrorCommand(ui.printWrongIndexError());
        }


    }

    public Command parseEditTimeString(String str) {
        try {
            int cut = str.indexOf(" /time ");
            int index = Integer.parseInt(str.substring(5, cut));
            String newTime = str.substring(cut + 7);
            LocalDate.parse(newTime);
            return new EditTimeCommand(index, newTime);
        } catch (StringIndexOutOfBoundsException | NumberFormatException | DateTimeParseException e) {
            return e instanceof DateTimeParseException
                    ? new ErrorCommand(ui.printWrongTimeError())
                    : new ErrorCommand(ui.printWrongIndexError());
        }

    }





}
