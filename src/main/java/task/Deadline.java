package task;

import util.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class Deadline extends Task {
    public static final String COMMAND_STRING = "deadline";
    public static final String END_DATE_STRING = "by";
    private LocalDate endDate;

    public Deadline(String desc, LocalDate endDate) {
        super(desc);
        this.endDate = endDate;
    }

    public Deadline(String desc, LocalDate endDate, boolean isDone) {
        super(desc, isDone);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, d MMM yy");
        String dateString = endDate.format(dateTimeFormatter);
        return "[D]" + super.toString() + " (by: " + dateString + ")";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String getCommandString() {
        return COMMAND_STRING;
    }

    @Override
    public String toSaveString() {
        HashMap<String, List<String>> commandMap = new HashMap<>();

        // Add command flag
        commandMap.put(Parser.COMMAND_FLAG, new ArrayList<>());
        commandMap.get(Parser.COMMAND_FLAG).add(getCommandString());

        // Add description
        commandMap.put(getCommandString(), new ArrayList<>());
        commandMap.get(getCommandString()).add(super.getDescription());

        // Add end date
        commandMap.put(END_DATE_STRING, new ArrayList<>());
        commandMap.get(END_DATE_STRING).add(endDate.format(ISO_LOCAL_DATE));

        // Add done flag
        if (super.isDone()) {
            commandMap.put("done", new ArrayList<>());
        }

        return Parser.commandMapToString(commandMap);
    }

    public static Deadline fromSaveString(String saveString) {
        HashMap<String, List<String>> commandMap = Parser.parseCommandMap(saveString);
        
        List<String> descriptions = commandMap.get(COMMAND_STRING);
        String description = String.join(" ", descriptions);

        String endDateString = commandMap.get(END_DATE_STRING).get(0);
        LocalDate endDate = LocalDate.parse(endDateString);

        boolean isDone = commandMap.containsKey("done");

        return new Deadline(description, endDate, isDone);
    }
}
