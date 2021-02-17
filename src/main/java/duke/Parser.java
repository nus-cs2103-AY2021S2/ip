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
            return new ByeCommand();

        } else if (str.startsWith("list")) {
            return new ListCommand();

        } else if (str.startsWith("help")) {
            return new HelpCommand();

        } else if (str.startsWith("delete") && isCorrectDeleteString(str)) {
            int taskNum = Integer.parseInt(str.substring(7));
            return new DeleteCommand(taskNum);

        } else if (str.startsWith("done") && isCorrectDoneString(str)) {
            int taskNum = Integer.parseInt(str.substring(5));
            return new DoneCommand(taskNum);

        } else if (str.startsWith("find")) {
            String text = str.substring(5);
            return new FindCommand(text);

        } else if (str.startsWith("edit")) {
            return editTask(str);

        } else {
            return addTask(str);
        }
    }

    /**
     * Parses user input for adding tasks and returns the corresponding command
     *
     * @param str User input
     * @return Corresponding command for Duke to execute
     */
    public Command addTask(String str) {
        if (str.startsWith("todo ") && isCorrectTodoString(str)) {
            String name = str.substring(5);
            Todo curr = new Todo(name, false);
            return new AddCommand(curr);

        } else if (str.startsWith("deadline ") && isCorrectDeadlineString(str)) {
            int cut = str.indexOf("/by");
            String name = str.substring(9, cut - 1);
            String time = str.substring(cut + 4);
            Deadline curr = new Deadline(name, false, time);
            return new AddCommand(curr);

        } else if (str.startsWith("event ") && isCorrectEventString(str)) {
            int cut = str.indexOf("/at");
            String name = str.substring(6, cut - 1);
            String time = str.substring(cut + 4);
            Event curr = new Event(name, false, time);
            return new AddCommand(curr);
        } else {
            return new ErrorCommand(ui.printUnknownInputError());
        }
    }


    /**
     * Parses user input for editing tasks and returns the corresponding command
     *
     * @param str User input
     * @return Corresponding command for Duke to execute
     */
    public Command editTask(String str) {
        if (str.contains("/name") && isCorrectEditNameString(str)) {
            int cut = str.indexOf("/name");
            int index = Integer.parseInt(str.substring(5, cut - 1));
            String newName = str.substring(cut + 6);
            return new EditNameCommand(index, newName);

        } else if (str.contains("/time") && isCorrectEditTimeString(str)) {
            int cut = str.indexOf("/time");
            int index = Integer.parseInt(str.substring(5, cut - 1));
            String newTime = str.substring(cut + 6);
            return new EditTimeCommand(index, newTime);

        } else {
            return new ErrorCommand(ui.printUnknownInputError());
        }
    }

    public boolean isCorrectDeleteString(String str) {
        try {
            int taskNum = Integer.parseInt(str.substring(7));
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean isCorrectDoneString(String str) {
        try {
            int taskNum = Integer.parseInt(str.substring(5));
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean isCorrectTodoString(String str) {
        String name = str.substring(5);
        if (name.length() == 0) {
            return false;
        }
        return true;
    }

    public boolean isCorrectDeadlineString(String str) {
        int cut = str.indexOf("/by");
        if (cut == -1) {
            return false;
        }

        String name = str.substring(9, cut - 1);
        if (name.length() == 0) {
            return false;
        }

        String time = str.substring(cut + 4);
        try {
            LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    public boolean isCorrectEventString(String str) {
        int cut = str.indexOf("/at");
        if (cut == -1) {
            return false;
        }

        String name = str.substring(6, cut - 1);
        if (name.length() == 0) {
            return false;
        }

        String time = str.substring(cut + 4);
        try {
            LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    public boolean isCorrectEditNameString(String str) {
        int cut = str.indexOf("/name");
        try {
            int index = Integer.parseInt(str.substring(5, cut - 1));
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return false;
        }


        String newName = str.substring(cut + 6);
        if (newName.length() == 0) {
            return false;
        }
        return true;
    }

    public boolean isCorrectEditTimeString(String str) {
        int cut = str.indexOf("/time");

        try {
            int index = Integer.parseInt(str.substring(5, cut - 1));
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return false;
        }

        String newTime = str.substring(cut + 6);
        try {
            LocalDate.parse(newTime);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }





}
