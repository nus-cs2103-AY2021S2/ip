package duke.command;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import duke.Statistics;
import duke.TaskType;
import duke.exception.AddDeadlineCommandParseException;
import duke.exception.AddEventCommandParseException;
import duke.exception.AddToDoCommandParseException;
import duke.exception.DeleteCommandParseException;
import duke.exception.DoneCommandParseException;
import duke.exception.ExitCommandParseException;
import duke.exception.FindCommandParseException;
import duke.exception.ListCommandParseException;
import duke.exception.UnknownCommandParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Encapsulates a utility parser that parses task from the saved file and
 * commands inputted by the user.
 *
 * @author Aaron Saw Min Sern
 */
public class Parser {

    /**
     * Returns a task decoded from the encoded string representation of task. This
     * parser will return null on invalid encoded strings.
     *
     * @param str the encoded string representation of task
     * @return the task corresponding to the encoded representation
     */
    public static Task parseAsTask(String str) {
        final String[] tokens = str.split(" \\| ");
        String[] datetime;
        try {
            switch (tokens[0]) {
            case "T":
                final ToDo todo = new ToDo(tokens[2]);
                if (tokens[1].equals("1")) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                datetime = tokens[3].split(" ", 2);
                Deadline deadline;
                if (datetime.length == 2) {
                    deadline = Deadline.create(tokens[2], datetime[0].strip(), datetime[1].strip());
                } else {
                    deadline = Deadline.create(tokens[2], datetime[0].strip());
                }
                if (tokens[1].equals("1")) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                datetime = tokens[3].split(" ", 2);
                Event event;
                if (datetime.length == 2) {
                    event = Event.create(tokens[2], datetime[0].strip(), datetime[1].strip());
                } else {
                    event = Event.create(tokens[2], datetime[0].strip());
                }
                if (tokens[1].equals("1")) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Returns a command decoded from the string inputted by the users. This parser
     * will throw Exceptions on invalid input strings.
     *
     * @param fullCommand the encoded string representation of task
     * @return the task corresponding to the encoded representation
     */
    public static Command parseCommand(String fullCommand) throws Exception {
        final String[] tokens = fullCommand.strip().split("\\s+", 2);
        assert(tokens.length != 0);
        final String command = tokens[0];
        switch (command) {
        case "bye":
            if (tokens.length != 1) {
                throw new ExitCommandParseException();
            }

            return new ExitCommand();
        case "list":
            if (tokens.length != 1) {
                throw new ListCommandParseException();
            }

            return new ListCommand();
        case "find":
            if (tokens.length != 2) {
                throw new FindCommandParseException();
            }

            return new FindCommand(tokens[1]);
        case "delete":
            if (tokens.length != 2) {
                throw new DeleteCommandParseException();
            }

            try {
                final int deleteIndex = Integer.parseInt(tokens[1]) - 1;
                return new DeleteCommand(deleteIndex);
            } catch (final NumberFormatException e) {
                throw new NumberFormatException("\tOops! Please input a number.");
            }
        case "done":
            if (tokens.length != 2) {
                throw new DoneCommandParseException();
            }

            try {
                final int doneIndex = Integer.parseInt(tokens[1]) - 1;
                return new DoneCommand(doneIndex);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("\tOops! Please input a number.");
            }
        case "todo":
            if (tokens.length != 2) {
                throw new AddToDoCommandParseException();
            }

            return new AddCommand(TaskType.TODO, tokens[1]);
        case "deadline":
            if (tokens.length != 2) {
                throw new AddDeadlineCommandParseException();
            }
            final String[] splitOnBy = tokens[1].split("\\s+/by\\s+", 2);
            if (splitOnBy.length != 2) {
                throw new AddDeadlineCommandParseException();
            }

            return new AddCommand(TaskType.DEADLINE, splitOnBy[0], splitOnBy[1]);
        case "event":
            if (tokens.length != 2) {
                throw new AddEventCommandParseException();
            }
            final String[] splitOnAt = tokens[1].split("\\s+/at\\s+", 2);
            if (splitOnAt.length != 2) {
                throw new AddEventCommandParseException();
            }

            return new AddCommand(TaskType.EVENT, splitOnAt[0], splitOnAt[1]);
        default:
            throw new UnknownCommandParseException();
        }
    }

    /**
     * Parses a list of String representing the statistics into a Statistics instance.
     *
     * @param   stats   the list of String encoded to the saved file
     */
    public static Statistics parseAsStats(List<String> stats) {
        List<LocalDateTime> dateTimes = stats.stream().map(str -> LocalDateTime
                .parse(str))
                .collect(Collectors.toList()
        );
        return new Statistics(dateTimes);
    }
}
