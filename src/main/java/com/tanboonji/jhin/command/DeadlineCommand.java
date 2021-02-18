package com.tanboonji.jhin.command;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.jhin.exception.InvalidCommandArgumentException;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.Deadline;
import com.tanboonji.jhin.model.Task;
import com.tanboonji.jhin.parser.DateParser;

/**
 * The DeadlineCommand class contains information to execute the "deadline" command.
 */
public class DeadlineCommand extends Command {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("(.*)\\W(?=/by)/by\\W(.*)");
    private static final String INVALID_ARGUMENT_MESSAGE = "Sorry, the deadline command you entered is invalid.\n"
                    + "Please enter a valid deadline command in the following format:\n"
                    + "deadline <description> /by <date> <time>";
    private static final String SUCCESS_MESSAGE_FORMAT = "Got it. I've added this deadline task:\n"
            + "%s\n"
            + "Now you have %d %s.";
    private static final String TASK_SINGULAR = "task";
    private static final String TASK_PLURAL = "tasks";
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
    public boolean shouldExitJhin() {
        return false;
    }

    @Override
    public String execute() {
        Task newTask = new Deadline(description, by);
        taskList.addTask(newTask);

        String taskSingularPlural = (taskList.getSize() > 1) ? TASK_PLURAL : TASK_SINGULAR;
        return String.format(SUCCESS_MESSAGE_FORMAT, newTask, taskList.getSize(), taskSingularPlural);
    }

    /**
     * Returns new deadline command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New deadline command.
     * @throws JhinException If arguments does not match deadline command format.
     */
    public static DeadlineCommand parseArguments(String arguments) throws JhinException {
        Matcher matcher = COMMAND_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(INVALID_ARGUMENT_MESSAGE);
        }

        String description = matcher.group(DESCRIPTION_GROUP);
        LocalDateTime by = DateParser.parseDateTime(matcher.group(DATE_GROUP));

        return new DeadlineCommand(description, by);
    }
}
