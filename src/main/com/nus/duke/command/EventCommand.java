package com.nus.duke.command;

import com.nus.duke.data.Event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command {

    public static final String COMMAND = "event";
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<description>.*)\\s/at\\s(?<date>.*)");

    public static final String IMPROPER_USAGE_FORMAT = "Improper event format";
    public static final String USAGE_MESSAGE = COMMAND + ": Adds a new event to the task list\n"
            + "Usage: event [description] /at [date]\n"
            + "Example: event read books /at 13-13-2021 7 PM";
    public static final String SUCCESS_MESSAGE_TEMPLATE = "Added a new Event:\n%s\nNow you have %d tasks in the list.";

    private final Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public String execute() {
        int size = this.taskList.addTask(this.event);
        return String.format(SUCCESS_MESSAGE_TEMPLATE, this.event, size);
    }

    public static Command parseArguments(String arguments) {
        final Matcher matcher = COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(IMPROPER_USAGE_FORMAT + "\n" + USAGE_MESSAGE);
        }

        final String description = matcher.group("description");
        final String date = matcher.group("date");

        Event event = new Event(description, date);
        return new EventCommand(event);
    }
}
