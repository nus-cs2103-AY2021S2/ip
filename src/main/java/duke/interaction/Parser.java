package duke.interaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SnoozeCommand;
import duke.command.TodoCommand;
import duke.common.DukeException;
import duke.common.DukeString;
import javafx.application.Platform;

/**
 * A class that handles the interpretation of the given command to DukeBot.
 */
public class Parser {
    private boolean isBye;
    private final DateTimeFormatter formatter;

    /**
     * Constructs a new Parser.
     */
    public Parser() {
        this.isBye = false;
        this.formatter = DateTimeFormatter.ofPattern(DukeString.FORMAT_DATE_INPUT);
    }

    /**
     * Getter for the bye field.
     *
     * Used to indicate that the parser received a bye.
     * @return the value of bye.
     */
    public boolean isBye() {
        return this.isBye;
    }

    /**
     * Parse the input as given, construct the appropriate command with content, if any.
     *
     * @param input the command string to be parsed from the user.
     * @return the appropriate command with content and dates, if any.
     * @throws DukeException.InvalidCommand if there is no command found.
     * @throws DukeException.InvalidTask if the task specified for done or delete is invalid.
     * @throws DukeException.InvalidDateFormat if the date format for an event or deadline is invalid.
     * @throws DukeException.EmptyDescription if the description for a task is empty.
     * @throws DukeException.EmptyDeadlineDate if the command is deadline and no date is specified.
     * @throws DukeException.EmptyEventDate if the command is event and both dates are not specified.
     * @throws DukeException.InvalidEventEnd if the command is event and the end is before the start.
     */
    public Command parseInput(final String input) throws
            DukeException.InvalidCommand,
            DukeException.InvalidTask,
            DukeException.InvalidDateFormat,
            DukeException.EmptyDescription,
            DukeException.EmptyDeadlineDate,
            DukeException.EmptyEventDate,
            DukeException.InvalidEventEnd {
        Scanner scanner = new Scanner(input);

        if (!scanner.hasNext()) {
            throw new DukeException.InvalidCommand();
        }
        switch (scanner.next().toLowerCase()) {
        case DukeString.COMMAND_BYE:
            return getExitCommand();
        case DukeString.COMMAND_LIST:
            return getListCommand();
        case DukeString.COMMAND_DONE:
            return getDoneCommand(scanner);
        case DukeString.COMMAND_DELETE:
            return getDeleteCommand(scanner);
        case DukeString.COMMAND_DEADLINE:
            return getDeadlineCommand(scanner);
        case DukeString.COMMAND_EVENT:
            return getEventCommand(scanner);
        case DukeString.COMMAND_TODO:
            return getTodoCommand(scanner);
        case DukeString.COMMAND_FIND:
            return getFindCommand(scanner);
        case DukeString.COMMAND_SNOOZE:
            return getSnoozeCommand(scanner);
        default:
            throw new DukeException.InvalidCommand();
        }
    }
    private ExitCommand getExitCommand() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
            }
        }, 2000);
        this.isBye = true;
        return new ExitCommand();
    }

    private ListCommand getListCommand() {
        return new ListCommand();
    }

    private DoneCommand getDoneCommand(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            throw new DukeException.InvalidTask();
        }
        return new DoneCommand(scanner.nextInt());
    }

    private DeleteCommand getDeleteCommand(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            throw new DukeException.InvalidTask();
        }
        return new DeleteCommand(scanner.nextInt());
    }

    private DeadlineCommand getDeadlineCommand(Scanner scanner) {
        if (!scanner.hasNext()) {
            throw new DukeException.EmptyDescription(DukeString.COMMAND_DEADLINE);
        }

        String[] tokens = scanner.nextLine().split(DukeString.COMMAND_DEADLINE_SEP);

        if (tokens.length < 2 || tokens[1].isBlank()) {
            throw new DukeException.EmptyDeadlineDate();
        }

        LocalDateTime startDate;
        try {
            startDate = LocalDateTime.parse(tokens[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException.InvalidDateFormat();
        }

        return new DeadlineCommand(tokens[0].trim(), startDate);
    }

    private EventCommand getEventCommand(Scanner scanner) {
        if (!scanner.hasNext()) {
            throw new DukeException.EmptyDescription(DukeString.COMMAND_EVENT);
        }

        String[] tokens = scanner.nextLine().split(DukeString.COMMAND_EVENT_SEP);

        if (tokens.length < 2 || tokens[1].isBlank()) {
            throw new DukeException.EmptyEventDate();
        }

        String[] dates = tokens[1].split(DukeString.COMMAND_EVENT_TO);

        if (dates.length < 2 || dates[0].isBlank() || dates[1].isBlank()) {
            throw new DukeException.EmptyEventDate();
        }

        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = LocalDateTime.parse(dates[0].trim(), formatter);
            endDate = LocalDateTime.parse(dates[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException.InvalidDateFormat();
        }

        if (startDate.compareTo(endDate) >= 0) {
            throw new DukeException.InvalidEventEnd();
        }

        return new EventCommand(tokens[0].trim(), startDate, endDate);
    }

    private TodoCommand getTodoCommand(Scanner scanner) {
        if (!scanner.hasNext()) {
            throw new DukeException.EmptyDescription(DukeString.COMMAND_TODO);
        }
        return new TodoCommand(scanner.nextLine().trim());
    }

    private FindCommand getFindCommand(Scanner scanner) {
        if (!scanner.hasNext()) {
            throw new DukeException.EmptyDescription(DukeString.COMMAND_FIND);
        }
        return new FindCommand(scanner.next());
    }


    private SnoozeCommand getSnoozeCommand(Scanner scanner) {
        if (!scanner.hasNext()) {
            throw new DukeException.InvalidTask();
        }

        String[] tokens = scanner.nextLine().split(DukeString.COMMAND_SNOOZE_REGEX);

        if (tokens.length < 2 || tokens[1].isBlank()) {
            throw new DukeException.EmptyDeadlineDate();
        }
        //Check if first token is a valid digit
        if (!tokens[0].trim().matches("\\d+")) {
            throw new DukeException.InvalidTask();
        }

        String[] dates = tokens[1].split(DukeString.COMMAND_EVENT_TO);

        LocalDateTime startDate;
        try {
            startDate = LocalDateTime.parse(dates[0].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException.InvalidDateFormat();
        }

        if (dates.length < 2 || !tokens[1].contains("/to")) {
            return new SnoozeCommand(Integer.parseInt(tokens[0].trim()), startDate);
        }

        LocalDateTime endDate;
        try {
            endDate = LocalDateTime.parse(dates[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException.InvalidDateFormat();
        }

        if (startDate.compareTo(endDate) >= 0) {
            throw new DukeException.InvalidEventEnd();
        }

        return new SnoozeCommand(Integer.parseInt(tokens[0].trim()), startDate, endDate);
    }

}
