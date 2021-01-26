package duke;

import duke.commands.ByeCommand;
import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ListCommand;

import duke.exceptions.UnknownInputException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

public class Parser {

    public Command parse(String str) throws UnknownInputException {
        if (str.startsWith("bye")) {
            return new ByeCommand();
        } else if (str.startsWith("list")) {
            return new ListCommand();
        } else if (str.startsWith("delete")) {
            int taskNum = Integer.valueOf(str.substring(7));
            return new DeleteCommand(taskNum);
        } else if (str.startsWith("done")) {
            int taskNum = Integer.valueOf(str.substring(5));
            return new DoneCommand(taskNum);
        } else {
            return addTasks(str);
        }
    }

    public Command addTasks(String str) throws UnknownInputException {
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
            throw new UnknownInputException();
        }
    }

}
