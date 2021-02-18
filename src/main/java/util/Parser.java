package util;

import command.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parser is a utility interface that specifies the convention for the CommandMap
 * HashMap structure. Parser is also responsible for converting user input into
 * valid CommandMaps and existing Tasks into saveStrings for storage in the save file.
 */
public interface Parser {
    String COMMAND_FLAG = "COMMAND_FLAG_IDENTIFIER";

    /**
     * Parses the user input into an executable Command object
     *
     * @param input String input from the user.
     * @return Command object representing the user's instructions.
     * @throws DukeException Whenever the user inputs an illegal instruction.
     */
    static Command parseCommand(String input) throws DukeException {
        HashMap<String, List<String>> commandMap = parseCommandMap(input);

        String commandFlag = commandMap.get(COMMAND_FLAG).get(0);

        switch (commandFlag) {
        case HelpCommand.COMMAND_STRING:
            return HelpCommand.fromCommandMap(commandMap);
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
            throw new DukeException("Sorry I didn't understand "
                    + "\"" + commandFlag + "\"!\n"
                    + "Try typing \"help\" to see the available commands.");
        }
    }

    /**
     * Parses the user input into a commandMap.
     *
     * @param input String input from the user.
     * @return HashMap representing the commandMap of the user's input
     */
    static HashMap<String, List<String>> parseCommandMap(String input) {
        HashMap<String, List<String>> commandMap = new HashMap<>();

        // Insert COMMAND flag
        String command = input.split(" ", 2)[0];
        commandMap.put(COMMAND_FLAG, new ArrayList<>());
        commandMap.get(COMMAND_FLAG).add(command);

        // Insert other flags
        String[] flagInputs = input.split("/");
        for (String flagInput : flagInputs) {
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

    /**
     * Extracts the command or first word from a commandMap.
     *
     * @param commandMap CommandMap to be parsed.
     * @return The first word or command in the commandMap.
     */
    static String extractCommandString(HashMap<String, List<String>> commandMap) {
        return commandMap.get(COMMAND_FLAG).get(0);
    }

    /**
     * Converts a commandMap into a string representation that can be parsed to
     * obtain the same commandMap. Should produce the inverse output of
     * parseCommandMap(String: )
     *
     * @param commandMap Command map to be translated into a String instruction.
     * @return A String representation that can be parsed to obtain back the same
     *         commandMap.
     */
    static String commandMapToString(HashMap<String, List<String>> commandMap) {
        List<String> results = new ArrayList<>();

        String command = extractCommandString(commandMap);
        List<String> descriptions = commandMap.get(command);
        String description = String.join(" ", descriptions);

        results.add(command + " " + description);

        for (String k : commandMap.keySet()) {

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
