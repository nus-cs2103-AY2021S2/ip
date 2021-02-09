package duke;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EditNameCommand;
import duke.commands.EditTimeCommand;
import duke.commands.ErrorCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Parser class to parse user input
 */
public class Parser {

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
        } else if (str.startsWith("delete")) {
            int taskNum = Integer.parseInt(str.substring(7));
            return new DeleteCommand(taskNum);
        } else if (str.startsWith("done")) {
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
        if (str.startsWith("todo ")) {
            Todo curr = new Todo(str.substring(5), false);
            return new AddCommand(curr);
        } else if (str.startsWith("deadline ")) {
            int cut = str.indexOf("/by");
            Deadline curr = new Deadline(str.substring(9, cut - 1), false, str.substring(cut + 4));
            return new AddCommand(curr);
        } else if (str.startsWith("event ")) {
            int cut = str.indexOf("/at");
            Event curr = new Event(str.substring(6, cut - 1), false, str.substring(cut + 4));
            return new AddCommand(curr);
        } else {
            return new ErrorCommand();
        }
    }

    /**
     * Parses user input for editing tasks and returns the corresponding command
     *
     * @param str User input
     * @return Corresponding command for Duke to execute
     */
    public Command editTask(String str) {
        if (str.contains("/name")) {
            int cut = str.indexOf("/name");
            int index = Integer.parseInt(str.substring(5, cut - 1));
            String newName = str.substring(cut + 6);
            return new EditNameCommand(index, newName);
        } else if (str.contains("/time")) {
            int cut = str.indexOf("/time");
            int index = Integer.parseInt(str.substring(5, cut - 1));
            String newTime = str.substring(cut + 6);
            return new EditTimeCommand(index, newTime);
        } else {
            return new ErrorCommand();
        }
    }

}
