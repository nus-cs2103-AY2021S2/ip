package com.nus.duke.command;

import com.nus.duke.common.DukeDateParserException;
import com.nus.duke.data.Deadline;
import com.nus.duke.parser.DateParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {

    public static final String COMMAND = "deadline";
    public static final Pattern COMMAND_FORMAT = Pattern
            .compile("(?<description>.*)\\s/by\\s(?<date>.*)");
    public static final String IMPROPER_USAGE_FORMAT = "Improper deadline format";
    public static final String USAGE_MESSAGE = COMMAND + ": Adds a new deadline to the task list\n"
            + "Usage: deadline [description] /by [date]\n"
            + "Example: deadline read books /by 13-13-2021 7 PM\n"
            + "Date formats available: dd-MM-yyyy or dd/MM/yyyy or Sunday\n"
            + "Time formats available: 24 Hour Format [HH:mm][HHmm] or 12 Hour Format [hh:mm am/pm][hhmm am/pm]";
    public static final String SUCCESS_MESSAGE_TEMPLATE = "Added a new Deadline:\n%s"
            + "\nNow you have %d tasks in the list.";

    private final Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public String execute() {
        int size = this.taskList.addTask(this.deadline);
        return String.format(SUCCESS_MESSAGE_TEMPLATE, this.deadline, size);
    }

    public static Command parseArguments(String arguments) {
        final Matcher matcher = COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(IMPROPER_USAGE_FORMAT + "\n" + USAGE_MESSAGE);
        }

        final String description = matcher.group("description");
        final String date = matcher.group("date");

        try {
            Deadline deadline = new Deadline(description, DateParser.parseDateTime(date));
            return new DeadlineCommand(deadline);
        } catch (DukeDateParserException e) {
            return new IncorrectCommand(IMPROPER_USAGE_FORMAT + "\n" + USAGE_MESSAGE);
        }
    }
}
