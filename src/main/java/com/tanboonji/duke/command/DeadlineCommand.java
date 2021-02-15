package com.tanboonji.duke.command;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.exception.InvalidCommandArgumentException;
import com.tanboonji.duke.model.Deadline;
import com.tanboonji.duke.model.Task;
import com.tanboonji.duke.parser.DateParser;

/**
 * The DeadlineCommand class contains information to execute the "deadline" command.
 */
public class DeadlineCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "deadline";
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(.*)\\W(?=/by)/by\\W(.*)");
    private static final String ERROR_MESSAGE =
            "Sorry, please enter a valid description and datetime for the deadline\n"
            + "\tCommand: deadline [description] /by [deadline]";
    private static final int DESCRIPTION_GROUP = 1;
    private static final int DATE_GROUP = 2;

    private final String description;
    private final LocalDateTime by;

    private DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public boolean shouldSaveData() {
        return true;
    }

    @Override
    public boolean shouldExitDuke() {
        return false;
    }

    @Override
    public String execute() {
        Task newTask = new Deadline(description, by);
        taskList.addTask(newTask);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n\t")
                .append(newTask)
                .append("\nNow you have ")
                .append(taskList.getSize())
                .append(" task");
        if (taskList.getSize() > 1) {
            builder.append("s");
        }
        return builder.toString();
    }

    /**
     * Returns new deadline command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New deadline command.
     * @throws DukeException If user input does not match deadline command format.
     */
    public static DeadlineCommand parseArguments(String arguments) throws DukeException {
        Matcher matcher = COMMAND_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }

        String description = matcher.group(DESCRIPTION_GROUP);
        LocalDateTime by = DateParser.parseDateTime(matcher.group(DATE_GROUP));

        return new DeadlineCommand(description, by);
    }
}
