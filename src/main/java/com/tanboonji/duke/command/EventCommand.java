package com.tanboonji.duke.command;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.Event;
import com.tanboonji.duke.model.Task;
import com.tanboonji.duke.parser.DateParser;

/**
 * The EventCommand class contains information to execute the "event" command.
 */
public class EventCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "event";
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(.*)\\W(?=\\/at)\\/at\\W(.*)");
    private static final String ERROR_MESSAGE = "Sorry, please enter a valid  and datetime for the event.\n"
            + "\tCommand: event [description] /at [datetime]";
    private static final int DESCRIPTION_GROUP = 1;
    private static final int DATE_GROUP = 2;

    private final String description;
    private final LocalDateTime at;

    private EventCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
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
    public String execute() throws DukeException {
        Task newTask = new Event(description, at);
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
     * Returns new event command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New event command.
     * @throws DukeException If user input does not match event command format.
     */
    public static EventCommand parseArguments(String arguments) throws DukeException {
        Matcher matcher = COMMAND_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            throw new DukeException(ERROR_MESSAGE);
        }

        String description = matcher.group(DESCRIPTION_GROUP);
        LocalDateTime at = DateParser.parseDateTime(matcher.group(DATE_GROUP));

        return new EventCommand(description, at);
    }
}
