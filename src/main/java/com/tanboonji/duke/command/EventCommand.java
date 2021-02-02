package com.tanboonji.duke.command;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.Event;
import com.tanboonji.duke.model.Task;
import com.tanboonji.duke.parser.DateParser;

public class EventCommand extends Command {

    public static final String COMMAND = "event";
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(.*)\\W(?=\\/at)\\/at\\W(.*)");
    private static final String ERROR_MESSAGE = "☹ Sorry, please enter a valid  and datetime for the event.\n"
            + "\tCommand: event [description] /at [datetime]";

    private final String description;
    private final LocalDateTime at;

    private EventCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public String execute() throws DukeException {
        Task newTask = new Event(description, at);
        taskList.addTask(newTask);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n\t")
                .append(newTask)
                .append("\nNow you have ")
                .append(taskList.getSize());
        if (taskList.getSize() == 1) {
            builder.append(" task");
        } else {
            builder.append(" tasks");
        }
        return builder.toString();
    }

    @Override
    public boolean shouldSave() {
        return true;
    }

    public static EventCommand parseArguments(String input) throws DukeException {
        Matcher matcher = COMMAND_FORMAT.matcher(input);

        if (!matcher.matches()) {
            throw new DukeException(ERROR_MESSAGE);
        }

        String description = matcher.group(1);
        LocalDateTime at = DateParser.parseDateTime(matcher.group(2));

        return new EventCommand(description, at);
    }
}
