package com.tanboonji.jhin.command;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.jhin.exception.InvalidCommandArgumentException;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.Event;
import com.tanboonji.jhin.model.Task;
import com.tanboonji.jhin.parser.DateParser;

/**
 * The EventCommand class contains information to execute the "event" command.
 */
public class EventCommand extends Command {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("(.*)\\W(?=/at)/at\\W(.*)");
    private static final String ERROR_MESSAGE = "Sorry, please enter a valid description and datetime for the event.\n"
            + "Command: event [description] /at [datetime]";
    private static final String SUCCESS_MESSAGE = "Got it. I've added this task:\n"
            + "%s\n"
            + "Now you have %d %s.";
    private static final String TASK_SINGULAR = "task";
    private static final String TASK_PLURAL = "tasks";
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
    public boolean shouldExitJhin() {
        return false;
    }

    @Override
    public String execute() {
        Task newTask = new Event(description, at);
        taskList.addTask(newTask);

        String taskSingularPlural = (taskList.getSize() > 1) ? TASK_PLURAL : TASK_SINGULAR;
        return String.format(SUCCESS_MESSAGE, newTask, taskList.getSize(), taskSingularPlural);
    }

    /**
     * Returns new event command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New event command.
     * @throws JhinException If user input does not match event command format.
     */
    public static EventCommand parseArguments(String arguments) throws JhinException {
        Matcher matcher = COMMAND_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }

        String description = matcher.group(DESCRIPTION_GROUP);
        LocalDateTime at = DateParser.parseDateTime(matcher.group(DATE_GROUP));

        return new EventCommand(description, at);
    }
}
