package util;

import command.*;

import java.util.*;
import java.util.stream.Collectors;

public interface Parser {
    String COMMAND_FLAG = "COMMAND_FLAG_IDENTIFIER";

    static Command parseCommand(String input) throws DukeException {
        HashMap<String, List<String>> commandMap = parseCommandMap(input);

        String commandFlag = commandMap.get(COMMAND_FLAG).get(0);

        switch (commandFlag) {
            case ListCommand.COMMAND_STRING:
                return ListCommand.fromCommandMap(commandMap);
            case FindCommand.COMMAND_STRING:
                return FindCommand.fromCommandMap(commandMap);
            case TodoCommand.COMMAND_STRING:
                return TodoCommand.fromCommandMap(commandMap);
            case DeadlineCommand.COMMAND_STRING:
                return DeadlineCommand.fromCommandMap(commandMap);
            case EventCommand.COMMAND_STRING:
                return EventCommand.fromCommandMap(commandMap);
            case DoneCommand.COMMAND_STRING:
                return DoneCommand.fromCommandMap(commandMap);
            case DeleteCommand.COMMAND_STRING:
                return DeleteCommand.fromCommandMap(commandMap);
            case QuitCommand.COMMAND_STRING:
                return QuitCommand.fromCommandMap(commandMap);
            default:
                throw new DukeException("Sorry I didn't understand that");
        }
    }

    static HashMap<String, List<String>> parseCommandMap(String input) {
        HashMap<String, List<String>> commandMap = new HashMap<>();

        // Insert COMMAND flag
        String command = input.split(" ", 2)[0];
        commandMap.put(COMMAND_FLAG, new ArrayList<>());
        commandMap.get(COMMAND_FLAG).add(command);

        // Insert other flags
        String[] flagInputs = input.split("/");
        for (String flagInput: flagInputs) {
            String[] args = flagInput.split(" ");

            // Filter out double spaces
            List<String> argList = Arrays.stream(args)
                    .filter(x -> !(x.equals("")))
                    .collect(Collectors.toList());

            // Continue if no flag was specified
            if (argList.isEmpty()) {
                continue;
            }

            // Create flag and its ArrayList
            String flag = argList.get(0);
            commandMap.put(flag, new ArrayList<>());

            // Insert the rest of the args to the flag
            for (int i = 1; i < argList.size(); i++) {
                commandMap.get(flag).add(argList.get(i));
            }
        }

        return commandMap;
    }

    static String extractCommandString(HashMap<String, List<String>> commandMap) {
        return commandMap.get(COMMAND_FLAG).get(0);
    }

    static String commandMapToString(HashMap<String, List<String>> commandMap) {
        List<String> results = new ArrayList<>();

        String command = extractCommandString(commandMap);
        List<String> descriptions = commandMap.get(command);
        String description = String.join(" ", descriptions);

        results.add(command + " " + description);

        for (String k: commandMap.keySet()) {

            // Skip adding the command flag
            if (k.equals(COMMAND_FLAG)) continue;
            // Skip the first command + description
            if (k.equals(command)) continue;

            List<String> args = commandMap.get(k);
            String argString = String.join(" ", args);
            results.add(k + " " + argString);
        }

        return String.join("/", results);
    }
}
