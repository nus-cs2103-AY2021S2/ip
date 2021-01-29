package duke.logging;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.UnknownCommand;
import duke.command.Command;

public class Parser {
    public static Command parse(String[] fullCommand) {
        String type = fullCommand[0];
        String taskDescription = fullCommand[1];

        if (type.equals("list")) {
            return new ListCommand(taskDescription);
        } else if (type.equals("bye")) {
            return new ByeCommand(taskDescription);
        } else if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {
            return new AddCommand(type, taskDescription);
        } else if (type.equals("done")) {
            return new DoneCommand(taskDescription);
        } else if (type.equals("delete")) {
            return new DeleteCommand(taskDescription);
        } else {
            return new UnknownCommand(taskDescription);
        }
    }
}
