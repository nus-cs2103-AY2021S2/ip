package task;

import util.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Todo extends Task {
    public static final String COMMAND_STRING = "todo";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
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

        return Parser.commandMapToString(commandMap);
    }

    public static Todo fromSaveString(String saveString) {
        HashMap<String, List<String>> commandMap = Parser.parseCommandMap(saveString);
        List<String> descriptions = commandMap.get(COMMAND_STRING);
        String description = String.join(" ", descriptions);
        return new Todo(description);
    }

}
