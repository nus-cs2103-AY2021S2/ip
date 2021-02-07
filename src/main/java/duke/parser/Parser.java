package duke.parser;

import duke.command.AddCmd;
import duke.command.ByeCmd;
import duke.command.Command;
import duke.command.DeleteCmd;
import duke.command.DoneCmd;
import duke.command.FindCmd;
import duke.command.ListCmd;
import duke.exception.DukeException;
import duke.task.TaskType;

import java.util.Arrays;

/**
 * Interprets user input and routes them to the correct command
 */
public class Parser {

    /**
     * Returns the Command to process the line of user input. Returned Commands are initialised with arguments
     * from user input.
     *
     * @param input A line of user input
     * @return Command
     */
    public static Command parse(String input) {
        String[] words = input.trim().split(" ");
        String cmdStr = words[0];

        // Recombine cmdArgs for further parsing in individual cmd classes
        String[] remain = Arrays.copyOfRange(words, 1, words.length);
        String cmdArgs = String.join(" ", remain);

        Command cmd;

        switch (cmdStr) {
        case "bye":
            cmd = new ByeCmd();
            break;
        case "list":
            cmd = new ListCmd();
            break;
        case "done":
            cmd = new DoneCmd(cmdArgs);
            break;
        case "todo":
        case "deadline":
        case "event":
            cmd = new AddCmd(cmdArgs, TaskType.valueOf(cmdStr.toUpperCase()));
            break;
        case "delete":
            cmd = new DeleteCmd(cmdArgs);
            break;
        case "find":
            cmd = new FindCmd(cmdArgs);
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that command means :-(");
        }

        return cmd;
    }
}
