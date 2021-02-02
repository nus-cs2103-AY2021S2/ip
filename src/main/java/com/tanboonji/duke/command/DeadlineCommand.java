package com.tanboonji.duke.command;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.Deadline;
import com.tanboonji.duke.model.Task;
import com.tanboonji.duke.parser.DateParser;

public class DeadlineCommand extends Command {

    public static final String COMMAND = "deadline";
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(.*)\\W(?=/by)/by\\W(.*)");
    private static final String ERROR_MESSAGE =
            "☹ Sorry, please enter a valid description and datetime for the deadline\n"
            + "\tCommand: deadline [description] /by [deadline]";

    private final String description;
    private final LocalDateTime by;

    private DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute() throws DukeException {
        Task newTask = new Deadline(description, by);
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

    public static DeadlineCommand parseArguments(String input) throws DukeException {
        Matcher matcher = COMMAND_FORMAT.matcher(input);

        if (!matcher.matches()) {
            throw new DukeException(ERROR_MESSAGE);
        }

        String description = matcher.group(1);
        LocalDateTime by = DateParser.parseDateTime(matcher.group(2));

        return new DeadlineCommand(description, by);
    }
}
