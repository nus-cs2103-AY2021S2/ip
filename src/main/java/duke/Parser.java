package duke;

import duke.commands.*;
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
        } else {
            return addTasks(str);
        }
    }

    /**
     * Parses user input for adding tasks and returns the corresponding command
     *
     * @param str User input
     * @return Corresponding command for Duke to execute
     */
    public Command addTasks(String str) {
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

}
