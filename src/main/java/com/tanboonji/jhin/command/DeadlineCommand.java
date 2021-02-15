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

    /** String input to execute this command */
    public static final String COMMAND = "deadline";
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(.*)\\W(?=/by)/by\\W(.*)");
    private static final String ERROR_MESSAGE =
            "Sorry, please enter a valid description and datetime for the deadline.\n"
            + "Command: deadline [description] /by [deadline]";
    private static final String SUCCESS_MESSAGE = "Got it. I've added this task:\n"
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
        return String.format(SUCCESS_MESSAGE, newTask, taskList.getSize(), taskSingularPlural);
    }

    /**
     * Returns new deadline command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New deadline command.
     * @throws JhinException If user input does not match deadline command format.
     */
    public static DeadlineCommand parseArguments(String arguments) throws JhinException {
        Matcher matcher = COMMAND_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }

        String description = matcher.group(DESCRIPTION_GROUP);
        LocalDateTime by = DateParser.parseDateTime(matcher.group(DATE_GROUP));

        return new DeadlineCommand(description, by);
    }
}
